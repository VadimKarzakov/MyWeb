package com.example.vda2.repository;

import com.example.vda2.model.OrderNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderNotificationRepository extends JpaRepository<OrderNotification, Long> {

}
