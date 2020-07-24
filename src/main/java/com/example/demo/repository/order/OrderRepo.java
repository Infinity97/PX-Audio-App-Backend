package com.example.demo.repository.order;

import com.example.demo.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Infinity97
 */
public interface OrderRepo extends JpaRepository<Order, String> {
}
