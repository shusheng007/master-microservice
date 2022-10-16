package top.shusheng007.goodsserver.functionality.goodsdetail.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import top.shusheng007.goodsserver.functionality.goodsdetail.model.BookItem;

/**
 * Created by Ben.Wang
 * <p>
 * Author      Ben.Wang
 * Date        2021/10/26 17:35
 * Description
 */
@Repository
public interface BookRepository extends ReactiveMongoRepository<BookItem,String> {

//    @Query("{id:'?0'}")
//    Mono<BookItem> findById(String id);

}
