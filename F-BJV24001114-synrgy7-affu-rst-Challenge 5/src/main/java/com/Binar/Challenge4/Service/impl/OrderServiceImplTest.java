package com.Binar.Challenge4.Service.impl;

import com.Binar.Challenge4.DTO.Orderreq;
import com.Binar.Challenge4.Entity.Order;
import com.Binar.Challenge4.Entity.User;
import com.Binar.Challenge4.Exception.UnauthorizedException;
import com.Binar.Challenge4.Repository.OrderDetailRepository;
import com.Binar.Challenge4.Repository.OrderRepository;
import com.Binar.Challenge4.Repository.ProductRepository;
import com.Binar.Challenge4.Repository.UserRepository;
import com.Binar.Challenge4.utils.Response;


import java.util.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private Response response;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderDetailRepository orderDetailRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;
    private UUID orderId;
    private UUID userId;
    private Orderreq orderreq;

    @BeforeEach
    void setUp() {
        orderId = UUID.randomUUID();
        userId = UUID.randomUUID();

        order = new Order();
        order.setId(orderId);
        order.setDestination_address("123 Main St");

        orderreq = new Orderreq();
        orderreq.setUserId(userId);
        orderreq.setDestinationAddress("123 Main St");
        orderreq.setProductIdList(new ArrayList<>());
        orderreq.setQuantityList(new ArrayList<>());
    }

    @Test
    void testAddOrder_Success() {
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        when(response.sucsess(any())).thenReturn(Map.of("status", "success"));

        Map result = orderService.addOrder(orderreq);

        assertNotNull(result);
        assertEquals("success", result.get("status"));

        verify(userRepository, times(1)).findById(userId);
        verify(orderRepository, times(2)).save(any(Order.class));
        verify(response, times(1)).sucsess(any());
    }

    @Test
    void testAddOrder_UserNotFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        when(response.eror(anyString(), anyInt())).thenReturn(Map.of("status", "error"));

        Map result = orderService.addOrder(orderreq);

        assertNotNull(result);
        assertEquals("error", result.get("status"));

        verify(userRepository, times(1)).findById(userId);
        verify(orderRepository, never()).save(any(Order.class));
        verify(response, times(1)).eror(anyString(), anyInt());
    }

    @Test
    void testUpdateOrder_Success() {
        when(orderRepository.getByIdOrder(orderId)).thenReturn(order);
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        when(response.sucsess(any())).thenReturn(Map.of("status", "success"));

        Map result = orderService.updateOrder(orderId, order);

        assertNotNull(result);
        assertEquals("success", result.get("status"));

        verify(orderRepository, times(1)).getByIdOrder(orderId);
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(response, times(1)).sucsess(any());
    }

    @Test
    void testUpdateOrder_OrderNotFound() {
        when(orderRepository.getByIdOrder(orderId)).thenReturn(null);
        when(response.eror(anyString(), anyInt())).thenReturn(Map.of("status", "error"));

        Map result = orderService.updateOrder(orderId, order);

        assertNotNull(result);
        assertEquals("error", result.get("status"));

        verify(orderRepository, times(1)).getByIdOrder(orderId);
        verify(orderRepository, never()).save(any(Order.class));
        verify(response, times(1)).eror(anyString(), anyInt());
    }

    @Test
    void testDeleteOrder_Success() {
        when(orderRepository.getByIdOrder(orderId)).thenReturn(order);
        doNothing().when(orderRepository).delete(any(Order.class));
        when(response.sucsess(anyString())).thenReturn(Map.of("status", "success"));

        Map result = orderService.deleteOrder(orderId);

        assertNotNull(result);
        assertEquals("success", result.get("status"));

        verify(orderRepository, times(1)).getByIdOrder(orderId);
        verify(orderRepository, times(1)).delete(any(Order.class));
        verify(response, times(1)).sucsess(anyString());
    }

    @Test
    void testDeleteOrder_OrderNotFound() {
        when(orderRepository.getByIdOrder(orderId)).thenReturn(null);
        when(response.eror(anyString(), anyInt())).thenReturn(Map.of("status", "error"));

        Map result = orderService.deleteOrder(orderId);

        assertNotNull(result);
        assertEquals("error", result.get("status"));

        verify(orderRepository, times(1)).getByIdOrder(orderId);
        verify(orderRepository, never()).delete(any(Order.class));
        verify(response, times(1)).eror(anyString(), anyInt());
    }

    @Test
    void testGetOrderById_Success() {
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(response.sucsess(any())).thenReturn(Map.of("status", "success"));

        Map result = orderService.getOrderById(orderId);

        assertNotNull(result);
        assertEquals("success", result.get("status"));

        verify(orderRepository, times(1)).findById(orderId);
        verify(response, times(1)).sucsess(any());
    }

    @Test
    void testGetOrderById_OrderNotFound() {
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());
        when(response.eror(anyString(), anyInt())).thenReturn(Map.of("status", "error"));

        Map result = orderService.getOrderById(orderId);

        assertNotNull(result);
        assertEquals("error", result.get("status"));

        verify(orderRepository, times(1)).findById(orderId);
        verify(response, times(1)).eror(anyString(), anyInt());
    }
}
