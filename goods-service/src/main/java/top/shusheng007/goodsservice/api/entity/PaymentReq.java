package top.shusheng007.goodsservice.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Created by shusheng007
 *
 * @author shusheng007
 * @date 2022/8/9 16:16
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentReq {
    private String orderId;
}
