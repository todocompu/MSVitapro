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

/**
 * @author Tabango
 */
@RestController
@RequestMapping("/")
public class UpdateController {

    @Autowired
    PoolService poolService;

    @RequestMapping(value = "/feeding/{farmcode}/{regDateStart}", method = {RequestMethod.GET})
    public RespuestaWebTO SisActualizacionesTO(@PathVariable("farmcode") String farmcode,
            @PathVariable("regDateStart") String regDateStart
    ) {
        RespuestaWebTO resp = new RespuestaWebTO();
        resp.setEstadoOperacion(RespuestaWebTO.EstadoOperacionEnum.ADVERTENCIA.getValor());
        try {
            List<Pool> respues = poolService.listDataPool(regDateStart, farmcode);
            resp.setEstadoOperacion(RespuestaWebTO.EstadoOperacionEnum.EXITO.getValor());
            resp.setExtraInfo(respues);
        } catch (Exception e) {
            resp.setEstadoOperacion(RespuestaWebTO.EstadoOperacionEnum.ERROR.getValor());
            resp.setOperacionMensaje(e.getMessage());
        }
        return resp;
    }

}
