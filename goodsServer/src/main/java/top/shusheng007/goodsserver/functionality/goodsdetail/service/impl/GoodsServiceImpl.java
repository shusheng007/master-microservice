package top.shusheng007.goodsserver.functionality.goodsdetail.service.impl;

import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.shusheng007.goodsserver.functionality.goodsdetail.model.Book;
import top.shusheng007.goodsserver.functionality.goodsdetail.model.BookItem;
import top.shusheng007.goodsserver.functionality.goodsdetail.model.api.PaymentReq;
import top.shusheng007.goodsserver.functionality.goodsdetail.repository.GoodsRepository;
import top.shusheng007.goodsserver.functionality.goodsdetail.service.GoodsService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ben.Wang
 * <p>
 * Author      Ben.Wang
 * Date        2021/10/26 11:02
 * Description
 */
@Service
public class GoodsServiceImpl implements GoodsService {

//    @Value("${services.name}")
//    private List<String> services;

    private final GoodsRepository goodsRepository;
    private final WebClient.Builder webClientBuilder;
    private final ReactiveCircuitBreakerFactory cbFactory;

    public GoodsServiceImpl(GoodsRepository goodsRepository, WebClient.Builder webClientBuilder, ReactiveCircuitBreakerFactory cbFactory) {
        this.goodsRepository = goodsRepository;
        this.webClientBuilder = webClientBuilder;
        this.cbFactory = cbFactory;
    }

    @Override
    public Mono<Book> findBookById(String id) {
        return goodsRepository.getBookById(id);
    }

    @Override
    public Flux<Book> saveAllBooks(List<Book> books) {
        return goodsRepository.insertAll(books.stream().map(
                        book -> new BookItem(book.getId(), book.getName(), book.getAuthor()))
                .collect(Collectors.toList()));
    }

    @Override
    public Mono<String> buyBook(String bookId) {
        return webClientBuilder.build().post()
                .uri("http://order-service/order/payment")
                .body(Mono.just(new PaymentReq(bookId)), PaymentReq.class)
                .retrieve()
                .bodyToMono(String.class);
    }

    @Override
    public Mono<String> getUserOrderList(String userId) {
        return webClientBuilder.build().get()
                .uri("http://order-service/order/getOrders/{userId}",userId)
                .retrieve()
                .bodyToMono(String.class)
                .transform(it->cbFactory.create("slow").run(it,throwable -> Mono.just("fallback")));
    }
}
