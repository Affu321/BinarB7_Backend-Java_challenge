package com.Binar.Challenge4.Service.impl;


import com.Binar.Challenge4.Entity.Merchant;
import com.Binar.Challenge4.Repository.MerchantRepository;
import com.Binar.Challenge4.Service.MerchantService;
import com.Binar.Challenge4.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import java.util.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private Response response;

    @Override
    @Async
    public CompletableFuture<Optional<List<Merchant>>> getAllMerchant() {
        List<Merchant> merchantList = merchantRepository.findAll();
        return CompletableFuture.completedFuture(Optional.ofNullable(merchantList.isEmpty() ? null : merchantList));
    }

    @Override
    @Async
    public CompletableFuture<Map<String, Object>> insertMerchant(Merchant merchant) {
        try {
            if (response.checkNull(merchant.getMerchant_name()) || StringUtils.isEmpty(merchant.getMerchant_name())) {
                return CompletableFuture.completedFuture(response.eror("MerchantName is required.", 402));
            }
            merchant.setOpen(merchant.isOpen());
            Merchant savedMerchant = merchantRepository.save(merchant);
            return CompletableFuture.completedFuture(response.sucsess(savedMerchant));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(response.eror("An error occurred while saving merchant.", 500));
        }
    }

    @Override
    @Async
    public CompletableFuture<Map<String, Object>> updateMerchant(UUID merchantId, Merchant merchant) {
        try {
            Optional<Merchant> existingMerchant = Optional.ofNullable(merchantRepository.getByIdMerchant(merchantId));
            if (!existingMerchant.isPresent()) {
                return CompletableFuture.completedFuture(response.eror("id not found", 404));
            }
            Merchant edit = existingMerchant.get();
            edit.setMerchant_name(merchant.getMerchant_name());
            edit.setMerchant_location(merchant.getMerchant_location());
            edit.setOpen(merchant.isOpen());

            Merchant updatedMerchant = merchantRepository.save(edit);
            return CompletableFuture.completedFuture(response.sucsess(updatedMerchant));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(response.eror(e.getMessage(), 500));
        }
    }

    @Override
    @Async
    public CompletableFuture<Map<String, Object>> deleteMerchant(UUID merchantId) {
        try {
            Optional<Merchant> findMerchantOptional = Optional.ofNullable(merchantRepository.getByIdMerchant(merchantId));
            if (!findMerchantOptional.isPresent()) {
                return CompletableFuture.completedFuture(response.eror("id not found", 404));
            }
            Merchant merchant = findMerchantOptional.get();
            merchantRepository.delete(merchant);
            return CompletableFuture.completedFuture(response.sucsess("data berhasil dihapus"));
        } catch (DataAccessException e) {
            return CompletableFuture.completedFuture(response.eror("An error occurred while deleting merchant", 500));
        }
    }

    @Override
    @Async
    public CompletableFuture<Map<String, Object>> getMerchantById(UUID id) {
        Optional<Merchant> getId = merchantRepository.findById(id);
        if (!getId.isPresent()) {
            return CompletableFuture.completedFuture(response.eror("id not found", 404));
        }
        return CompletableFuture.completedFuture(response.sucsess(getId.get()));
    }

    @Override
    @Async
    public CompletableFuture<Map<String, Object>> merchantPagination(int page, int size) {
        Pageable showMerchant = PageRequest.of(page, size, Sort.by("merchantname").descending());
        Page<Merchant> list = merchantRepository.getAllDataPage(showMerchant);
        return CompletableFuture.completedFuture(response.sucsess(list));
    }
}
