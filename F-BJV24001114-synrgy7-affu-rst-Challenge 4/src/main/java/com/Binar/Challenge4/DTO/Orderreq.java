package com.Binar.Challenge4.DTO;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Orderreq {
    private UUID userId;
    private String destinationAddress;
    private List<UUID> productIdList;
    private List<Integer> quantityList;
    //testttttt
}

