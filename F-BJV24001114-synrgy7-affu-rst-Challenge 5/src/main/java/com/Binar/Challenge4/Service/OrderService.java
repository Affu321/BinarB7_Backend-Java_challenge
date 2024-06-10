package com.Binar.Challenge4.Service;


import com.Binar.Challenge4.DTO.Orderreq;
import com.Binar.Challenge4.Entity.Order;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    Map addOrder(Orderreq orderData);
    Map updateOrder(UUID orderId, Order order);
    Map deleteOrder(UUID orderId);
    Map getOrderById(UUID id);
    Map pagination(int page, int size);
    Optional<List<Order>> getAllOrder();
}
