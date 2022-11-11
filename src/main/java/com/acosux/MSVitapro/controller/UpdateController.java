/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acosux.MSVitapro.controller;

import com.acosux.MSVitapro.util.Pool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import com.acosux.MSVitapro.service.PoolService;
import com.acosux.MSVitapro.util.Dates;
import com.acosux.MSVitapro.util.IntegratedPool;
import com.acosux.MSVitapro.util.PoolTO;
import com.acosux.MSVitapro.util.ProductIntegrationTO;
import com.acosux.MSVitapro.util.VariablesTO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Tabango
 */
@RestController
@RequestMapping("/")
public class UpdateController {

    @Autowired
    PoolService poolService;

    @RequestMapping(value = "/feeding/{farmcode}/{productCenter}/{regDateStart}", method = {RequestMethod.GET})
    public ResponseEntity<List<Pool>> getListPoolBYDate(
            @PathVariable("farmcode") String farmcode,
            @PathVariable("productCenter") String productCenter,
            @PathVariable("regDateStart") String regDateStart) {
        String formatoFecha = "yyyy-MM-dd HH:mm:ss";
        Long da = Long.parseLong(regDateStart);
        Date date = new Date(da);
        TimeZone timeZone = TimeZone.getTimeZone("America/Danmarkshavn");
        SimpleDateFormat formato = new SimpleDateFormat(formatoFecha);
        formato.setTimeZone(timeZone);
        String fecha = formato.format(date);
        List<Pool> respues = new ArrayList<>();
        List<PoolTO> poolTO = new ArrayList<>();
        try {
            poolTO = poolService.listDataPool(fecha, farmcode, productCenter);
            if (poolTO.size() > 0) {
                for (PoolTO item : poolTO) {
                    Pool poolItem = new Pool();
                    Map<String, Object> listados = new HashMap<String, Object>();
                    List<VariablesTO> variablesItemSobrevivencia = new ArrayList();
                    List<VariablesTO> variablesItemGramaje = new ArrayList();
                    List<VariablesTO> variablesItemGramajeEliminados = new ArrayList();
                    List<VariablesTO> variablesItemConsumo = new ArrayList();
                    List<VariablesTO> variablesItemConsumoEnd = new ArrayList();
                    List<VariablesTO> variablesItemConsumoDelete = new ArrayList();
                    List<Dates> listDates = new ArrayList();
                    poolItem.setPoolcode(item.getPoolcode());
                    poolItem.setPoolname(item.getPoolname());
                    //Aqui agrego el listado de  varaibles TO filtrado por piscina 
                    variablesItemSobrevivencia = poolService.listDataSobrevivencia(fecha, farmcode, item.getPoolcode(), productCenter);
                    variablesItemGramaje = poolService.listDataPesos(fecha, farmcode, item.getPoolcode(), productCenter);
                    variablesItemGramajeEliminados = poolService.listGraDelete(fecha, farmcode, item.getPoolcode(), productCenter);
                    variablesItemGramaje.addAll(variablesItemGramajeEliminados);
                    variablesItemGramaje.addAll(poolService.listSobreDelete(fecha, farmcode, item.getPoolcode(), productCenter));
                    variablesItemConsumo = poolService.listDataInsumos(fecha, farmcode, item.getPoolcode(), productCenter);
                    variablesItemConsumoDelete = poolService.listDataConsDelete(fecha, farmcode, item.getPoolcode(), productCenter);
                    variablesItemConsumoEnd.addAll(variablesItemConsumoDelete);
                    listDates = poolService.listDataDatesUpdates(fecha, farmcode, item.getPoolcode(), productCenter);
                    variablesItemConsumoEnd.addAll(variablesItemConsumo);
                    if (listDates.size() > 0) {
                        for (Dates itemDate : listDates) {
                            List<VariablesTO> variablesItem = poolService.listDataInsumosEnd(itemDate.getConsFecha(), farmcode, item.getPoolcode(), productCenter);
                            variablesItemConsumoEnd.addAll(variablesItem);
                        }
                    }
                    poolItem.getVariables().addAll(variablesItemSobrevivencia);
                    poolItem.getVariables().addAll(variablesItemGramaje);
                    poolItem.getVariables().addAll(variablesItemConsumoEnd);
                    respues.add(poolItem);
                }
            }
            if (respues.size() > 0) {
                return new ResponseEntity<>(respues, HttpStatus.OK);
            }
        } catch (Exception e) {
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/listIntegration/{integration}", method = {RequestMethod.GET})
    public ResponseEntity<List<IntegratedPool>> getListIntegratedPool(
            @PathVariable("integration") String integration) {
        List<IntegratedPool> listPool = new ArrayList<>();
        try {
            listPool = poolService.getListIntegratedPool(integration);
            if (listPool.size() > 0) {
                return new ResponseEntity<>(listPool, HttpStatus.OK);
            }
        } catch (Exception e) {
        }
        return new ResponseEntity<>(listPool, HttpStatus.OK);
    }

    @RequestMapping(value = "/verifiyProduct/{farmcode}/{codeIntegration}/{listAll}", method = {RequestMethod.GET})
    public ResponseEntity<List<ProductIntegrationTO>> getListProductIntegration(
            @PathVariable("farmcode") String farCode,
            @PathVariable("codeIntegration") String codeIntegration,
            @PathVariable("listAll") boolean listAll) {
        List<ProductIntegrationTO> listProductIntegration = new ArrayList<>();
        try {
            listProductIntegration = poolService.getListProductIntegration(farCode, codeIntegration, listAll);
            if (listProductIntegration.size() > 0) {
                return new ResponseEntity<>(listProductIntegration, HttpStatus.OK);
            }
        } catch (Exception e) {
        }
        return new ResponseEntity<>(listProductIntegration, HttpStatus.OK);
    }

}
