package top.shusheng007.ordersevice.api;

import entity.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/16 22:10
 * @description:
 */

@FeignClient(value = "logistics-service" ,url = "https://xxxxx")
public interface LogisticsFeign {

    @GetMapping("/logistics/delivery")
    public BaseResponse<String> delivery(@RequestParam("orderId") String orderId);


}
