package com.example.demo.repository.order;

import com.example.demo.model.order.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Infinity97
 */
public interface PaymentRepo extends JpaRepository<Payment, String> {
}
