/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.acosux.MSVitapro.util;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author jtabango
 */
public class PoolTO {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private String poolcode;
}
