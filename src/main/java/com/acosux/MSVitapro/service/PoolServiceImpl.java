/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acosux.MSVitapro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.acosux.MSVitapro.dao.PoolDao;
import com.acosux.MSVitapro.util.CicleEnd;
import com.acosux.MSVitapro.util.CicleStart;
import com.acosux.MSVitapro.util.Dates;
import com.acosux.MSVitapro.util.IntegratedPool;
import com.acosux.MSVitapro.util.PoolTO;
import com.acosux.MSVitapro.util.ProductIntegrationTO;
import com.acosux.MSVitapro.util.VariablesTO;

/**
 * @author Trabajo
 */
@Service
public class PoolServiceImpl implements PoolService {

    @Autowired
    private PoolDao poolDao;

    @Override
    public List<PoolTO> listDataPool(String regDateStart, String farmcode, String productCenter, String pool) throws Exception {
        List<PoolTO> poolTO = poolDao.listPoolEdit(regDateStart, farmcode, productCenter, pool);
        return poolTO;
    }

    @Override
    public List<VariablesTO> listDataSobrevivencia(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        return poolDao.listDataSobrevivencia(regDateStart, farmcode, pool, productCenter);
    }

    @Override
    public List<VariablesTO> listDataInsumos(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        return poolDao.listDataInsumos(regDateStart, farmcode, pool, productCenter);
    }

    @Override
    public List<VariablesTO> listDataPesos(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        return poolDao.listDataPesos(regDateStart, farmcode, pool, productCenter);
    }

    @Override
    public List<VariablesTO> listGraDelete(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        return poolDao.listGraDelete(regDateStart, farmcode, pool, productCenter);
    }

    @Override
    public List<VariablesTO> listSobreDelete(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        return poolDao.listSobreDelete(regDateStart, farmcode, pool, productCenter);
    }

    @Override
    public List<Dates> listDataDatesUpdates(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        return poolDao.listDataDatesUpdates(regDateStart, farmcode, pool, productCenter);
    }

    @Override
    public List<VariablesTO> listDataInsumosEnd(String consFecha, String farmcode, String pool, String productCenter) throws Exception {
        return poolDao.listDataInsumosEnd(consFecha, farmcode, pool, productCenter);
    }

    @Override
    public List<IntegratedPool> getListIntegratedPool(String integration) throws Exception {
        return poolDao.getListIntegratedPool(integration);
    }

    @Override
    public List<ProductIntegrationTO> getListProductIntegration(String farmCode, String codeIntegracion, boolean listAll) throws Exception {
        return poolDao.getListProductIntegration(farmCode, codeIntegracion, listAll);
    }

    @Override
    public List<VariablesTO> listDataConsDelete(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        return poolDao.listDataConsDelete(regDateStart, farmcode, pool, productCenter);
    }

    @Override
    public CicleStart dataCicleStart(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        return poolDao.dataCicleStart(regDateStart, farmcode, pool, productCenter);
    }

    @Override
    public CicleEnd dataCicleEnd(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        return poolDao.dataCicleEnd(regDateStart, farmcode, pool, productCenter);
    }

}
