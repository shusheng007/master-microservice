package top.shusheng007.apigateway.config;

import org.checkerframework.checker.units.qual.K;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import top.shusheng007.apigateway.filter.AdHandlerGatewayFilterFactory;
import top.shusheng007.apigateway.predicates.VipCustomerRoutePredicateFactory;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/16 13:49
 * @description:
 */

@Configuration
public class GatewayConfig {

    @Bean
    public VipCustomerRoutePredicateFactory vipCustomerRoutePredicateFactory() {
        return new VipCustomerRoutePredicateFactory();
    }

    @Bean
    public AdHandlerGatewayFilterFactory adHandlerGatewayFilterFactory() {
        return new AdHandlerGatewayFilterFactory();
    }

    @Bean
    public KeyResolver pathKeyResolver() {
        return new KeyResolver() {
            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
//                Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
                String path = exchange.getRequest().getURI().getPath();
                return Mono.just(path);
            }
        };
    }
}
