/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.acosux.MSVitapro.util;

import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author jtabango
 * Se nombra Variables por que asi esta la informacion que nos enviaron
 */
@Entity
public class VariablesTO implements java.io.Serializable {
    
    @Id
    private Integer id;
    
    private String code;
    
    private String value;
    
    private String units;
    
    private Date date;
    
    private Date regDateTime;
    
    private String productCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
      
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getRegDateTime() {
        return regDateTime;
    }

    public void setRegDateTime(Date regDateTime) {
        this.regDateTime = regDateTime;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    
    
}
