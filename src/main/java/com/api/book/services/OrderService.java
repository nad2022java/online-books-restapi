package com.api.book.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.api.book.entities.Book;

@Service
public class OrderService {

    private final BookService bookService;

    public static final Map<String, Collection<Book>> ORDERS = new HashMap<>();

    public OrderService(BookService bookService) {
        this.bookService = bookService;
    }

    public String addBooks(@Nullable String orderId, Collection<String> bookIds) {

        if (orderId == null || "".equals(orderId.trim())) {
            orderId = UUID.randomUUID().toString();
        }

        final Collection<Book> books = ORDERS.getOrDefault(orderId, new HashSet<>());

        final Collection<Book> toBeAdded = bookService
                .findAll()
                .stream()
                .filter(e -> bookIds.contains(e.getIsbn()))
                .collect(Collectors.toSet());

        books.addAll(toBeAdded);

        ORDERS.put(orderId, books);

        return orderId;

    }

    public String deleteBooks(@NonNull String orderId, Collection<String> bookIds) {
        final Collection<Book> orderedBooks = ORDERS.get(orderId);
        orderedBooks.removeIf(e -> bookIds.contains(e.getIsbn()));
        ORDERS.put(orderId, orderedBooks);
        return orderId;
    }

    public String checkout(@NonNull String orderId) {
        final Collection<Book> books = ORDERS.get(orderId);
        final double sum = books.stream().mapToDouble(Book::getPrice).sum();
        return String.valueOf(sum);
    }
}
