package me.kyeongrok.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.kyeongrok.springbootdeveloper.domain.Book;
import me.kyeongrok.springbootdeveloper.dto.AddBookRequest;
import me.kyeongrok.springbootdeveloper.dto.UpdateBookRequest;
import me.kyeongrok.springbootdeveloper.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void mockMvcSetup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        bookRepository.deleteAll();
    }

    @DisplayName("GET findAllBooks: 전체 책 목록을 조회합니다.")
    @Test
    public void findAllBooks() throws Exception {
        // given
        final String url = "/api/books";
        final String title = "title";
        final String author = "author";
        final String information = "information";
        final int quantity = 30;

        // when
        bookRepository.save(Book.builder()
                .title(title)
                .author(author)
                .information(information)
                .quantity(quantity)
                .build());

        final ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].information").value(information))
                .andExpect(jsonPath("$[0].author").value(author))
                .andExpect(jsonPath("$[0].title").value(title))
                .andExpect(jsonPath("$[0].quantity").value(quantity));
    }

    @DisplayName("GET findBookById: 책 상세정보를 조회합니다.")
    @Test
    public void findBookById() throws Exception {
        // given
        final String url = "/api/books/{id}";
        final String title = "title";
        final String author = "author";
        final String information = "information";
        final int quantity = 30;

        Book saveBook = bookRepository.save(Book.builder()
                .title(title)
                .author(author)
                .information(information)
                .quantity(quantity)
                .build());

        // when
        final ResultActions resultActions = mockMvc.perform(get(url, saveBook.getId()));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.information").value(information))
                .andExpect(jsonPath("$.author").value(author))
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.quantity").value(quantity));
    }

    @DisplayName("POST addBook: 새로운 책을 등록합니다.")
    @Test
    public void addBook() throws Exception {
        // given
        final String url = "/api/books";
        final String title = "title";
        final String author = "author";
        final String information = "information";
        final int quantity = 30;

        final AddBookRequest userReq = new AddBookRequest(title, author, information, quantity);

        final String requestBody = objectMapper.writeValueAsString(userReq);

        // when
        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        // then
        resultActions.andExpect(status().isCreated());

        List<Book> books = bookRepository.findAll();

        assertThat(books.size()).isEqualTo(1);
        assertThat(books.get(0).getTitle()).isEqualTo(title);
    }

    @DisplayName("GET findBookByTitle: 도서명으로 검색 합니다.")
    @Test
    public void findBookByTitle() throws Exception {
        // given
        final String url = "/api/books/title/{title}";
        final String title = "title";
        final String author = "author";
        final String information = "information";
        final int quantity = 30;

        Book saveBook = bookRepository.save(Book.builder()
                .title(title)
                .author(author)
                .information(information)
                .quantity(quantity)
                .build());

        // when
        ResultActions resultActions = mockMvc.perform(get(url, saveBook.getTitle()));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].information").value(information))
                .andExpect(jsonPath("$[0].author").value(author))
                .andExpect(jsonPath("$[0].title").value(title))
                .andExpect(jsonPath("$[0].quantity").value(quantity));

    }

    @DisplayName("GET findBookByAuthor: 저자명으로 검색 합니다.")
    @Test
    public void findBookByAuthor() throws Exception {
        // given
        final String url = "/api/books/author/{author}";
        final String title = "title";
        final String author = "author";
        final String information = "information";
        final int quantity = 30;

        Book saveBook = bookRepository.save(Book.builder()
                .title(title)
                .author(author)
                .information(information)
                .quantity(quantity)
                .build());

        // when
        ResultActions resultActions = mockMvc.perform(get(url, saveBook.getAuthor()));

        // then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].information").value(information))
                .andExpect(jsonPath("$[0].author").value(author))
                .andExpect(jsonPath("$[0].title").value(title))
                .andExpect(jsonPath("$[0].quantity").value(quantity));

    }


    @DisplayName("DELETE deleteBook: 책을 삭제합니다.")
    @Test
    public void deleteBook() throws Exception {
        // given
        final String url = "/api/books/{id}";
        final String title = "title";
        final String author = "author";
        final String information = "information";
        final int quantity = 30;

        Book saveBook = bookRepository.save(Book.builder()
                .title(title)
                .author(author)
                .information(information)
                .quantity(quantity)
                .build());

        // when
        mockMvc.perform(delete(url, saveBook.getId()))
                .andExpect(status().isOk());

        // then
        List<Book> books = bookRepository.findAll();
        assertThat(books.isEmpty());
    }

    @DisplayName("PUT updateBook: 책정보를 수정합니다.")
    @Test
    public void updateBook() throws Exception {
        // given
        final String url = "/api/books/{id}";
        final String title = "title";
        final String author = "author";
        final String information = "information";
        final int quantity = 30;

        Book saveBook = bookRepository.save(Book.builder()
                .title(title)
                .author(author)
                .information(information)
                .quantity(quantity)
                .build());

        final String newInformation = "newInformation";
        final int newQuantity = 90;

        UpdateBookRequest update = new UpdateBookRequest(newInformation, newQuantity);

        // when
        ResultActions resultActions = mockMvc.perform(put(url, saveBook.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(update)));

        // then
        resultActions.andExpect(status().isOk());

        Book book = bookRepository.findById(saveBook.getId()).get();

        assertThat(book.getInformation()).isEqualTo(newInformation);
        assertThat(book.getQuantity()).isEqualTo(newQuantity);
    }


}