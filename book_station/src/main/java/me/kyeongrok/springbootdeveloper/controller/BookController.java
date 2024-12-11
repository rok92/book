package me.kyeongrok.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.kyeongrok.springbootdeveloper.domain.Book;
import me.kyeongrok.springbootdeveloper.dto.AddBookRequest;
import me.kyeongrok.springbootdeveloper.dto.BookResponse;
import me.kyeongrok.springbootdeveloper.dto.UpdateBookRequest;
import me.kyeongrok.springbootdeveloper.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    // 전체 책 리스트 조회
    @GetMapping
    public ResponseEntity<List<BookResponse>> findAllBooks() {
        List<BookResponse> books = bookService.findAllBooks()
                .stream()
                .map(BookResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(books);
    }

    // 책 상세정보 조회
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> findBookById(@PathVariable Long id) {
        Book book = bookService.findBookById(id);
        return ResponseEntity.ok()
                .body(new BookResponse(book));
    }

    // 책 등록
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody AddBookRequest request) {
        Book saveBook = bookService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saveBook);
    }

    // 제목으로 검색
    @GetMapping("/title/{title}")
    public ResponseEntity<List<BookResponse>> findBookByTitle(@PathVariable String title) {
        List<Book> book = bookService.findBookByTitle(title);
        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<BookResponse> bookResponses = book.stream()
                .map(BookResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookResponses);
    }

    // 저자로 검색
    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookResponse>> findBookByAuthor(@PathVariable String author) {
        List<Book> book = bookService.findBookByAuthor(author);
        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<BookResponse> bookResponses = book.stream()
                .map(BookResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookResponses);
    }


    // 책 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok()
                .build();
    }

    // 책 등록정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody UpdateBookRequest request) {
        Book updateBook = bookService.updateBook(id, request);
        return ResponseEntity.ok()
                .body(updateBook);
    }
}
