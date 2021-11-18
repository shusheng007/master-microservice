package top.shusheng007.orderserver.payment.model;

/**
 * Created by Ben.Wang
 * <p>
 * Author      Ben.Wang
 * Date        2021/10/27 16:34
 * Description
 */
public class OrderDetail {

    private String orderId;
    private String goodsType;
    private int price;

    public OrderDetail(String orderId, String goodsType, int price) {
        this.orderId = orderId;
        this.goodsType = goodsType;
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderId='" + orderId + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", price=" + price +
                '}';
    }
}
