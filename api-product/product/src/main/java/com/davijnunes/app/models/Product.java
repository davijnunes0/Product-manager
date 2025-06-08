package com.davijnunes.app.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_PRODUCTS")
public class Product  implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID productId;

    @Column(nullable =  false)
    private String productName;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String productDescription;

    @Column(nullable =  false)
    private BigDecimal productPrice;

    

    public UUID getProductId() {return this.productId;}

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductDescription(String productDescription){
        this.productDescription = productDescription;
    }

    public String getProductDescription() {return this.productDescription;}

    public void setProductName(String productName) {
        this.productName = productName;

    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }



}
