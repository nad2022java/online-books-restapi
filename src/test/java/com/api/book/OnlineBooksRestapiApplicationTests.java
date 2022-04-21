package com.api.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.api.book.dao.BookDao;
import com.api.book.entities.Book;
import com.api.book.services.BookService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.api.book.dao.BookDao;
import com.api.book.entities.Book;
import com.api.book.services.BookService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;




@SpringBootTest(classes = OnlineBooksRestapiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class OnlineBooksRestapiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;
    
    
    private Collection<Book> getAllBooks() {
        final Collection<Book> BOOKS = new ArrayList<>();
        BOOKS.add(new Book("0426144376", "Jim Dennis", "Learning martial arts", "Kung Fu Master", "Comic", 200.00));
        BOOKS.add(new Book("9780446602174", "Peter David", "Story about Batman", "Batman Forever", "Fiction", 300.00));
        BOOKS.add(new Book("478046602654", "David Houston", "Story about Superman", "Swamp Thing", "Fiction", 400.00));
        BOOKS.add(new Book("54343435", "John Morrison", "Story about tigers", "Jungle King", "Fiction", 500.00));
        BOOKS.add(new Book("689876987", "Mr.X", "about nothing", "abc book", "Comic", 50.00));
        BOOKS.add(new Book("1557092281", "George Lowther", "Story about Superman", "The Adventures of Superman", "Comic", 100.00));

        return BOOKS;
    }

    
    

    @Test
    public void createBookTest() throws Exception {

        when(bookService.findAll()).thenReturn(getAllBooks());

        final MvcResult mvcResult = mockMvc.perform(
                get("/books").accept(APPLICATION_JSON)
        ).andReturn();

        verify(bookService).findAll();

        assertEquals(APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType());

        final Collection<Book> books = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(6, books.size());

    }
    
    @Test
    public void getBooksTest() throws Exception {

        when(bookService.findAll()).thenReturn(BookDao.BOOKS);

        final MvcResult mvcResult = mockMvc.perform(
                get("/books").accept(APPLICATION_JSON)
        ).andReturn();

        verify(bookService).findAll();

        assertEquals(APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType());

        final Collection<Book> books = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(6, books.size());
    }
    
    @Test
    public void getBooksByIdTest() throws Exception {

        when(bookService.getBookById(anyString())).thenReturn(BookDao.BOOKS.stream().findAny().get());

        final MvcResult mvcResult = mockMvc.perform(
                get("/books/112").accept(APPLICATION_JSON)
        ).andReturn();

        verify(bookService).getBookById(anyString());

        assertEquals(APPLICATION_JSON_VALUE, mvcResult.getResponse().getContentType());

        final Book book = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertNotNull(book);
    }
    @Test
    public void postBookTest() throws Exception {

        when(bookService.addBook(any())).thenReturn(BookDao.BOOKS.stream().findFirst().get());

        final MvcResult mvcResult = mockMvc.perform(
                post("/books")
                        .contentType(APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(BookDao.BOOKS.stream().findFirst().get()))
        ).andReturn();

        assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
    }

    
    @Test
    public void deleteBookTest() throws Exception {

        doNothing().when(bookService).deleteBook(anyString());

        final MvcResult mvcResult = mockMvc.perform(
                delete("/books/7766")
                        .contentType(APPLICATION_JSON)
        ).andReturn();

        assertEquals(HttpStatus.NO_CONTENT.value(), mvcResult.getResponse().getStatus());
    }
    
    @Test
    public void updateBookTest() throws Exception {

        doNothing().when(bookService).updateBook(any(), anyString());

        final MvcResult mvcResult = mockMvc.perform(
                put("/books/7766")
                        .contentType(APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(BookDao.BOOKS.stream().findFirst().get()))
        ).andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals("0426144376", BookDao.BOOKS.stream().findFirst().get().getIsbn());
    }
    
    @Test
    public void getBooksByIdTest404() throws Exception {

        when(bookService.getBookById(anyString())).thenReturn(null);

        final MvcResult mvcResult = mockMvc.perform(
                get("/books/112")
        ).andReturn();

        verify(bookService).getBookById(anyString());
        assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
    }
    
    @Test
    public void getBooksTest404() throws Exception {

        when(bookService.findAll()).thenReturn(Collections.emptyList());

        final MvcResult mvcResult = mockMvc.perform(
                get("/books")
        ).andReturn();

        verify(bookService).findAll();

        assertEquals(HttpStatus.NOT_FOUND.value(), mvcResult.getResponse().getStatus());
    }

   
}

