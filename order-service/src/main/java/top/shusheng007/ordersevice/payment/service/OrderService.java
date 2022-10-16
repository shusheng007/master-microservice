package top.shusheng007.ordersevice.payment.service;

import top.shusheng007.ordersevice.payment.model.OrderDetail;

import java.util.List;

/**
 * Created by Ben.Wang
 * <p>
 * Author      Ben.Wang
 * Date        2021/10/27 16:42
 * Description
 */

public interface OrderService {

    OrderDetail paymentOrder(String orderId);

    List<OrderDetail> getOrdersByUser(String userId);
}
