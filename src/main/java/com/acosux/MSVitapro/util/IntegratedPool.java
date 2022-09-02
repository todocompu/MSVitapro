/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.acosux.MSVitapro.util;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author dlopez
 */
@Entity
public class IntegratedPool {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;

    private String idCompany;

    private String nameCompany;

    private String idProduccionCenter;

    private String nameProduccionCenter;

    private String idPool;

    private String namePool;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(String idCompany) {
        this.idCompany = idCompany;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getIdProduccionCenter() {
        return idProduccionCenter;
    }

    public void setIdProduccionCenter(String idProduccionCenter) {
        this.idProduccionCenter = idProduccionCenter;
    }

    public String getNameProduccionCenter() {
        return nameProduccionCenter;
    }

    public void setNameProduccionCenter(String nameProduccionCenter) {
        this.nameProduccionCenter = nameProduccionCenter;
    }

    public String getIdPool() {
        return idPool;
    }

    public void setIdPool(String idPool) {
        this.idPool = idPool;
    }

    public String getNamePool() {
        return namePool;
    }

    public void setNamePool(String namePool) {
        this.namePool = namePool;
    }

}
