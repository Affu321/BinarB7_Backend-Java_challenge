package com.Binar.Challenge4.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceDTO {

    private String product_name;
    private BigDecimal price;
    private int quantitiy;
    private BigDecimal total_price;
}

