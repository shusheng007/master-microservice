package top.shusheng007.apigateway.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/19 00:14
 * @description:
 */

@Configuration
public class MsCircuitBreakerConfig {

    //对Resilience4J的配置
    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return new Customizer<ReactiveResilience4JCircuitBreakerFactory>() {
            @Override
            public void customize(ReactiveResilience4JCircuitBreakerFactory factory) {
                CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                        .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED) // 滑动窗口的类型为时间窗口
                        .slidingWindowSize(10) // 时间窗口的大小为10秒
                        .minimumNumberOfCalls(1) // 在单位时间窗口内最少需要3次调用才能开始进行统计计算
                        .failureRateThreshold(50) // 在单位时间窗口内调用失败率达到50%后会启动断路器
                        .enableAutomaticTransitionFromOpenToHalfOpen() // 允许断路器自动由打开状态转换为半开状态
                        .waitDurationInOpenState(Duration.ofSeconds(2)) // 断路器打开状态转换为半开状态需要等待5秒
                        .permittedNumberOfCallsInHalfOpenState(2) // 在半开状态下允许进行正常调用的次数
                        .recordExceptions(Throwable.class) // 所有异常都当作失败来处理
                        .build();
                TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
                        .timeoutDuration(Duration.ofMillis(200))//接口200毫秒没有响应就认为失败了
                        .build();

                factory.configureDefault(id -> {
                    return new Resilience4JConfigBuilder(id)
                            .timeLimiterConfig(timeLimiterConfig)
                            .circuitBreakerConfig(circuitBreakerConfig)
                            .build();
                });
            }
        };
    }
}
