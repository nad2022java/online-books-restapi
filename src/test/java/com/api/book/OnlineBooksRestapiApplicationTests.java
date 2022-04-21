package com.api.book;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import com.api.book.controller.BookController;
import com.api.book.dao.BookDao;
import com.api.book.entities.Book;
import com.api.book.services.BookService;

//@SpringBootTest
@RunWith(SpringRunner.class)
@WebMvcTest(value=BookController.class)
class OnlineBooksRestapiApplicationTests {
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void createBookTest() {
		
		BookDao dao = new BookDao();
		 int expectedsize =  dao.BOOKS.size()+1;
		Collection<Book> books = dao.BOOKS;
		 Book newBook = new Book("0426452376", "Mr.X", "About jungle boy and animals", "Jungle Book", "Comic", 150.00);
		 Boolean result =  books.add(newBook);
		 Assert.isTrue(result, "true");
		 assertNotNull(result);
	}

}
