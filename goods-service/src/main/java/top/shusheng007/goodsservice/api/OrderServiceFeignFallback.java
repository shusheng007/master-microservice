package top.shusheng007.goodsservice.api;

import entity.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.shusheng007.goodsservice.api.entity.OrderDetail;
import top.shusheng007.goodsservice.api.entity.PaymentReq;
import utils.ResultUtil;

import java.util.List;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/11/6 19:50
 * @description:
 */

@Component
@Slf4j
public class OrderServiceFeignFallback implements OrderServiceFeign{
    @Override
    public BaseResponse<OrderDetail> payment(PaymentReq paymentReq) {
        log.info("支付fallback:{}",paymentReq.toString());
        return ResultUtil.error("支付失败");
    }

    @Override
    public List<OrderDetail> getOrdersByUserId(String userId) {
        return null;
    }
}
