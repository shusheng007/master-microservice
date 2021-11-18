package top.shusheng007.goodsserver.functionality.goodsdetail.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.shusheng007.goodsserver.functionality.goodsdetail.model.Book;

import java.util.List;

/**
 * Created by Ben.Wang
 * <p>
 * Author      Ben.Wang
 * Date        2021/10/26 11:01
 * Description
 */

public interface GoodsService {

    Mono<Book> findBookById(String id);

    Flux<Book> saveAllBooks(List<Book> books);

    Mono<String> buyBook(String bookId);

    Mono<String> getUserOrderList(String userId);
}
