package top.shusheng007.goodsservice.api;

import entity.BaseResponse;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.shusheng007.goodsservice.api.entity.OrderDetail;
import top.shusheng007.goodsservice.api.entity.PaymentReq;
import top.shusheng007.goodsservice.config.OpenFeignLoadBalancerConfig;

import java.util.List;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/8/9 16:06
 * @description:
 */

@FeignClient(value = "order-service",fallback = OrderServiceFeignFallback.class,configuration = {})
@LoadBalancerClient(value = "order-service",configuration = OpenFeignLoadBalancerConfig.class)
public interface OrderServiceFeign {

    @PostMapping(value = "/order/payment")
    public BaseResponse<OrderDetail> payment(@RequestBody PaymentReq paymentReq);

    @GetMapping(value = "/order/getOrders/{userId}")
    public List<OrderDetail> getOrdersByUserId(@PathVariable("userId") String userId);
}
