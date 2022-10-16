package top.shusheng007.goodsserver.functionality.goodsdetail.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Ben.Wang
 * <p>
 * Author      Ben.Wang
 * Date        2021/10/26 17:30
 * Description
 */
@Document("goodsItems")
public class BookItem {
    @Id
    private String id;

    private String name;
    private String author;

    public BookItem(String id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public BookItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
