/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.acosux.MSVitapro.util;

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author jtabango
 * Se nombra Variables por que asi esta la informacion que nos enviaron
 */
public class VariablesTO {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private String code;
    
    private String value;
    
    private String units;
    
    private Date date;
    
    private Date regDateTime;
    
    private String productCode;
    
    
}
