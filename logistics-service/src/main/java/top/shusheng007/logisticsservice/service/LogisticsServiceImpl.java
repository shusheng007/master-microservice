package top.shusheng007.logisticsservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/16 22:00
 * @description:
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LogisticsServiceImpl implements LogisticsService{
    @Override
    public String deliveryGoods(String orderId) {
        //模拟慢请求
        try {
            log.info("正在备货....");
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            log.error(e.getMessage(),e);
        }
        return String.format("您的订单：[%s]已经发货，请注意查收",orderId);
    }
}
