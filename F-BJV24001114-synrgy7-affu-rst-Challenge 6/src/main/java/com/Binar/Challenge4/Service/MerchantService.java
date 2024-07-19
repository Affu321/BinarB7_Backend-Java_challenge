package com.Binar.Challenge4.Service;



import com.Binar.Challenge4.Entity.Merchant;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface MerchantService {
    CompletableFuture<Optional<List<Merchant>>> getAllMerchant();
    CompletableFuture<Map<String, Object>> insertMerchant(Merchant merchant);
    CompletableFuture<Map<String, Object>> updateMerchant(UUID merchantId, Merchant merchant);
    CompletableFuture<Map<String, Object>> deleteMerchant(UUID merchantId);
    CompletableFuture<Map<String, Object>> getMerchantById(UUID id);
    CompletableFuture<Map<String, Object>> merchantPagination(int page, int size);
}


