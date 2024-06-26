package com.Binar.Challenge4.Repository;

import com.Binar.Challenge4.Entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
        List<OrderDetail> findAllByOrderId(UUID orderId);
}

