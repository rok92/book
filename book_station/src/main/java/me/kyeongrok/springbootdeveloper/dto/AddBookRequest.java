package me.kyeongrok.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kyeongrok.springbootdeveloper.domain.Book;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddBookRequest {

    private String title;
    private String author;
    private String information;
    private int quantity;

    public Book toEntity() {
        return Book.builder()
                .title(title)
                .author(author)
                .information(information)
                .quantity(quantity)
                .build();
    }
}
