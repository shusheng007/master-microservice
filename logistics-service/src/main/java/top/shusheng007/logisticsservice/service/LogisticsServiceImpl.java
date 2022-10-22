package top.shusheng007.logisticsservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

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
        if(new Random().nextBoolean()){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return String.format("您的订单：[%s]已经发货，请注意查收",orderId);
    }
}
