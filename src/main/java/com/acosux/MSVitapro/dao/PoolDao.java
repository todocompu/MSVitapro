/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acosux.MSVitapro.dao;

import com.acosux.MSVitapro.util.Pool;
import com.acosux.MSVitapro.util.PoolTO;
import com.acosux.MSVitapro.util.dao.GenericDao;

import java.util.List;

public interface PoolDao extends GenericDao<Pool, Integer> {


    List<Pool> listDataSobrevivencia(String regDateStart , String farmcode ) throws Exception;

    List<Pool> listDataInsumos(String regDateStart , String farmcode) throws Exception;

    List<Pool> listDataPesos(String regDateStart , String farmcode) throws Exception;
    
    List<PoolTO> listPoolEdit(String regDateStart , String farmcode) throws Exception;
    

}

