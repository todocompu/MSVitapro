/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acosux.MSVitapro.dao;

import com.acosux.MSVitapro.util.Dates;
import com.acosux.MSVitapro.util.IntegratedPool;
import com.acosux.MSVitapro.util.Pool;
import com.acosux.MSVitapro.util.PoolTO;
import com.acosux.MSVitapro.util.VariablesTO;
import com.acosux.MSVitapro.util.dao.GenericDaoImpl;
import com.acosux.MSVitapro.util.dao.GenericSQLDao;
import com.acosux.MSVitapro.util.ProductIntegrationTO;
import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Trabajo
 */
@Repository
public class PoolDaoImpl extends GenericDaoImpl<Pool, Integer> implements PoolDao {

    @Autowired
    private GenericSQLDao genericSQLDao;

    @Override
    public List<VariablesTO> listDataPesos(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        String sql = "SELECT * FROM inventario.fun_list_gra_detail ('" + farmcode + "','" + regDateStart + "','" + pool + "','" + productCenter + "')";
        return genericSQLDao.obtenerPorSql(sql, VariablesTO.class);
    }

    @Override
    public List<VariablesTO> listGraDelete(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        String sql = "SELECT * FROM inventario.fun_list_gra_delete_detail('" + farmcode + "','" + regDateStart + "','" + pool + "','" + productCenter + "')";
        return genericSQLDao.obtenerPorSql(sql, VariablesTO.class);
    }

    @Override
    public List<VariablesTO> listDataSobrevivencia(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        String sql = "SELECT * FROM inventario.fun_list_sur_detail('" + farmcode + "','" + regDateStart + "','" + pool + "','" + productCenter + "')";
        return genericSQLDao.obtenerPorSql(sql, VariablesTO.class);
    }

    @Override
    public List<VariablesTO> listSobreDelete(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        String sql = "SELECT * FROM inventario.fun_list_sur_delete_detail('" + farmcode + "','" + regDateStart + "','" + pool + "','" + productCenter + "')";
        return genericSQLDao.obtenerPorSql(sql, VariablesTO.class);
    }

    @Override
    public List<VariablesTO> listDataInsumos(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        String sql = "SELECT * FROM inventario.fun_consumos_detail('" + farmcode + "','" + regDateStart + "','" + pool + "','" + productCenter + "')";
        return genericSQLDao.obtenerPorSql(sql, VariablesTO.class);
    }

    @Override
    public List<VariablesTO> listDataConsDelete(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        String sql = "SELECT * FROM inventario.fun_consumos_eliminados('" + farmcode + "','" + regDateStart + "','" + pool + "','" + productCenter + "')";
        return genericSQLDao.obtenerPorSql(sql, VariablesTO.class);
    }

    @Override
    public List<Dates> listDataDatesUpdates(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        String sql = "SELECT * FROM inventario.cons_fecha('" + farmcode + "','" + regDateStart + "','" + pool + "','" + productCenter + "')";
        return genericSQLDao.obtenerPorSql(sql, Dates.class);
    }

    @Override
    public List<VariablesTO> listDataInsumosEnd(String consFecha, String farmcode, String pool, String productCenter) throws Exception {

        String sql = "SELECT * from inventario.fun_list_data_insumos_end('" + farmcode + "','" + consFecha + "','" + pool + "','" + productCenter + "')";
        return genericSQLDao.obtenerPorSql(sql, VariablesTO.class);
    }

    @Override
    public List<PoolTO> listPoolEdit(String regDateStart, String farmcode, String productCenter, String pool) throws Exception {
        List<PoolTO> listPoolEditaGramaje;
        List<PoolTO> listPoolDeleteGramaje;
        List<PoolTO> listPoolEditaConsumos;
        List<PoolTO> listoPoolUpdateConsumos;
        List<PoolTO> listPoolDeleteConsumos;
        List<PoolTO> listPool = new ArrayList<>();
        String sql;
        String filPool = pool == null || pool.equals("null") ? null : "'" + pool + "'";
        // Pool Edit Gramaje
        sql = "SELECT * FROM produccion.fun_list_pool_gramaje ('" + farmcode + "', '" + regDateStart + "', '" + productCenter + "', " + filPool + ")";
        listPoolEditaGramaje = (genericSQLDao.obtenerPorSql(sql, PoolTO.class));

        sql = "SELECT * FROM produccion.fun_list_pool_edita_gramaje('" + farmcode + "', '" + regDateStart + "', '" + productCenter + "', " + filPool + ")";
        listPoolDeleteGramaje = (genericSQLDao.obtenerPorSql(sql, PoolTO.class));
        // Pool Edit Consumos
        sql = "SELECT * FROM inventario.fun_list_pool_consumo ('" + farmcode + "', '" + regDateStart + "', '" + productCenter + "', " + filPool + ")";
        listPoolEditaConsumos = (genericSQLDao.obtenerPorSql(sql, PoolTO.class));

        sql = "SELECT * FROM inventario.fun_list_pool_consumo_update('" + farmcode + "', '" + regDateStart + "', '" + productCenter + "', " + filPool + ")";
        listoPoolUpdateConsumos = (genericSQLDao.obtenerPorSql(sql, PoolTO.class));

        sql = "SELECT * FROM inventario.fun_list_pool_delete_consumo('" + farmcode + "', '" + regDateStart + "', '" + productCenter + "', " + filPool + ")";
        listPoolDeleteConsumos = (genericSQLDao.obtenerPorSql(sql, PoolTO.class));

        listPool.addAll(listPoolDeleteGramaje);
        listPool.addAll(listPoolEditaGramaje);
        listPool.addAll(listPoolEditaConsumos);
        listPool.addAll(listPoolDeleteConsumos);
        List listaEndPool = new ArrayList();
        Map<Integer, PoolTO> mapPool = new HashMap<Integer, PoolTO>(listPool.size());
        for (PoolTO p : listPool) {
            mapPool.put(p.hashCode(), p);
        }
        for (Entry<Integer, PoolTO> p : mapPool.entrySet()) {
            listaEndPool.add(p.getValue());
        }
        listPool = listaEndPool;
        return listPool;
    }

    @Override
    public List<IntegratedPool> getListIntegratedPool(String integration) throws Exception {
        List<IntegratedPool> lisIntegratedPool;
        String sql;
        sql = "SELECT row_number() over(partition by '' order by '') as id , sis_empresa.emp_codigo as idCompany, sis_empresa.emp_razon_social as nameCompany, "
                + "prd_sector.sec_codigo as idProduccionCenter, prd_sector.sec_nombre as nameProduccionCenter, prd_piscina.pis_numero as idPool, prd_piscina.pis_nombre as namePool "
                + "FROM produccion.prd_piscina INNER JOIN produccion.prd_sector ON "
                + "prd_piscina.sec_empresa = prd_sector.sec_empresa AND "
                + "prd_piscina.sec_codigo = prd_sector.sec_codigo "
                + "INNER JOIN sistemaweb.sis_empresa ON "
                + "prd_sector.sec_empresa = sis_empresa.emp_codigo "
                + "WHERE prd_sector.sec_integracion_con = '" + integration + "' AND prd_piscina.pis_activa=true";
        lisIntegratedPool = (genericSQLDao.obtenerPorSql(sql, IntegratedPool.class));
        return lisIntegratedPool;
    }

    @Override
    public List<ProductIntegrationTO> getListProductIntegration(String farmCode, String codeIntegracion, boolean listAll) throws Exception {
        List<ProductIntegrationTO> listProductIntegration;
        String containAll = listAll ? "AND inv_producto.pro_codigo_integracion IS NULL " : "";
        String sql;
        sql = "SELECT row_number() over (partition by '' order by '') as id, inv_consumos.cons_empresa as company, prd_sector.sec_codigo as productCenter,"
                + "inv_producto.pro_codigo_principal as productCode, inv_producto.pro_codigo_integracion as productCodeIntegration, inv_producto.pro_nombre as productName, inv_producto.pro_detalle as productDescription "
                + "FROM inventario.inv_consumos INNER JOIN inventario.inv_consumos_detalle ON "
                + "inv_consumos.cons_empresa = inv_consumos_detalle.cons_empresa AND "
                + "inv_consumos.cons_periodo = inv_consumos_detalle.cons_periodo AND "
                + "inv_consumos.cons_motivo = inv_consumos_detalle.cons_motivo AND "
                + "inv_consumos.cons_numero = inv_consumos_detalle.cons_numero "
                + "INNER JOIN inventario.inv_producto ON "
                + "inv_consumos_detalle.pro_empresa = inv_producto.pro_empresa AND "
                + "inv_consumos_detalle.pro_codigo_principal = inv_producto.pro_codigo_principal "
                + "INNER JOIN produccion.prd_sector ON "
                + "inv_consumos_detalle.sec_empresa = prd_sector.sec_empresa AND "
                + "inv_consumos_detalle.sec_codigo = prd_sector.sec_codigo "
                + "WHERE inv_consumos.cons_empresa='" + farmCode + "' AND prd_sector.sec_integracion_con='" + codeIntegracion + "' " + containAll
                + "GROUP by 4,5,company, productCenter, productName, productDescription Order by productCode ASC";
        listProductIntegration = (genericSQLDao.obtenerPorSql(sql, ProductIntegrationTO.class));
        return listProductIntegration;
    }

}
