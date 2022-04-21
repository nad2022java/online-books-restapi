package com.api.book.entities;

import java.util.Collection;

public class Order {

    private String id;
    private Collection<Book> books;

    public String getId() {
        return id;
    }

    public Order setId(String id) {
        this.id = id;
        return this;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public Order setBooks(Collection<Book> books) {
        this.books = books;
        return this;
    }
}
