package top.shusheng007.goodsservice.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/11/6 15:00
 * @description:
 */
@Configuration
public class OpenFeignOkHttpConfig {

    @Bean
    public okhttp3.OkHttpClient okHttpClient(OkHttpClient.Builder builder){
        return builder
                .retryOnConnectionFailure(false)//连接失败不进行重试
                .build();
    }
}
