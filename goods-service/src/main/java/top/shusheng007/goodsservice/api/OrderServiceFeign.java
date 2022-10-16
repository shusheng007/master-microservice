package top.shusheng007.goodsservice.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.shusheng007.goodsservice.api.entity.OrderDetail;
import top.shusheng007.goodsservice.api.entity.PaymentReq;

import java.util.List;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/8/9 16:06
 * @description:
 */

@FeignClient(value = "order-service")
public interface OrderServiceFeign {

    @PostMapping(value = "/order/payment")
    public OrderDetail payment(@RequestBody PaymentReq paymentReq);

    @GetMapping(value = "/order/getOrders/{userId}")
    public List<OrderDetail> getOrdersByUserId(@PathVariable("userId") String userId);
}
