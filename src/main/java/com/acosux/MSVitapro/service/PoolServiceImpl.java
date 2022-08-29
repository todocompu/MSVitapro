/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acosux.MSVitapro.service;

import com.acosux.MSVitapro.util.Pool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.acosux.MSVitapro.dao.PoolDao;
import com.acosux.MSVitapro.util.PoolTO;
import com.acosux.MSVitapro.util.VariablesTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Trabajo
 */
@Service
public class PoolServiceImpl implements PoolService {

    @Autowired
    private PoolDao poolDao;

    @Override
    public List<PoolTO> listDataPool(String regDateStart, String farmcode) throws Exception {
        List<PoolTO> poolTO = poolDao.listPoolEdit(regDateStart, farmcode);
        return poolTO;
    }

    @Override
    public List<VariablesTO> listDataSobrevivencia(String regDateStart, String farmcode, String pool) throws Exception {
        return poolDao.listDataSobrevivencia(regDateStart, farmcode, pool);
    }

    @Override
    public List<VariablesTO> listDataInsumos(String regDateStart, String farmcode, String pool) throws Exception {
        return poolDao.listDataInsumos(regDateStart, farmcode, pool);
    }

    @Override
    public List<VariablesTO> listDataPesos(String regDateStart, String farmcode, String pool) throws Exception {
        return poolDao.listDataPesos(regDateStart, farmcode, pool);
    }

}
