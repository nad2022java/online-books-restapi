package com.api.book.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public String addBooks(@RequestBody final Collection<String> bookIds,
                           @RequestParam(name = "orderId", required = false) final String orderId) {
        return orderService.addBooks(orderId, bookIds);
    }

    @PostMapping("/checkout")
    public String checkout(@RequestParam(name = "orderId") final String orderId) {
        return orderService.checkout(orderId);
    }

    @DeleteMapping
    public String removeBooks(@RequestBody final Collection<String> bookIds,
                              @RequestParam(name = "orderId") final String orderId) {
        return orderService.deleteBooks(orderId, bookIds);
    }
}
