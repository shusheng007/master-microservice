package top.shusheng007.goodsserver.functionality.goodsdetail.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.shusheng007.goodsserver.functionality.goodsdetail.model.Book;
import top.shusheng007.goodsserver.functionality.goodsdetail.service.GoodsService;

import java.util.List;

/**
 * Created by Ben.Wang
 * <p>
 * Author      Ben.Wang
 * Date        2021/10/22 17:09
 * Description
 */

@RestController()
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsService goodsService;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }


    //从MongoDB中查询数据
    @GetMapping(value = "/book/{bookId}")
    public Mono<Book> searchBookById(@PathVariable String bookId){
        return goodsService.findBookById(bookId);
    }

    //向MongoDB中存储数据
    @PostMapping(value = "/book/store")
    public Flux<Book> saveAllBooks(@RequestBody List<Book> books){
        return goodsService.saveAllBooks(books);
    }

    //调用order-service微服务，通过服务发现获取到地址信息后，经过本地负载均衡调用不同的实例
    @GetMapping("/buyBook/{bookId}")
    public Mono<String> buyBook(@PathVariable String bookId){
        return goodsService.buyBook(bookId);
    }

    @GetMapping("/userOrders/{userId}")
    public Mono<String> getUserOrders(@PathVariable String userId){
        return goodsService.getUserOrderList(userId);
    }

}
