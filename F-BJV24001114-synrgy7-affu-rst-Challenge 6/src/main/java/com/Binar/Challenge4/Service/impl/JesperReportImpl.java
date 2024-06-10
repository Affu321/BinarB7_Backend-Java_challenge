//package com.Binar.Challenge4.Service.impl;
//
//import com.Binar.Challenge4.DTO.InvoiceDTO;
//import com.Binar.Challenge4.Entity.Order;
//import com.Binar.Challenge4.Entity.OrderDetail;
//import com.Binar.Challenge4.Repository.OrderDetailRepository;
//import com.Binar.Challenge4.Repository.OrderRepository;
//import com.Binar.Challenge4.Service.JasperService;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.util.ResourceUtils;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.math.BigDecimal;
//import java.util.*;
//
//@Service
//@AllArgsConstructor
//@Slf4j
//public class JesperReportImpl implements JasperService {
//
//    private final OrderDetailRepository orderDetailRepository;
//    private final OrderRepository orderRepository;
//
//    @Override
//    public byte[] getItemReport(UUID orderId) {
//        List<InvoiceDTO> itemsInvoice = new ArrayList<>();
//        BigDecimal totalPrice = BigDecimal.ZERO;
//        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
//        List<OrderDetail> items = orderDetailRepository.findAllByOrderId(order.getId());
//
//        for (OrderDetail item : items) {
//            InvoiceDTO invoiceDTO = new InvoiceDTO();
//            invoiceDTO.setProduct_name(item.getProduct().getProduct_name());
//            invoiceDTO.setPrice(BigDecimal.valueOf(item.getProduct().getPrice().doubleValue()));
//            invoiceDTO.setQuantitiy(item.getQuantitiy());
//            BigDecimal itemTotalPrice = item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantitiy()));
//            invoiceDTO.setTotal_price(BigDecimal.valueOf(itemTotalPrice.doubleValue()));
//            totalPrice = totalPrice.add(itemTotalPrice);
//            itemsInvoice.add(invoiceDTO);
//        }
//
//        JasperReport jasperReport;
//        try {
//            jasperReport = (JasperReport) JRLoader.loadObject(ResourceUtils.getFile("classpath:Invoice.jasper"));
//        } catch (FileNotFoundException | JRException e) {
//            try {
//                File file = ResourceUtils.getFile("classpath:Invoice.jrxml");
//                jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//                JRSaver.saveObject(jasperReport, "Invoice.jasper");
//            } catch (FileNotFoundException | JRException ex) {
//                throw new RuntimeException(ex);
//            }
//        }
//
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(itemsInvoice);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("userName", order.getUser().getUsername());
//        parameters.put("orderId", order.getId().toString());
//        parameters.put("totalPrice", totalPrice); // Ensure this is BigDecimal
//
//        JasperPrint jasperPrint;
//        byte[] reportContent;
//
//        try {
//            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//            reportContent = JasperExportManager.exportReportToPdf(jasperPrint);
//        } catch (JRException e) {
//            throw new RuntimeException(e);
//        }
//        return reportContent;
//    }
//}
