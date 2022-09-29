/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acosux.MSVitapro.dao;

import com.acosux.MSVitapro.util.IntegratedPool;
import com.acosux.MSVitapro.util.Pool;
import com.acosux.MSVitapro.util.PoolTO;
import com.acosux.MSVitapro.util.ProductIntegrationTO;
import com.acosux.MSVitapro.util.VariablesTO;
import com.acosux.MSVitapro.util.dao.GenericDao;

import java.util.List;

public interface PoolDao extends GenericDao<Pool, Integer> {

    List<VariablesTO> listDataSobrevivencia(String regDateStart, String farmcode, String pool, String productCenter) throws Exception;

    List<VariablesTO> listDataInsumos(String regDateStart, String farmcode, String pool, String productCenter) throws Exception;

    List<VariablesTO> listDataPesos(String regDateStart, String farmcode, String pool, String productCenter) throws Exception;

    List<PoolTO> listPoolEdit(String regDateStart, String farmcode, String productCenter) throws Exception;

    List<IntegratedPool> getListIntegratedPool(String integration) throws Exception;

    List<ProductIntegrationTO> getListProductIntegration(String farmCode, String codeIntegracion, boolean listAll) throws Exception;
}
