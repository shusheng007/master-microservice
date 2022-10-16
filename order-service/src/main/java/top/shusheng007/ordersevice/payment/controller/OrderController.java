package top.shusheng007.ordersevice.payment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.shusheng007.ordersevice.payment.model.OrderDetail;
import top.shusheng007.ordersevice.payment.model.api.PaymentReq;
import top.shusheng007.ordersevice.payment.service.OrderService;

import java.util.List;

/**
 * Created by Ben.Wang
 * <p>
 * Author      Ben.Wang
 * Date        2021/10/27 16:32
 * Description
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;


    @PostMapping(value = "/payment")
    public OrderDetail payment(@RequestBody PaymentReq paymentReq){
        return orderService.paymentOrder(paymentReq.getOrderId());
    }

    @GetMapping(value = "/getOrders/{userId}")
    public List<OrderDetail> getOrdersByUserId(@PathVariable("userId") String userId){
        return orderService.getOrdersByUser(userId);
    }


}
