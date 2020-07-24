package com.example.demo.repository.order;

import com.example.demo.model.order.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepo extends JpaRepository<OrderDetail, String> {
}
