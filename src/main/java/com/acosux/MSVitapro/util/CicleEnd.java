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
public class CicleEnd implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String date;
    private String biomass;
    private BigDecimal weight;
    private BigDecimal balerBiomass;
    private BigDecimal balerWeight;
    private String runNumber;

    public CicleEnd() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBiomass() {
        return biomass;
    }

    public void setBiomass(String biomass) {
        this.biomass = biomass;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getBalerBiomass() {
        return balerBiomass;
    }

    public void setBalerBiomass(BigDecimal balerBiomass) {
        this.balerBiomass = balerBiomass;
    }

    public BigDecimal getBalerWeight() {
        return balerWeight;
    }

    public void setBalerWeight(BigDecimal balerWeight) {
        this.balerWeight = balerWeight;
    }

    public String getRunNumber() {
        return runNumber;
    }

    public void setRunNumber(String runNumber) {
        this.runNumber = runNumber;
    }

}
