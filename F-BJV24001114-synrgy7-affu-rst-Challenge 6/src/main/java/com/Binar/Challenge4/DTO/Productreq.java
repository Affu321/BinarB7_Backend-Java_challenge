package com.Binar.Challenge4.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
@Data
public class Productreq {
    private UUID id;
    private String productName;
    private BigDecimal price;
    private boolean completed;
    private UUID merchantId;
}
