package top.shusheng007.goodsservice.controller;

import entity.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.shusheng007.goodsservice.api.entity.PaymentReq;
import top.shusheng007.goodsservice.service.PaymentService;
import utils.ResultUtil;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/8/9 16:36
 * @description:
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/goods")
public class GoodsController {
    private final PaymentService paymentService;

    @PostMapping(value = "/makeOrder")
    public BaseResponse<String> makeOrder(@RequestBody PaymentReq paymentReq){
        return ResultUtil.ok(paymentService.payment(paymentReq.getOrderId()));
    }

    @GetMapping(value = "/testLimit")
    public BaseResponse<String> testLimit(){
        return ResultUtil.ok("testLimit");
    }

    @GetMapping("/checkGoods")
    public BaseResponse<String> getGoods(@RequestParam("goodsId") String goodsId){
        log.info("开始商品调用:{}",goodsId);
        if("delay".equals(goodsId)){
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                log.error("睡眠失败",e);
            }
        }
        log.info("结束商品调用:{}",goodsId);

        return ResultUtil.ok("ok");
    }

    @GetMapping("/vip")
    public String vipHandle(@RequestHeader("vipKey")String vip){
        return String.format("vip处理，header:%s",vip);
    }
}
