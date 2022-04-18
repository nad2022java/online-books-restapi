package com.api.book.entities;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class BookService {

	 private static List<Book> booksList = new ArrayList();

	static {

		booksList.add(new Book("1557092281", "The Adventures of Superman", "George Lowther", "Story about Superman", 'C',
				100.00));
		booksList.add(new Book("0426144376", "Kung Fu Master", "Jim Dennis", "Learning martial arts", 'F', 150.00));
		booksList.add(new Book("9780446602174", "Batman Forever", "Peter David", "Story about Batman", 'C', 150.00));
		booksList.add(new Book("9780446602174", "Swamp Thing", "David Houston", " Swamp Thing movie.", 'F', 200.00));

	}
	
	//retreiving all books
	public  List<Book> getAllBooks(){
		
		return booksList;
	}
	
	// retrieve book by id
	public Book getBookById(String id) {
		
		Book book  = null;
		try {
		book = booksList.stream().filter(b->b.getIsbn().equals(id)).findFirst().get();
		}catch(Exception e) {}
		return book;
	}
	
	
	//adding the book
public Book addBook(Book book) {
		
	booksList.add(book);
		return book;
	}

// delete book
public void deleteBook(String bookId) {
	
	booksList = booksList.stream().filter(book->!book.getIsbn().equals(bookId)).collect(Collectors.toList());
	booksList.removeIf(b->b.getIsbn().equals(bookId));
}

public void updateBook(Book book, String bookId) {
	
 	booksList.stream().filter(b->b.getIsbn().equals(bookId)).forEach(b->{
		
	b.setAuthor(book.getAuthor());
	b.setDescription(book.getDescription());
	b.setName(book.getName());
	b.setPrice(book.getPrice());
	b.setType(book.getType());
});
}

}
