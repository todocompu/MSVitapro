/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.acosux.MSVitapro.util;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author jtabango
 */
public class Pool {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private String poolcode;

    private String poolname;

    private List<VariablesTO> variables = new ArrayList<VariablesTO>();

    public String getPoolcode() {
        return poolcode;
    }

    public void setPoolcode(String poolcode) {
        this.poolcode = poolcode;
    }

    public String getPoolname() {
        return poolname;
    }

    public void setPoolname(String poolname) {
        this.poolname = poolname;
    }

    public List<VariablesTO> getVariables() {
        return variables;
    }

    public void setVariables(List<VariablesTO> variables) {
        this.variables = variables;
    }

}
