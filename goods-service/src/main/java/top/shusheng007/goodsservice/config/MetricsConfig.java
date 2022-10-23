package top.shusheng007.goodsservice.config;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/21 00:46
 * @description:
 */
@Slf4j
@Configuration
public class MetricsConfig {

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry){
        log.info("meterRegistry类型：{}", registry.getClass().getCanonicalName());
        return new TimedAspect(registry);
    }
}
