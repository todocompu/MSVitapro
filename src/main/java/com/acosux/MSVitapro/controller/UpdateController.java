/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acosux.MSVitapro.controller;

import com.acosux.MSVitapro.util.RespuestaWebTO;
import com.acosux.MSVitapro.util.Pool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import com.acosux.MSVitapro.service.PoolService;
import com.acosux.MSVitapro.util.PoolTO;
import com.acosux.MSVitapro.util.VariablesTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tabango
 */
@RestController
@RequestMapping("/")
public class UpdateController {

    @Autowired
    PoolService poolService;

    @RequestMapping(value = "/feeding/{farmcode}/{regDateStart}", method = {RequestMethod.GET})
    public List<Pool> SisActualizacionesTO(@PathVariable("farmcode") String farmcode,
            @PathVariable("regDateStart") String regDateStart) {
        List<Pool> respues = new ArrayList<>();
        List<PoolTO> poolTO = new ArrayList<>();
        try {
            poolTO = poolService.listDataPool(regDateStart, farmcode);
            for (PoolTO item : poolTO) {
                Pool poolItem = new Pool();
                Map<String, Object> listados = new HashMap<String, Object>();
                List<VariablesTO> variablesItemSobrevivencia = new ArrayList();
                List<VariablesTO> variablesItemGramaje = new ArrayList();
                List<VariablesTO> variablesItemConsumo = new ArrayList();
                poolItem.setPoolcode(item.getPoolcode());
                poolItem.setPoolname(item.getPoolname());
                //Aqui agrego el listado de  varaibles TO filtrado por piscina 
                variablesItemSobrevivencia = poolService.listDataSobrevivencia(regDateStart, farmcode, item.getPoolcode());
                variablesItemGramaje = poolService.listDataPesos(regDateStart, farmcode, item.getPoolcode());
                variablesItemConsumo = poolService.listDataInsumos(regDateStart, farmcode, item.getPoolcode());

                poolItem.getVariables().addAll(variablesItemSobrevivencia);
                poolItem.getVariables().addAll(variablesItemGramaje);
                poolItem.getVariables().addAll(variablesItemConsumo);
                respues.add(poolItem);
            }
        } catch (Exception e) {
        }
        return respues;
    }

}
