//package com.examplechallengebinarc4.challengebinarc4.Testing;
//
//import com.examplechallengebinarc4.challengebinarc4.Entity.Merchant;
//import com.examplechallengebinarc4.challengebinarc4.Entity.Order;
//import com.examplechallengebinarc4.challengebinarc4.Entity.Product;
//import com.examplechallengebinarc4.challengebinarc4.Service.OrderService;
//import com.examplechallengebinarc4.challengebinarc4.Service.ProductService;
//import junit.framework.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.math.BigDecimal;
//import java.util.Map;
//import java.util.UUID;
//
//import static org.junit.Assert.assertEquals;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@RunWith(SpringRunner.class)
//public class TestingProduct {
//
//    @Autowired
//    private ProductService productService;
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    public void addProduct() {
//        Product save = new Product();
//        save.setProduct_name("Mie Iblis");
//        save.setPrice(new BigDecimal("15000.00"));
//        save.setCompleted(true);
//
//        Map map = productService.addProduct(save);
//        int responseCode = (Integer) map.get("status");
//        assertEquals(200, responseCode);
//    }
//    @Test
//    public void addProductWithExistingMerchant() {
//        Merchant merchant = new Merchant();
//        merchant.setId(UUID.fromString("84aa2daf-e2ee-4710-8af2-a7e0d17178de"));
//
//        Product product = new Product();
//        product.setProduct_name("Mie Iblis");
//        product.setPrice(new BigDecimal("15000.00"));
//        product.setCompleted(true);
//        product.setMerchant(merchant);
//
//        Map map = productService.addProduct(product);
//        int responseCode = (Integer) map.get("status");
//        assertEquals(200, responseCode);
//    }
//
//    @Test
//    public void pagination(){
//        Map map = productService.pagination(0,10);
//        System.out.println(map);
//        int responseCode = (Integer) map.get("status");
//        Assert.assertEquals(200, responseCode);
//    }
//}
