package top.shusheng007.goodsservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.shusheng007.goodsservice.api.OrderServiceFeign;
import top.shusheng007.goodsservice.api.entity.OrderDetail;
import top.shusheng007.goodsservice.api.entity.PaymentReq;
import top.shusheng007.goodsservice.service.PaymentService;

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

    @Override
    public String payment(String orderId) {
        OrderDetail result = orderServiceFeign.payment(PaymentReq.builder()
                .orderId(orderId)
                .build());
        return String.format("你已经成功购买:%s",result.getGoodsName());
    }
}
