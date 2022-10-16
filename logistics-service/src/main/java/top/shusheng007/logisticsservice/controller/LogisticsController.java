package top.shusheng007.logisticsservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.shusheng007.logisticsservice.service.LogisticsService;

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
    public String delivery(@RequestParam("orderId") String orderId){
        return logisticsService.deliveryGoods(orderId);
    }
}
