package me.kyeongrok.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.kyeongrok.springbootdeveloper.domain.Book;
import me.kyeongrok.springbootdeveloper.dto.AddBookRequest;
import me.kyeongrok.springbootdeveloper.dto.UpdateBookRequest;
import me.kyeongrok.springbootdeveloper.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service // 빈으로 등록
public class BookService {

    private final BookRepository bookRepository;

    // 리스트 전체 조회
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    // 책 상세정보 조회
    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found" + id));
    }

    // 새로운 책 등록
    public Book save(AddBookRequest request) {
        return bookRepository.save(request.toEntity());
    }

    // 제목으로 검색
    public List<Book> findBookByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    // 저자로 검색
    public List<Book> findBookByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    // 책 삭제
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    // 책 정보 수정
    @Transactional
    public Book updateBook(long id, UpdateBookRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found" + id));
        book.updateBook(request.getInformation(), request.getQuantity());
        return book;
    }

}
