package top.shusheng007.goodsservice.api.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public class OrderFeignErrorDecoder implements ErrorDecoder {
    private ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {

        log.info("methodKey:{} response:{}", methodKey, response.toString());

        try (InputStream is = response.body().asInputStream()) {

            String s = IOUtils.toString(is, StandardCharsets.UTF_8);

            log.info("body:{}", s);

        } catch (IOException e) {
            log.error("feign request error", e);
        }

        return errorDecoder.decode(methodKey, response);
    }
}
