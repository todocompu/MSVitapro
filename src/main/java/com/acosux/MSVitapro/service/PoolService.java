/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acosux.MSVitapro.service;

import com.acosux.MSVitapro.util.IntegratedPool;
import com.acosux.MSVitapro.util.PoolTO;
import com.acosux.MSVitapro.util.VariablesTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Trabajo
 */
@Transactional
public interface PoolService {

    @Transactional
    List<PoolTO> listDataPool(String regDateStart, String farmcode) throws Exception;

    @Transactional
    List<VariablesTO> listDataSobrevivencia(String regDateStart, String farmcode, String pool) throws Exception;

    @Transactional
    List<VariablesTO> listDataInsumos(String regDateStart, String farmcode, String pool) throws Exception;

    @Transactional
    List<VariablesTO> listDataPesos(String regDateStart, String farmcode, String pool) throws Exception;

    @Transactional
    List<IntegratedPool> getListIntegratedPool(String integration) throws Exception;

}
