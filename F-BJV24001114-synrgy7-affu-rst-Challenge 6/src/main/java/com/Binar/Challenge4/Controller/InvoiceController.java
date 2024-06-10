//package com.Binar.Challenge4.Controller;
//
//
//import com.Binar.Challenge4.Service.JasperService;
//import lombok.AllArgsConstructor;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.core.io.Resource;
//import org.springframework.http.ContentDisposition;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.UUID;
//
//@AllArgsConstructor
//@RestController
//@RequestMapping("/api/v1/invoice")
//public class InvoiceController {
//
//
//    private final JasperService jasperService;
//    @GetMapping("generate/invoice/{orderId}")
//    public ResponseEntity<Resource> getItemReport(@PathVariable UUID orderId) {
//
//        byte[] reportContent = jasperService.getItemReport(orderId);
//
//        ByteArrayResource resource = new ByteArrayResource(reportContent);
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .contentLength(resource.contentLength())
//                .header(HttpHeaders.CONTENT_DISPOSITION,
//                        ContentDisposition.attachment()
//                                .filename("invoice-report-" + orderId + ".pdf")
//                                .build().toString())
//                .body(resource);
//    }
////    @PostMapping("/generate/reporting/{merchantId}")
////    public ResponseEntity<Resource> generateReportingMerchant(@PathVariable UUID merchantId,
////                                                              @RequestParam String startDate,
////                                                              @RequestParam String endDate) {
////        byte[] reportContent = jasperService.generateReportingMerchant(merchantId, startDate, endDate);
////        ByteArrayResource resource = new ByteArrayResource(reportContent);
////
////        return ResponseEntity.ok()
////                .contentType(MediaType.APPLICATION_PDF)
////                .contentLength(resource.contentLength())
////                .header(HttpHeaders.CONTENT_DISPOSITION,
////                        ContentDisposition.attachment()
////                                .filename("reporting-merchant-" + merchantId + ".pdf")
////                                .build().toString())
////                .body(resource);
////    }
//}
//
//
