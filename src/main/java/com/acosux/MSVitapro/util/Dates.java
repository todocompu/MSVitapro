/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.acosux.MSVitapro.util;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author dlopez
 */
@Entity
public class Dates implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "cons_fecha")
    private String consFecha;

    public Dates() {
    }

    public String getConsFecha() {
        return consFecha;
    }

    public void setConsFecha(String consFecha) {
        this.consFecha = consFecha;
    }

}
