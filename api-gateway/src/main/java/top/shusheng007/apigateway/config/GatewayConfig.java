package top.shusheng007.apigateway.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.shusheng007.apigateway.filter.AdHandlerGatewayFilterFactory;
import top.shusheng007.apigateway.predicates.VipCustomerRoutePredicateFactory;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/16 13:49
 * @description:
 */

@EnableDiscoveryClient
@Configuration
public class GatewayConfig {

    @Bean
    public VipCustomerRoutePredicateFactory vipCustomerRoutePredicateFactory(){
        return new VipCustomerRoutePredicateFactory();
    }

    @Bean
    public AdHandlerGatewayFilterFactory adHandlerGatewayFilterFactory(){
        return new AdHandlerGatewayFilterFactory();
    }
}
