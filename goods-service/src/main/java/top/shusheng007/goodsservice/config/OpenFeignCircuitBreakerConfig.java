package top.shusheng007.goodsservice.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.function.Function;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/11/6 20:18
 * @description:
 */
@Configuration
public class OpenFeignCircuitBreakerConfig {

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer(){
        return new Customizer<Resilience4JCircuitBreakerFactory>() {
            @Override
            public void customize(Resilience4JCircuitBreakerFactory factory) {
                CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                        .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED) // 滑动窗口的类型为请求个数
                        .slidingWindowSize(10) // 时间窗口的大小为10个
                        .minimumNumberOfCalls(1) // 在单位时间窗口内最少需要1次调用才能开始进行统计计算
                        .failureRateThreshold(50) // 在单位时间窗口内调用失败率达到50%后会启动断路器
                        .enableAutomaticTransitionFromOpenToHalfOpen() // 允许断路器自动由打开状态转换为半开状态
                        .waitDurationInOpenState(Duration.ofSeconds(2)) // 断路器打开状态转换为半开状态需要等待2秒
                        .permittedNumberOfCallsInHalfOpenState(2) // 在半开状态下允许进行正常调用的次数
                        .recordExceptions(Throwable.class) // 所有异常都当作失败来处理
                        .build();
                TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
                        .timeoutDuration(Duration.ofMillis(500))//接口500毫秒没有响应就认为失败了
                        .build();
                factory.configureDefault(new Function<String, Resilience4JConfigBuilder.Resilience4JCircuitBreakerConfiguration>() {
                    @Override
                    public Resilience4JConfigBuilder.Resilience4JCircuitBreakerConfiguration apply(String id) {
                        return new Resilience4JConfigBuilder(id)
                                .timeLimiterConfig(timeLimiterConfig)
                                .circuitBreakerConfig(circuitBreakerConfig)
                                .build();
                    }
                });
            }
        };
    }
}
