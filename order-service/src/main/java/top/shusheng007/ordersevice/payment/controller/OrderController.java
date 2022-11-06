package top.shusheng007.ordersevice.payment.controller;

import entity.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.shusheng007.ordersevice.payment.model.OrderDetail;
import top.shusheng007.ordersevice.payment.model.api.PaymentReq;
import top.shusheng007.ordersevice.payment.service.OrderService;
import utils.ResultUtil;

import java.util.List;

/**
 * Created by Ben.Wang
 * <p>
 * Author      Ben.Wang
 * Date        2021/10/27 16:32
 * Description
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;


    @PostMapping(value = "/payment")
    public BaseResponse<OrderDetail> payment(@RequestBody PaymentReq paymentReq,
                                             @RequestHeader(value = "token",required = false) String token){
        log.info("token 为：{}",token);
        return ResultUtil.ok(orderService.paymentOrder(paymentReq.getOrderId())) ;
    }

    @GetMapping(value = "/getOrders/{userId}")
    public List<OrderDetail> getOrdersByUserId(@PathVariable("userId") String userId){
        return orderService.getOrdersByUser(userId);
    }


}
