package top.shusheng007.apigateway.predicates;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/16 16:39
 * @description:
 */

@Slf4j
public class VipCustomerRoutePredicateFactory extends AbstractRoutePredicateFactory<VipCustomerRoutePredicateFactory.Config> {
    public static final String VIP_KEY = "vipKey";
    public static final String VIP_VALUE = "vipValue";

    public VipCustomerRoutePredicateFactory() {
        super(Config.class);
    }


    //实现了这个在application.yml中配置的时候可以使用简写
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(VIP_KEY,VIP_VALUE);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return serverWebExchange -> {
            String value = serverWebExchange.getRequest().getHeaders().getFirst(config.getVipKey());
            if (!StringUtils.hasText(value) || !value.equals(config.getVipValue())) {
                log.info("屌丝用户");
                return false;
            }
            log.info("Vip用户");
            return true;
        };
    }

    public static class Config {
        private String vipKey;
        private String vipValue;

        public String getVipKey() {
            return vipKey;
        }

        public Config setVipKey(String vipKey) {
            this.vipKey = vipKey;
            return this;
        }

        public String getVipValue() {
            return vipValue;
        }

        public Config setVipValue(String vipValue) {
            this.vipValue = vipValue;
            return this;
        }
    }
}
