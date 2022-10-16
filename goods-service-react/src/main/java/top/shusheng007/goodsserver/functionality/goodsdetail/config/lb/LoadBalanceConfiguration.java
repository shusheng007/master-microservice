package top.shusheng007.goodsserver.functionality.goodsdetail.config.lb;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by Ben.Wang
 * <p>
 * Author      Ben.Wang
 * Date        2021/10/27 17:36
 * Description
 */

@Configuration
@LoadBalancerClient(value = "order-service",configuration = CustomLoadBalancerConfiguration.class)
public class LoadBalanceConfiguration {

    @LoadBalanced
    @Bean
    public WebClient.Builder loadBalancedWebClientBuilder(){
        return WebClient.builder();
    }
}
