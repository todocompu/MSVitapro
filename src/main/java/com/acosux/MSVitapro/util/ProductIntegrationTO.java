/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.acosux.MSVitapro.util;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author dlopez
 */
@Entity
public class ProductIntegrationTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    private String company;
    private String productCenter;
    private String productCode;
    private String productCodeIntegration;
    private String productName;
    private String productDescription;

    public ProductIntegrationTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProductCenter() {
        return productCenter;
    }

    public void setProductCenter(String productCenter) {
        this.productCenter = productCenter;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCodeIntegration() {
        return productCodeIntegration;
    }

    public void setProductCodeIntegration(String productCodeIntegration) {
        this.productCodeIntegration = productCodeIntegration;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

}
