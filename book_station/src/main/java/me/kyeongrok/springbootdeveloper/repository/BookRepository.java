package me.kyeongrok.springbootdeveloper.repository;

import me.kyeongrok.springbootdeveloper.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // 책 검색(책이름)
    List<Book> findByTitleContainingIgnoreCase(String title);

    // 책 검색(저자)
    List<Book> findByAuthorContainingIgnoreCase(String author);
}
