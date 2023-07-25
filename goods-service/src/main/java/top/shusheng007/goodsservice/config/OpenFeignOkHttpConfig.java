package top.shusheng007.goodsservice.config;

import feign.Client;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/11/6 15:00
 * @description:
 */
@Slf4j
@Configuration
public class OpenFeignOkHttpConfig {

    @Bean
    public okhttp3.OkHttpClient okHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //忽略证书验证
        try {
            TrustManager[] trustManagers = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustManagers, new SecureRandom());

            builder.sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustManagers[0]);
            builder.hostnameVerifier((hostname, session) -> true);
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            log.error("ignore ssl certificate error", e);
        }

        return builder
                .retryOnConnectionFailure(false)//连接失败不进行重试
                .build();
    }

    //一旦提供了自定义OkHttpClient，自动装配就失效了，所以必须提供自定义Client
    @Bean
    public Client feignClient() {
        return new feign.okhttp.OkHttpClient(okHttpClient());
    }
}
