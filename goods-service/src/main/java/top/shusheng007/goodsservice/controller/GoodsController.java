package top.shusheng007.goodsservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.shusheng007.goodsservice.api.entity.PaymentReq;
import top.shusheng007.goodsservice.service.PaymentService;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/8/9 16:36
 * @description:
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/goods")
public class GoodsController {
    private final PaymentService paymentService;

    @PostMapping(value = "/makeOrder")
    public String makeOrder(@RequestBody PaymentReq paymentReq){
        return paymentService.payment(paymentReq.getOrderId());
    }


    @GetMapping("/vip")
    public String vipHandle(@RequestHeader("vipKey")String vip){
        return String.format("vip处理，header:%s",vip);
    }
}
