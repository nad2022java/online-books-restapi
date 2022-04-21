package com.api.book.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.api.book.entities.Book;

@Component
public class BookDao {

	public static final Map<String, Collection<Book>> memory = new HashMap<>();

	public static Collection<Book> BOOKS = new ArrayList();
	
	public BookDao() {
		
		BOOKS.add(new Book("0426144376", "Jim Dennis", "Learning martial arts", "Kung Fu Master", "Comic", 200.00));
		BOOKS.add(new Book("9780446602174", "Peter David", "Story about Batman", "Batman Forever", "Fiction", 300.00));
		BOOKS.add(new Book("478046602654", "David Houston", "Story about Superman", "Swamp Thing", "Fiction", 400.00));
		BOOKS.add(new Book("54343435", "John Morrison", "Story about tigers", "Jungle King", "Fiction", 500.00));
		BOOKS.add(new Book("689876987", "Mr.X", "about nothing", "abc book", "Comic", 50.00));
		BOOKS.add(new Book("1557092281", "George Lowther", "Story about Superman", "The Adventures of Superman", "Comic",
				100.00));
	}
			

	public Collection<Book> findAll() {
		return BOOKS;
	}

	/*
	 * public List<Book> getAllBooks(){
	 * 
	 * return booksList; }
	 */
	// retrieve book by id
	public Book getBookById(String id) {

		Book book = null;
		try {
			book = BOOKS.stream().filter(b -> b.getIsbn().equals(id)).findFirst().get();
		} catch (Exception e) {
		}
		return book;
	}

	// adding the book
	public Book addBook(Book book) {

		BOOKS.add(book);
		return book;
	}

// delete book
	public void deleteBook(String bookId) {

		BOOKS = BOOKS.stream().filter(book -> !book.getIsbn().equals(bookId)).collect(Collectors.toList());
		BOOKS.removeIf(b -> b.getIsbn().equals(bookId));
	}

	public void updateBook(Book book, String bookId) {

		BOOKS.stream().filter(b -> b.getIsbn().equals(bookId)).forEach(b -> {

			b.setAuthor(book.getAuthor());
			b.setDescription(book.getDescription());
			b.setName(book.getName());
			b.setPrice(book.getPrice());
			b.setType(book.getType());
		});
	}
}
