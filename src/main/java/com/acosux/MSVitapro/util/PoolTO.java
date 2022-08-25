/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.acosux.MSVitapro.util;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 *
 * @author jtabango
 */
@Entity
public class PoolTO {
    @Id
    private String poolcode;

     public PoolTO() {
    }

    public String getPoolcode() {
        return poolcode;
    }

   
    public void setPoolcode(String poolcode) {
        this.poolcode = poolcode;
    }
    
}
