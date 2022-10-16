package top.shusheng007.ordersevice.payment.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
        logisticsFeign.delivery(orderId);
        return new OrderDetail(orderId,"书",50*10*10);
    }

    @Override
    public List<OrderDetail> getOrdersByUser(String userId) {
        //模拟耗时操作
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage(),e);
        }
        return Arrays.asList(
                new OrderDetail("A1","书",50*10*10),
                new OrderDetail("A2","键盘",250*10*10));
    }
}
