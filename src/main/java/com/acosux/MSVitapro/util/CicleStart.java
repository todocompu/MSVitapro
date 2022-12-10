/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.acosux.MSVitapro.util;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author denyslopez
 */
@Entity
public class CicleStart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String date;
    private BigDecimal quantity;
    private BigDecimal desity;
    private BigDecimal desityPlus;
    private BigDecimal weight;
    private String laboratory;
    private String nauplius;
    private String runNumber;

    public CicleStart() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDesity() {
        return desity;
    }

    public void setDesity(BigDecimal desity) {
        this.desity = desity;
    }

    public BigDecimal getDesityPlus() {
        return desityPlus;
    }

    public void setDesityPlus(BigDecimal desityPlus) {
        this.desityPlus = desityPlus;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(String laboratory) {
        this.laboratory = laboratory;
    }

    public String getNauplius() {
        return nauplius;
    }

    public void setNauplius(String nauplius) {
        this.nauplius = nauplius;
    }

    public String getRunNumber() {
        return runNumber;
    }

    public void setRunNumber(String runNumber) {
        this.runNumber = runNumber;
    }

}
