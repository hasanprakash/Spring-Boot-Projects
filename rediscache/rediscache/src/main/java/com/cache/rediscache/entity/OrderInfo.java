package com.cache.rediscache.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderInfo {
    @Id
    private int id;
    @Column
    private String name;
    @Column
    private int qty;
}
