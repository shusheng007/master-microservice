package top.shusheng007.goodsserver.functionality.goodsdetail.model.api;

/**
 * Created by Ben.Wang
 * <p>
 * Author      Ben.Wang
 * Date        2021/10/27 17:20
 * Description
 */
public class PaymentReq {
    private String orderId;

    public PaymentReq(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}
