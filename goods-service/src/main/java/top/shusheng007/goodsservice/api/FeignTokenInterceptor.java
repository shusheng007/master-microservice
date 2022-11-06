package top.shusheng007.goodsservice.api;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/11/6 20:44
 * @description:
 */
@Slf4j
//@Component
public class FeignTokenInterceptor implements RequestInterceptor {

    private static final String TOKEN = "token";

    @Override
    public void apply(RequestTemplate template) {
        String token = getToken();
        log.info("拦截token：{}",token);
        template.header(TOKEN, token);
    }

    private String getToken() {
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            return Optional.ofNullable(request.getHeader(TOKEN)).orElse("");
        } catch (Exception exception) {
            log.error("获取request失败",exception);
        }
        return "";
    }
}
