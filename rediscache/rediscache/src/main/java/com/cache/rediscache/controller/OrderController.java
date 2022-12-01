package com.cache.rediscache.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cache.rediscache.entity.OrderInfo;
import com.cache.rediscache.respository.OrderRepository;

@RestController
@RequestMapping("/order")
@EnableCaching
public class OrderController {
    @Autowired
    private OrderRepository repository;

    @PostMapping
    public OrderInfo saveOrder(@RequestBody OrderInfo order) {
        return repository.save(order);
    }
    @GetMapping
    public List<OrderInfo> getAllOrders() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(value = "name", key = "#id")
    public OrderInfo getOrderById(@PathVariable int id) {
        System.out.println("result from database");
        return repository.findById(id).orElse(null);
    }
}
