package com.Binar.Challenge4.Controller;


import com.Binar.Challenge4.Entity.Merchant;
import com.Binar.Challenge4.Service.MerchantService;
import com.Binar.Challenge4.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/v1/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    public Response response;

    @GetMapping(value = {"/all-merchant", "/all-merchant/"})
    public DeferredResult<ResponseEntity<Map>> getListMerchant() {
        DeferredResult<ResponseEntity<Map>> output = new DeferredResult<>();
        merchantService.getAllMerchant().thenAccept(result -> {
            output.setResult(new ResponseEntity<>(response.sucsess(result), HttpStatus.OK));
        });
        return output;
    }

    @PostMapping(value = {"/save", "/save/"})
    public DeferredResult<ResponseEntity<Map>> saveMerchant(@RequestBody Merchant request) {
        DeferredResult<ResponseEntity<Map>> output = new DeferredResult<>();
        merchantService.insertMerchant(request).thenAccept(result -> {
            output.setResult(new ResponseEntity<>(result, HttpStatus.OK));
        });
        return output;
    }

    @PutMapping(value = {"/update/{merchantId}", "/update/{merchantId}/"})
    public DeferredResult<ResponseEntity<Map>> updateMerchant(@RequestBody Merchant request, @PathVariable("merchantId") UUID merchantId) {
        DeferredResult<ResponseEntity<Map>> output = new DeferredResult<>();
        merchantService.updateMerchant(merchantId, request).thenAccept(result -> {
            output.setResult(new ResponseEntity<>(response.sucsess(result), HttpStatus.OK));
        });
        return output;
    }

    @DeleteMapping(value = {"/delete/{merchantId}", "/delete/{merchantId}/"})
    public DeferredResult<ResponseEntity<Map>> deleteMerchant(@PathVariable("merchantId") UUID merchantId) {
        DeferredResult<ResponseEntity<Map>> output = new DeferredResult<>();
        merchantService.deleteMerchant(merchantId).thenAccept(result -> {
            output.setResult(new ResponseEntity<>(result, HttpStatus.OK));
        });
        return output;
    }

    @GetMapping(value = {"/get/{merchantId}", "/get/{merchantId}/"})
    public DeferredResult<ResponseEntity<Map>> getById(@PathVariable("merchantId") UUID merchantId) {
        DeferredResult<ResponseEntity<Map>> output = new DeferredResult<>();
        merchantService.getMerchantById(merchantId).thenAccept(result -> {
            output.setResult(new ResponseEntity<>(result, HttpStatus.OK));
        });
        return output;
    }
}
