package top.shusheng007.ordersevice.payment.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import top.shusheng007.ordersevice.api.LogisticsFeign;
import top.shusheng007.ordersevice.payment.model.OrderDetail;
import top.shusheng007.ordersevice.payment.service.OrderService;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ben.Wang
 * <p>
 * Author      Ben.Wang
 * Date        2021/10/27 16:42
 * Description
 */

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private final LogisticsFeign logisticsFeign;

    @Override
    public OrderDetail paymentOrder(String orderId) {
//        String delivery = logisticsFeign.delivery(orderId).getData();
        if(Math.random()>0.5d){
            try {
                Thread.sleep(5*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        log.info("创建订单：{}", orderId);
        return OrderDetail.builder()
                .orderId(orderId)
                .goodsName("设计模式")
                .price(50)
                .deliveryState("delivery")
                .build();
    }

    @Override
    public List<OrderDetail> getOrdersByUser(String userId) {
        //模拟耗时操作
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage(), e);
        }
        return Arrays.asList(
                new OrderDetail("A1", "书", 50 * 10 * 10, ""),
                new OrderDetail("A2", "键盘", 250 * 10 * 10, ""));
    }

    public void printScoreLevel(List<Integer> scores) {
        for (Integer score : scores) {
            if (score >= 90) {
                System.out.println("A");
                continue;
            }
            if (score >= 80) {
                System.out.println("B");
                continue;
            }
            if (score >= 70) {
                System.out.println("C");
                continue;
            }
            if (score >= 60) {
                System.out.println("D");
                continue;
            }
            System.out.println("E");
        }
    }

    public void multi(int[] a, int[] b) {


        //转成十进制
    }
}
