package com.api.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.dao.BookDao;
import com.api.book.entities.Book;

import com.api.book.services.BookService;

@RestController
public class BookController {

	@Autowired
	private final BookService bookService;

	
	
	
	public BookController(BookService bookService) {
		
		this.bookService = bookService;
	}

	// get all books handler
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> getBooks() {

		List<Book> list = (List<Book>) bookService.findAll();
		if (list.size() <= 0) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		return ResponseEntity.of(Optional.of(list));

	}

	// get single book handler
	@RequestMapping(value = "/books/{id}", method = RequestMethod.GET)
	public ResponseEntity<Book> getBook(@PathVariable("id") String id) {

		Book book = bookService.getBookById(id);
		if (book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}

	// create single book handler
	@RequestMapping(value = "/books", method = RequestMethod.POST)
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		Book b = null;
		try {
			b = bookService.addBook(book);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// delete single book handler
	@RequestMapping(value = "/books/{bookId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") String bookId) {
		try {
			bookService.deleteBook(bookId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// update single book handler
	@RequestMapping(value = "/books/{bookId}", method = RequestMethod.PUT)
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("bookId") String bookId) {
		try {
			bookService.updateBook(book, bookId);
			return ResponseEntity.ok().body(book);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}

	}

}
