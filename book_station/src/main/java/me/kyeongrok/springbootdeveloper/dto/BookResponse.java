package me.kyeongrok.springbootdeveloper.dto;

import lombok.Getter;
import me.kyeongrok.springbootdeveloper.domain.Book;

@Getter
public class BookResponse {
    private final long id;
    private final String title;
    private final String author;
    private final String information;
    private final int quantity;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.information = book.getInformation();
        this.quantity = book.getQuantity();
    }
}
