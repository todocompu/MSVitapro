/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acosux.MSVitapro.service;

import com.acosux.MSVitapro.util.Dates;
import com.acosux.MSVitapro.util.IntegratedPool;
import com.acosux.MSVitapro.util.PoolTO;
import com.acosux.MSVitapro.util.ProductIntegrationTO;
import com.acosux.MSVitapro.util.VariablesTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Trabajo
 */
@Transactional
public interface PoolService {

    @Transactional
    List<PoolTO> listDataPool(String regDateStart, String farmcode, String productCenter) throws Exception;

    @Transactional
    List<VariablesTO> listDataSobrevivencia(String regDateStart, String farmcode, String pool, String productCenter) throws Exception;

    @Transactional
    List<VariablesTO> listDataInsumos(String regDateStart, String farmcode, String pool, String productCenter) throws Exception;

    @Transactional
    List<VariablesTO> listDataPesos(String regDateStart, String farmcode, String pool, String productCenter) throws Exception;

    @Transactional
    List<VariablesTO> listGraDelete(String regDateStart, String farmcode, String pool, String productCenter) throws Exception;

    @Transactional
    List<VariablesTO> listSobreDelete(String regDateStart, String farmcode, String pool, String productCenter) throws Exception;

    @Transactional
    List<Dates> listDataDatesUpdates(String regDateStart, String farmcode, String pool, String productCenter) throws Exception;

    @Transactional
    List<VariablesTO> listDataInsumosEnd(String consFecha, String farmcode, String pool, String productCenter) throws Exception;

    @Transactional
    List<IntegratedPool> getListIntegratedPool(String integration) throws Exception;

    @Transactional
    List<ProductIntegrationTO> getListProductIntegration(String farmCode, String codeIntegracion, boolean listAll) throws Exception;

    @Transactional
    List<VariablesTO> listDataConsDelete(String regDateStart, String farmcode, String pool, String productCenter) throws Exception;
}
