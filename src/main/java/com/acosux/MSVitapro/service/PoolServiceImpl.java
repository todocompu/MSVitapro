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

/**
 * @author Trabajo
 */
@Service
public class PoolServiceImpl implements PoolService {

    @Autowired
    private PoolDao poolDao;

    @Override
    public List<Pool> listDataPool(String regDateStart, String farmcode) throws Exception {
        List<Pool> pool = new ArrayList<>();
        Pool poolItem = new Pool();
        List<VariablesTO> variablesItem;
        // Aqui falta ponerle el nombre de la piscina D: solo lo deje hasta el codigo pilas
        List<PoolTO> poolTO = poolDao.listPoolEdit(regDateStart, farmcode);
        for (int x = 0; x <= poolTO.size(); x++) {
            
            poolItem.setPoolcode(poolTO.get(x).getPoolcode());
            
            
            //Aqui agrego el listado de  varaibles TO filtrado por piscina 
            variablesItem = poolDao.listDataSobrevivencia(regDateStart, farmcode, poolTO.get(x).getPoolcode());
            poolItem.setVariables(variablesItem);
            
            variablesItem = poolDao.listDataPesos(regDateStart, farmcode, poolTO.get(x).getPoolcode());
            poolItem.setVariables(variablesItem);
            
            variablesItem = poolDao.listDataInsumos(regDateStart, farmcode, poolTO.get(x).getPoolcode());
            poolItem.setVariables(variablesItem);
            
            pool.add(poolItem);
        }
        return pool;
    }

}
