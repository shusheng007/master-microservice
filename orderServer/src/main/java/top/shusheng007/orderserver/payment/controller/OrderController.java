package top.shusheng007.orderserver.payment.controller;

import org.springframework.web.bind.annotation.*;
import top.shusheng007.orderserver.payment.model.OrderDetail;
import top.shusheng007.orderserver.payment.model.api.PaymentReq;
import top.shusheng007.orderserver.payment.service.OrderService;

import java.util.List;

/**
 * Created by Ben.Wang
 * <p>
 * Author      Ben.Wang
 * Date        2021/10/27 16:32
 * Description
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/payment")
    public OrderDetail payment(@RequestBody PaymentReq paymentReq){
        return orderService.paymentOrder(paymentReq.getOrderId());
    }

    @GetMapping(value = "/getOrders/{userId}")
    public List<OrderDetail> getOrdersByUserId(@PathVariable("userId") String userId){
        return orderService.getOrdersByUser(userId);
    }


}
