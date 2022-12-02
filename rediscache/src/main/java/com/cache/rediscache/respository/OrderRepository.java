package com.cache.rediscache.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cache.rediscache.entity.OrderInfo;

@Repository
public interface OrderRepository extends JpaRepository<OrderInfo, Integer> {
    
}
