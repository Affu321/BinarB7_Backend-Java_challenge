package com.Binar.Challenge4.Service.impl;

import com.Binar.Challenge4.DTO.Orderreq;
import com.Binar.Challenge4.Entity.Order;
import com.Binar.Challenge4.Entity.OrderDetail;
import com.Binar.Challenge4.Entity.Product;
import com.Binar.Challenge4.Entity.User;
import com.Binar.Challenge4.Exception.UnauthorizedException;
import com.Binar.Challenge4.Repository.OrderDetailRepository;
import com.Binar.Challenge4.Repository.OrderRepository;
import com.Binar.Challenge4.Repository.ProductRepository;
import com.Binar.Challenge4.Repository.UserRepository;
import com.Binar.Challenge4.Service.OrderService;
import com.Binar.Challenge4.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private Response response;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;


    @Override
    public Map addOrder(Orderreq orderData) {
        try {
            User user = userRepository.findById(orderData.getUserId())
                    .orElseThrow(() -> new UnauthorizedException("User tidak ditemukan"));

            Order order = new Order();
            order.setDestination_address(orderData.getDestinationAddress());
            order.setUser(user);
            order.setCompleted(false); // Initially set as false
            order.setOrder_time(LocalDateTime.now());
            Order savedOrder = orderRepository.save(order);

            BigDecimal totalPrice = BigDecimal.ZERO;  // Initialize total price

            for (int i = 0; i < orderData.getProductIdList().size(); i++) {
                Product product = productRepository.findById(orderData.getProductIdList().get(i))
                        .orElseThrow(() -> new UnauthorizedException("Product tidak ditemukan"));

                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(savedOrder);
                orderDetail.setProduct(product);
                orderDetail.setQuantitiy(orderData.getQuantityList().get(i));
                BigDecimal orderDetailPrice = product.getPrice().multiply(BigDecimal.valueOf(orderData.getQuantityList().get(i)));
                orderDetail.setTotal_price(orderDetailPrice);

                totalPrice = totalPrice.add(orderDetailPrice);

                orderDetailRepository.save(orderDetail);
            }
            savedOrder.setCompleted(true);
            orderRepository.save(savedOrder);

            Map<String, Object> responseData = new HashMap<>();
            responseData.put("order", savedOrder);
            responseData.put("completed", savedOrder.isCompleted());
            responseData.put("totalPrice", totalPrice);

            return response.sucsess(responseData);
        } catch (Exception e) {
            return response.eror("An error occurred while saving order.", 500);
        }
    }


    @Override
    public Map updateOrder(UUID orderId, Order order)  {
        try {
            Optional<Order> existingOrder = Optional.ofNullable(orderRepository.getByIdOrder(orderId));
            if (response.checkNull(order.getId())) {
                return response.eror("Id is required.", 402);
            }
            if (existingOrder.isPresent()) {
                Order edit = existingOrder.get();
                edit.setDestination_address(order.getDestination_address());
                return response.sucsess(orderRepository.save(edit));
            }
            return response.eror("id not found", 404);
        } catch (Exception e) {
            return response.eror(e.getMessage(), 500);
        }
    }

    @Override
    public Map deleteOrder(UUID orderId){
        try {
            Optional<Order> findOrderOptional = Optional.ofNullable(orderRepository.getByIdOrder(orderId));

            if (findOrderOptional.isPresent()) {
                Order order = findOrderOptional.get();
                orderRepository.delete(order);
                return response.sucsess("data has been deleted");

            } else {
                return response.eror("id not found", 404);
            }
        } catch (DataAccessException e) {
            return response.eror("An error occurred while deleting merchant", 500);
        }
    }

    @Override
    public Map getOrderById(UUID id) {
        Map map = new HashMap();
        Optional<Order> getId = orderRepository.findById(id);
        if (!getId.isPresent()) {
            return response.eror("id not found", 404);
        }
        return response.sucsess(getId.get());
    }

    @Override
    public Map pagination(int page, int size) {
        Pageable show_data = PageRequest.of(page,size, Sort.by("id").descending());
        Page<Order> list = orderRepository.getAllDataPage(show_data);
        return response.sucsess(list);
    }


    @Override
    public Optional<List<Order>> getAllOrder(){
        List<Order> orderList = orderRepository.findAll();
        return Optional.ofNullable(orderList.isEmpty()? null : orderList);
    }


}


