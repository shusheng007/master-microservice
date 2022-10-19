package top.shusheng007.logisticsservice.controller;

import entity.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.web.bind.annotation.*;
import top.shusheng007.logisticsservice.service.LogisticsService;
import utils.ResultUtil;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/16 18:23
 * @description:
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/logistics")
public class LogisticsController {
    private final LogisticsService logisticsService;

    @GetMapping("/delivery")
    public BaseResponse<String> delivery(@RequestParam("orderId") String orderId){
        return ResultUtil.ok(logisticsService.deliveryGoods(orderId));
    }
}
