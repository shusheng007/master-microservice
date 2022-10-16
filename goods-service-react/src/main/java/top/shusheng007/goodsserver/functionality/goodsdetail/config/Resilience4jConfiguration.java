package top.shusheng007.goodsserver.functionality.goodsdetail.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnErrorEvent;
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnSuccessEvent;
import io.github.resilience4j.core.EventConsumer;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Ben.Wang
 * <p>
 * Author      Ben.Wang
 * Date        2021/10/28 19:38
 * Description
 */

@Configuration
public class Resilience4jConfiguration {

    private Logger log = LoggerFactory.getLogger(Resilience4jConfiguration.class);

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer(){
        return new Customizer<ReactiveResilience4JCircuitBreakerFactory>() {
            @Override
            public void customize(ReactiveResilience4JCircuitBreakerFactory factory) {
                 factory.configureDefault(new Function<String, Resilience4JConfigBuilder.Resilience4JCircuitBreakerConfiguration>() {
                    @Override
                    public Resilience4JConfigBuilder.Resilience4JCircuitBreakerConfiguration apply(String id) {
                        return new Resilience4JConfigBuilder(id)
                                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                                .timeLimiterConfig(TimeLimiterConfig.ofDefaults())
                                .build();
                    }
                });
            }
        };
    }


    public Customizer<ReactiveResilience4JCircuitBreakerFactory> slowCustomizer(){
        return new Customizer<ReactiveResilience4JCircuitBreakerFactory>() {
            @Override
            public void customize(ReactiveResilience4JCircuitBreakerFactory factory) {
                factory.configure(new Consumer<Resilience4JConfigBuilder>() {
                    @Override
                    public void accept(Resilience4JConfigBuilder builder) {
                        builder.circuitBreakerConfig(CircuitBreakerConfig.custom()
                                .slidingWindowSize(3).build())
                                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(1)).build());
                    }
                },"slow");
                factory.addCircuitBreakerCustomizer(new Customizer<CircuitBreaker>() {
                    @Override
                    public void customize(CircuitBreaker circuitBreaker) {
                        circuitBreaker.getEventPublisher().onSuccess(new EventConsumer<CircuitBreakerOnSuccessEvent>() {
                            @Override
                            public void consumeEvent(CircuitBreakerOnSuccessEvent event) {
                                log.debug(event.toString());
                            }
                        }).onError(new EventConsumer<CircuitBreakerOnErrorEvent>() {
                            @Override
                            public void consumeEvent(CircuitBreakerOnErrorEvent event) {
                                log.debug(event.toString());
                            }
                        });
                    }
                });
            }
        };
    }






}
