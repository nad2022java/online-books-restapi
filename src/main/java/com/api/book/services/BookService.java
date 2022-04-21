package com.api.book.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.book.dao.BookDao;
import com.api.book.entities.Book;

@Service
public class BookService {

	private final BookDao bookDao;

	public BookService(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	// retreiving all books
	public Collection<Book> findAll() {
		return bookDao.findAll();
	}

	// retrieve book by id
	public Book getBookById(String id) {

		return bookDao.getBookById(id);
	}

	// adding the book
	public Book addBook(Book book) {

		return bookDao.addBook(book);
	}

	// delete book
	public void deleteBook(String bookId) {
		bookDao.deleteBook(bookId);
	}

	public void updateBook(Book book, String bookId) {

		bookDao.updateBook(book, bookId);
	}
}
