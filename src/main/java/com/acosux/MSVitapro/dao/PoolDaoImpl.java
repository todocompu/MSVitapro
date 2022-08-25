/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acosux.MSVitapro.dao;

import com.acosux.MSVitapro.util.Pool;
import com.acosux.MSVitapro.util.PoolTO;
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
    public List<Pool> listDataSobrevivencia(String regDateStart, String farmcode) throws Exception {
        String sql = "SELECT 'VAR001'as code, gra_ipromedio,'gr' as units, gra_fecha as date,usr_fecha_inserta as regDateTime, '' as productCode FROM produccion.prd_grameaje\n"
                + "WHERE usr_fecha_inserta > '" + regDateStart + "' and gra_empresa ='" + farmcode + "ORDER BY usr_fecha_inserta ASC";
        return genericSQLDao.obtenerPorSql(sql, Pool.class);
    }

    @Override
    public List<Pool> listDataInsumos(String regDateStart, String farmcode) throws Exception {
        String sql = "SELECT DISTINCT act_version FROM actualizaciones.act_actualizaciones ORDER BY act_version DESC";
        return genericSQLDao.obtenerPorSql(sql, Pool.class);
    }

    @Override
    public List<Pool> listDataPesos(String regDateStart, String farmcode) throws Exception {
        String sql = "SELECT DISTINCT act_version FROM actualizaciones.act_actualizaciones ORDER BY act_version DESC";
        return genericSQLDao.obtenerPorSql(sql, Pool.class);
    }

    @Override
    public List<PoolTO> listPoolEdit(String regDateStart, String farmcode) throws Exception {
        List<PoolTO> listPoolEditaGramaje = new ArrayList<>();
        List<PoolTO> listPoolEditaConsumos = new ArrayList<>();
        List<PoolTO> listPool = new ArrayList<>();
        String sql;
        // Pool Edit Gramaje
        sql = "SELECT DISTINCT pis_numero  as poolcode FROM produccion.prd_grameaje "
                + "WHERE usr_fecha_inserta > '" + regDateStart + "' and gra_empresa = '" + farmcode+"'";
        listPoolEditaGramaje = (genericSQLDao.obtenerPorSql(sql, PoolTO.class));
        // Pool Edit Consumos
        sql = "SELECT DISTINCT pis_numero FROM inventario.inv_consumos_detalle "
                + "	INNER JOIN inventario.inv_consumos "
                + "ON inventario.inv_consumos_detalle.cons_empresa  = inventario.inv_consumos.cons_empresa and "
                + "inventario.inv_consumos_detalle.cons_periodo  =inventario.inv_consumos.cons_periodo and "
                + "inventario.inv_consumos_detalle.cons_motivo  = inventario.inv_consumos.cons_motivo and "
                + "inventario.inv_consumos_detalle.cons_numero  = inventario.inv_consumos.cons_numero  and "
                + "inventario.inv_consumos.cons_empresa ='"+farmcode+"' and usr_fecha_inserta > '"+regDateStart+"'";
        listPoolEditaConsumos = (genericSQLDao.obtenerPorSql(sql, PoolTO.class));
        listPool.addAll(listPoolEditaGramaje);
        listPool.addAll(listPoolEditaConsumos);
        listPool.stream().distinct().collect(Collectors.toList());
        return listPool;
    }

}
