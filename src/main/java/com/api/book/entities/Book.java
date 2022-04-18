package com.api.book.entities;

public class Book {

	private String isbn;
	private String name;
	private String author;
	private String description;
	private char type;
	private double price;

	public Book() {

	}

	public Book(String isbn, String name, String author, String description, char type, double price) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.author = author;
		this.description = description;
		this.type = type;
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
