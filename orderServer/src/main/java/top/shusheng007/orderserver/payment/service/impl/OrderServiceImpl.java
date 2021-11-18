package top.shusheng007.orderserver.payment.service.impl;

import org.springframework.stereotype.Service;
import top.shusheng007.orderserver.payment.model.OrderDetail;
import top.shusheng007.orderserver.payment.service.OrderService;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ben.Wang
 * <p>
 * Author      Ben.Wang
 * Date        2021/10/27 16:42
 * Description
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public OrderDetail paymentOrder(String orderId) {
        return new OrderDetail(orderId,"书",50*10*10);
    }

    @Override
    public List<OrderDetail> getOrdersByUser(String userId) {
        //模拟耗时操作
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList(new OrderDetail("A1","书",50*10*10),
                new OrderDetail("A2","键盘",250*10*10));
    }
}
