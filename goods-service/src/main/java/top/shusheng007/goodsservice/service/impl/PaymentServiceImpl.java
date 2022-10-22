package top.shusheng007.goodsservice.service.impl;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.support.FeignHttpClientProperties;
import org.springframework.stereotype.Service;
import top.shusheng007.goodsservice.api.OrderServiceFeign;
import top.shusheng007.goodsservice.api.entity.OrderDetail;
import top.shusheng007.goodsservice.api.entity.PaymentReq;
import top.shusheng007.goodsservice.service.PaymentService;

import java.util.Random;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/8/9 16:31
 * @description:
 */


@Slf4j
@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private final OrderServiceFeign orderServiceFeign;

    @Timed(value = "goods.payment",description = "商品服务的支付方法耗时")
    @Override
    public String payment(String orderId) {
        OrderDetail result = orderServiceFeign.payment(PaymentReq.builder()
                .orderId(orderId)
                .build())
                .getData();

        return String.format("你已经成功购买:%s",result.getGoodsName());



//        return "ok";
    }
}
