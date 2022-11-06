package top.shusheng007.goodsservice.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.loadbalancer.NacosLoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
//import org.springframework.core.env.Environment;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/11/6 16:23
 * @description:
 */
//@Configuration
public class OpenFeignLoadBalancerConfig {

//    @Bean
//    public ReactorLoadBalancer<ServiceInstance> randomServiceInstanceLoadBalancer(Environment environment,
//                                                                                   LoadBalancerClientFactory loadBalancerClientFactory) {
//        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
//        return new RandomLoadBalancer(
//                loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
//    }

    @Bean
    public ReactorLoadBalancer<ServiceInstance> nacosServiceInstanceLoadBalancer(Environment environment,
                                                                                 LoadBalancerClientFactory loadBalancerClientFactory,
                                                                                 NacosDiscoveryProperties nacosDiscoveryProperties) {
        String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
        return new NacosLoadBalancer(
                loadBalancerClientFactory.getLazyProvider(name, ServiceInstanceListSupplier.class), name,nacosDiscoveryProperties);
    }


}
