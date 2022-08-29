/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acosux.MSVitapro.dao;

import com.acosux.MSVitapro.util.Pool;
import com.acosux.MSVitapro.util.PoolTO;
import com.acosux.MSVitapro.util.VariablesTO;
import com.acosux.MSVitapro.util.dao.GenericDaoImpl;
import com.acosux.MSVitapro.util.dao.GenericSQLDao;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Trabajo
 */
@Repository
public class PoolDaoImpl extends GenericDaoImpl<Pool, Integer> implements PoolDao {

    @Autowired
    private GenericSQLDao genericSQLDao;

    @Override
    public List<VariablesTO> listDataPesos(String regDateStart, String farmcode, String pool) throws Exception {
        String sql = "SELECT  row_number() over (partition by '' order by '') as id , 'GRA001' as code, gra_ipromedio as value,'gr' as units, gra_fecha as date,usr_fecha_inserta as regDateTime, '' as productCode FROM produccion.prd_grameaje "
                + "WHERE usr_fecha_inserta > '" + regDateStart + "' and gra_empresa ='" + farmcode + "' and  gra_piscina='" + pool + "' ORDER BY usr_fecha_inserta, gra_sector ASC, gra_piscina ASC, gra_fecha  ASC";
        return genericSQLDao.obtenerPorSql(sql, VariablesTO.class);
    }

    @Override
    public List<VariablesTO> listDataSobrevivencia(String regDateStart, String farmcode, String pool) throws Exception {
        String sql = "SELECT  row_number() over (partition by '' order by '') as id, 'SOB001'as code, gra_sobrevivencia as value, '%' as units, gra_fecha as date, usr_fecha_inserta as regDateTime, '' as productCode FROM produccion.prd_grameaje "
                + "WHERE usr_fecha_inserta > '" + regDateStart + "' and gra_empresa ='" + farmcode + "' and gra_piscina ='" + pool + "' ORDER BY usr_fecha_inserta ASC, gra_sector ASC, gra_piscina ASC, gra_fecha ASC";
        return genericSQLDao.obtenerPorSql(sql, VariablesTO.class);
    }

    @Override
    public List<VariablesTO> listDataInsumos(String regDateStart, String farmcode, String pool) throws Exception {

        String sql = "SELECT  row_number() over (partition by '' order by '') as id, 'INS001'as code, det_cantidad as value, inv_producto.med_codigo as units, "
                + "cons_fecha as date, inventario.inv_consumos.usr_fecha_inserta as regDateTime, inventario.inv_consumos_detalle.pro_codigo_principal as productCode "
                + "FROM inventario.inv_consumos INNER JOIN inventario.inv_consumos_detalle INNER JOIN inventario.inv_producto "
                + "ON inv_consumos.cons_empresa  = inv_consumos_detalle.cons_empresa and "
                + "inv_consumos.cons_periodo = inv_consumos_detalle.cons_periodo and "
                + "inv_consumos.cons_motivo = inv_consumos_detalle.cons_motivo and "
                + "inv_consumos.cons_numero = inv_consumos_detalle.cons_numero and "
                + "ON inv_consumos_detalle.pro_empresa = inv_producto.pro_empresa and "
                + "inv_consumos_detalle.pro_codigo_principal = inv_producto.pro_codigo_principal "
                
                + "WHERE inv_consumos.cons_empresa ='" + farmcode + "' and "
                + "COALESCE(usr_fecha_modifica, usr_fecha_inserta) > '" + regDateStart + "' AND "
                + "inv_consumos_detalle.pis_numero='" + pool + "' "
                
                + "ORDER BY COALESCE(usr_fecha_modifica, usr_fecha_inserta), inv_consumos_detalle.sec_codigo, inv_consumos_detalle.pis_numero, inv_consumos.cons_fecha";
        return genericSQLDao.obtenerPorSql(sql, VariablesTO.class);
    }

    @Override
    public List<PoolTO> listPoolEdit(String regDateStart, String farmcode) throws Exception {
        List<PoolTO> listPoolEditaGramaje = new ArrayList<>();
        List<PoolTO> listPoolEditaConsumos = new ArrayList<>();
        List<PoolTO> listPool = new ArrayList<>();
        String sql;
        // Pool Edit Gramaje
        sql = "SELECT DISTINCT prd_piscina.pis_numero  as poolcode, prd_piscina.pis_nombre as poolname FROM produccion.prd_grameaje "
                + "INNER JOIN produccion.prd_piscina ON "
                + "produccion.prd_grameaje.pis_empresa = produccion.prd_piscina.pis_empresa AND "
                + "produccion.prd_grameaje.pis_sector = produccion.prd_piscina.pis_sector AND "
                + "produccion.prd_grameaje.pis_numero = produccion.prd_piscina.pis_numero "
                + "WHERE prd_grameaje.usr_fecha_inserta > '" + regDateStart + "' and gra_empresa = '" + farmcode + "'";
        listPoolEditaGramaje = (genericSQLDao.obtenerPorSql(sql, PoolTO.class));
        // Pool Edit Consumos
        sql = "SELECT DISTINCT prd_piscina.pis_numero as poolcode, prd_piscina.pis_nombre as poolname FROM inventario.inv_consumos_detalle "
                + "INNER JOIN inventario.inv_consumos "
                + "ON inventario.inv_consumos_detalle.cons_empresa  = inventario.inv_consumos.cons_empresa AND "
                + "inventario.inv_consumos_detalle.cons_periodo  =inventario.inv_consumos.cons_periodo AND "
                + "inventario.inv_consumos_detalle.cons_motivo  = inventario.inv_consumos.cons_motivo AND "
                + "inventario.inv_consumos_detalle.cons_numero  = inventario.inv_consumos.cons_numero "
                + "INNER JOIN produccion.prd_piscina ON "
                + "inventario.inv_consumos_detalle.pis_empresa = produccion.prd_piscina.pis_empresa AND "
                + "inventario.inv_consumos_detalle.pis_sector = produccion.prd_piscina.pis_sector AND "
                + "inventario.inv_consumos_detalle.pis_numero = produccion.prd_piscina.pis_numero "
                + "WHERE inv_consumos.cons_empresa ='" + farmcode + "' AND COALESCE(inv_consumos.usr_fecha_modifica, inv_consumos.usr_fecha_inserta) > '" + regDateStart + "'";
        listPoolEditaConsumos = (genericSQLDao.obtenerPorSql(sql, PoolTO.class));
        listPool.addAll(listPoolEditaGramaje);
        listPool.addAll(listPoolEditaConsumos);
        listPool.stream().distinct().collect(Collectors.toList());
        return listPool;
    }

}
