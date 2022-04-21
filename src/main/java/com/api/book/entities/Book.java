package com.api.book.entities;

import java.util.Objects;

public class Book {

	private String isbn;
	private String author;
	private String description;
	private String name;
	private String type;
	private double price;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	public Book(String isbn, String author, String description, String name, String type, double price) {
		super();
		this.isbn = isbn;
		this.author = author;
		this.description = description;
		this.name = name;
		this.type = type;
		this.price = price;
	}





	public String getIsbn() {
		return isbn;
	}

	public Book setIsbn(String isbn) {
		this.isbn = isbn;
		return this;
	}

	public double getPrice() {
		return price;
	}

	public Book setPrice(double price) {
		this.price = price;
		return this;
	}

	public String getName() {
		return name;
	}

	public Book setName(String name) {
		this.name = name;
		return this;
	}

	public String getType() {
		return type;
	}

	public Book setType(String type) {
		this.type = type;
		return this;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Book book = (Book) o;
		return isbn.equals(book.isbn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}
}
