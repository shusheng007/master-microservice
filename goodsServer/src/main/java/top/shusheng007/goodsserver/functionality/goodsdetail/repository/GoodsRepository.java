package top.shusheng007.goodsserver.functionality.goodsdetail.repository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import top.shusheng007.goodsserver.functionality.goodsdetail.model.Book;
import top.shusheng007.goodsserver.functionality.goodsdetail.model.BookItem;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ben.Wang
 * <p>
 * Author      Ben.Wang
 * Date        2021/10/26 11:00
 * Description
 */

@Repository
public class GoodsRepository {
    private final BookRepository bookRepository;

    public GoodsRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Mono<Book> getBookById(String id){
        return bookRepository.findById(id).map(bookItem -> new Book(bookItem.getId(),bookItem.getName(),bookItem.getAuthor()));
    }

    public Flux<Book> insertAll(List<BookItem> books){
        return bookRepository.saveAll(books).map(item -> new Book(item.getId(),item.getName(),item.getAuthor()));
    }
}
