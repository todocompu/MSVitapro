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

/**
 * @author Trabajo
 */
@Service
public class PoolServiceImpl implements PoolService {

    @Autowired
    private PoolDao poolDao;


    @Override
    public List<Pool> listDataPool(String regDateStart, String farmcode) throws Exception {
        poolDao.listPoolEdit(regDateStart, farmcode);
        return null;
    } 

}
