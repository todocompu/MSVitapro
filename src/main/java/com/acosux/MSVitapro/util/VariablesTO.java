/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.acosux.MSVitapro.util;

import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author jtabango
 * Se nombra Variables por que asi esta la informacion que nos enviaron
 */
@Entity
public class VariablesTO implements java.io.Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;
    private String code;
    private String value;
    private String units;
    private Date date;
    //@Temporal(TemporalType.TIMESTAMP)
    private Timestamp regDateTime;
    private String productCode;

    public VariablesTO(){}
    
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

    public Timestamp getRegDateTime() {
        return regDateTime;
    }

    public void setRegDateTime(Timestamp regDateTime) {
        this.regDateTime = regDateTime;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    
    
}
