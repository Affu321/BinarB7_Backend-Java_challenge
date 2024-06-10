package com.Binar.Challenge4.Entity;
import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
@Table(name = "product")
public class Product extends AbstrackFood implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "product_name", length = 255)
    private String product_name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "completed", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean completed;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Merchant merchant;
}