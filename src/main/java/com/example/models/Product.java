package com.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String productName;
    private int year;
    private Double pice;
    private String url;

    public Product(){}

    public Product( String productName, int year, Double pice, String url) {
        
        this.productName = productName;
        this.year = year;
        this.pice = pice;
        this.url = url;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Double getPice() {
        return pice;
    }

    public void setPice(Double pice) {
        this.pice = pice;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", productName=" + productName + ", year=" + year + ", pice=" + pice + ", url="
                + url + "]";
    }

    
}
