package com.challenge.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.restaurant.model.Order;

import java.util.List;

/**
 * Created by sachin on 4/7/19.
 */
public interface OrderRepository extends JpaRepository<Order,Long> {

    Order findOrderById(long id);
}
