package top.shusheng007.apigateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.PrefixPathGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import top.shusheng007.apigateway.predicates.VipCustomerRoutePredicateFactory;

import static org.springframework.cloud.gateway.support.GatewayToStringStyler.filterToStringCreator;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/16 17:01
 * @description:
 */

@Slf4j
public class AdHandlerGatewayFilterFactory extends AbstractGatewayFilterFactory<AdHandlerGatewayFilterFactory.Config> {

    public AdHandlerGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                String vipKey = exchange.getRequest().getHeaders().getFirst("vipKey");
                if (!StringUtils.hasText(vipKey) || !vipKey.equals(config.getVipKey())) {
                    log.info("屌丝用户");
                    return chain.filter(exchange).then(
                            Mono.fromRunnable(() -> exchange.getResponse().getHeaders()
                                    .add("clientType","屌丝")));
                }
                return chain.filter(exchange);
            }

            @Override
            public String toString() {
                return filterToStringCreator(AdHandlerGatewayFilterFactory.this)
                        .append("vipKey", config.getVipKey())
                        .toString();
            }        };
    }

    public static class Config{
        private String vipKey;

        public String getVipKey() {
            return vipKey;
        }

        public Config setVipKey(String vipKey) {
            this.vipKey = vipKey;
            return this;
        }
    }
}
