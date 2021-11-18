package top.shusheng007.apigateway.predicates;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;

/**
 * Created by Ben.Wang
 * <p>
 * Author      Ben.Wang
 * Date        2021/10/25 17:30
 * Description
 */
public class VipCustomerRoutePredicateFactory extends AbstractRoutePredicateFactory<VipCustomerRoutePredicateFactory.Config> {


    public VipCustomerRoutePredicateFactory(Class<Config> configClass) {
        super(configClass);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return serverWebExchange -> {
            if (!config.isEnable) {
                return false;
            }
            String token = serverWebExchange.getRequest().getHeaders().getFirst("token");
            return isVip(token);
        };
    }

    //此处模拟调用其他服务判断此用户是否是vip
    private boolean isVip(String token) {
        return token != null && token.contains("vip");
    }

    public static class Config {
        private boolean isEnable;

        public boolean isEnable() {
            return isEnable;
        }

        public void setEnable(boolean enable) {
            isEnable = enable;
        }
    }
}
