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
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Trabajo
 */
@Repository
public class PoolDaoImpl extends GenericDaoImpl<Pool, Integer> implements PoolDao {

    @Autowired
    private GenericSQLDao genericSQLDao;

    @Override
    public List<VariablesTO> listDataPesos(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        String sql = "SELECT  row_number() over (partition by '' order by '') as id , 'VAR0006' as code, ROUND(gra_ipromedio,2) as value,'gr' as units, gra_fecha as date, (usr_fecha_inserta at time zone 'America/Guayaquil') at time zone 'UTC' as regDateTime, '' as productCode FROM produccion.prd_grameaje "
                + "WHERE usr_fecha_inserta > '" + regDateStart + "' and gra_empresa ='" + farmcode + "' and  gra_piscina='" + pool + "' and  gra_sector='" + productCenter + "' ORDER BY usr_fecha_inserta, gra_sector ASC, gra_piscina ASC, gra_fecha  ASC";
        return genericSQLDao.obtenerPorSql(sql, VariablesTO.class);
    }

    @Override
    public List<VariablesTO> listGraDelete(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        String sql = "SELECT  row_number() over (partition by '' order by '') as id , 'VAR0006' as code, null as value,'gr' as units, gra_fecha as date, (sis_suceso.sus_fecha at time zone 'America/Guayaquil') at time zone 'UTC' as regDateTime, '' as productCode "
                + " FROM sistemaweb.sis_suceso_gramaje INNER JOIN sistemaweb.sis_suceso "
                + " ON sis_suceso_gramaje.sus_suceso = sis_suceso.sus_secuencia "
                + "WHERE sis_suceso.sus_fecha  > '" + regDateStart + "'  AND sis_suceso.sus_suceso='DELETE' AND gra_empresa ='" + farmcode + "' AND "
                + "gra_piscina='" + pool + "' AND  gra_sector='" + productCenter + "' ORDER BY sis_suceso_gramaje.sus_suceso, gra_sector ASC, gra_piscina ASC, gra_fecha  ASC";
        return genericSQLDao.obtenerPorSql(sql, VariablesTO.class);
    }

    @Override
    public List<VariablesTO> listDataSobrevivencia(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        String sql = "SELECT  row_number() over (partition by '' order by '') as id, 'VAR0010'as code, ROUND(gra_sobrevivencia,2)as value, '%' as units, gra_fecha as date, (usr_fecha_inserta at time zone 'America/Guayaquil') at time zone 'UTC' as regDateTime , '' as productCode FROM produccion.prd_grameaje "
                + "WHERE usr_fecha_inserta > '" + regDateStart + "' and gra_empresa ='" + farmcode + "' and gra_piscina ='" + pool + "' and gra_sector='" + productCenter + "' ORDER BY usr_fecha_inserta ASC, gra_sector ASC, gra_piscina ASC, gra_fecha ASC";
        return genericSQLDao.obtenerPorSql(sql, VariablesTO.class);
    }

    @Override
    public List<VariablesTO> listSobreDelete(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        String sql = "SELECT  row_number() over (partition by '' order by '') as id , 'VAR0010' as code, null as value,'%' as units, gra_fecha as date, (sis_suceso.sus_fecha at time zone 'America/Guayaquil') at time zone 'UTC' as regDateTime, '' as productCode "
                + " FROM sistemaweb.sis_suceso_gramaje INNER JOIN sistemaweb.sis_suceso "
                + " ON sis_suceso_gramaje.sus_suceso = sis_suceso.sus_secuencia "
                + "WHERE sis_suceso.sus_fecha  > '" + regDateStart + "'  AND sis_suceso.sus_suceso='DELETE' AND gra_empresa ='" + farmcode + "' AND "
                + "gra_piscina='" + pool + "' AND  gra_sector='" + productCenter + "' ORDER BY sis_suceso_gramaje.sus_suceso, gra_sector ASC, gra_piscina ASC, gra_fecha  ASC";
        return genericSQLDao.obtenerPorSql(sql, VariablesTO.class);
    }

    @Override
    public List<VariablesTO> listDataInsumos(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {

        String sql = "SELECT  row_number() over (partition by '' order by '') as id,  CASE WHEN inv_producto.pro_nombre ilike 'Balanceado%' THEN 'VAR0003' ELSE 'VAR0073' END as code, CASE WHEN inv_consumos.cons_anulado THEN null ELSE ROUND(det_cantidad,2) END as value, inv_producto.med_codigo as units, "
                + "cons_fecha as date, (inventario.inv_consumos.usr_fecha_modifica at time zone 'America/Guayaquil') at time zone 'UTC' as regDateTime, inv_producto.pro_codigo_integracion as productCode "
                + "FROM inventario.inv_consumos INNER JOIN inventario.inv_consumos_detalle "
                + "ON inv_consumos.cons_empresa  = inv_consumos_detalle.cons_empresa and "
                + "inv_consumos.cons_periodo = inv_consumos_detalle.cons_periodo and "
                + "inv_consumos.cons_motivo = inv_consumos_detalle.cons_motivo and "
                + "inv_consumos.cons_numero = inv_consumos_detalle.cons_numero "
                + "INNER JOIN inventario.inv_producto ON "
                + "inv_consumos_detalle.pro_empresa = inv_producto.pro_empresa and "
                + "inv_consumos_detalle.pro_codigo_principal = inv_producto.pro_codigo_principal "
                + "WHERE inv_consumos.cons_empresa ='" + farmcode + "' and "
                + "inv_consumos.usr_fecha_modifica > '" + regDateStart + "' AND inv_consumos.usr_fecha_modifica = inv_consumos.usr_fecha_inserta AND "
                + "inv_consumos_detalle.pis_numero='" + pool + "' AND inv_consumos_detalle.pis_sector='" + productCenter + "' "
                + "ORDER BY COALESCE(inv_consumos.usr_fecha_modifica, inv_consumos.usr_fecha_inserta), inv_consumos_detalle.sec_codigo, inv_consumos_detalle.pis_numero, inv_consumos.cons_fecha";
        return genericSQLDao.obtenerPorSql(sql, VariablesTO.class);
    }

    @Override
    public List<VariablesTO> listDataConsDelete(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        String sql = "SELECT * FROM inventario.fun_consumos_eliminados('" + farmcode + "','" + regDateStart + "','" + pool + "','" + productCenter + "')";
        return genericSQLDao.obtenerPorSql(sql, VariablesTO.class);
    }

    @Override
    public List<Dates> listDataDatesUpdates(String regDateStart, String farmcode, String pool, String productCenter) throws Exception {
        String sql = "SELECT DISTINCT inventario.inv_consumos.cons_fecha "
                + "FROM inventario.inv_consumos INNER JOIN inventario.inv_consumos_detalle "
                + "ON inv_consumos.cons_empresa  = inv_consumos_detalle.cons_empresa and "
                + "inv_consumos.cons_periodo = inv_consumos_detalle.cons_periodo and "
                + "inv_consumos.cons_motivo = inv_consumos_detalle.cons_motivo and "
                + "inv_consumos.cons_numero = inv_consumos_detalle.cons_numero "
                + "INNER JOIN inventario.inv_producto ON "
                + "inv_consumos_detalle.pro_empresa = inv_producto.pro_empresa AND "
                + "inv_consumos_detalle.pro_codigo_principal = inv_producto.pro_codigo_principal "
                + "WHERE inv_consumos.cons_empresa ='" + farmcode + "' AND "
                + "inv_consumos.usr_fecha_modifica > '" + regDateStart + "'  AND "
                + "inv_consumos.usr_fecha_modifica <> inv_consumos.usr_fecha_inserta AND "
                + "inv_consumos_detalle.pis_numero='" + pool + "' AND inv_consumos_detalle.pis_sector='" + productCenter + "' ORDER BY cons_fecha";
        return genericSQLDao.obtenerPorSql(sql, Dates.class);
    }

    @Override
    public List<VariablesTO> listDataInsumosEnd(String consFecha, String farmcode, String pool, String productCenter) throws Exception {

        String sql = "SELECT  row_number() over (partition by '' order by '') as id, CASE WHEN inv_producto.pro_nombre ilike 'Balanceado%' THEN 'VAR0003' ELSE 'VAR0073' END as code, ROUND(det_cantidad,2) as value, inv_producto.med_codigo as units, "
                + "cons_fecha as date, (inventario.inv_consumos.usr_fecha_modifica at time zone 'America/Guayaquil') at time zone 'UTC' as regDateTime, inv_producto.pro_codigo_integracion as productCode "
                + "FROM inventario.inv_consumos INNER JOIN inventario.inv_consumos_detalle "
                + "ON inv_consumos.cons_empresa  = inv_consumos_detalle.cons_empresa and "
                + "inv_consumos.cons_periodo = inv_consumos_detalle.cons_periodo and "
                + "inv_consumos.cons_motivo = inv_consumos_detalle.cons_motivo and "
                + "inv_consumos.cons_numero = inv_consumos_detalle.cons_numero "
                + "INNER JOIN inventario.inv_producto ON "
                + "inv_consumos_detalle.pro_empresa = inv_producto.pro_empresa and "
                + "inv_consumos_detalle.pro_codigo_principal = inv_producto.pro_codigo_principal "
                + "WHERE inv_consumos.cons_empresa ='" + farmcode + "' AND "
                + "inventario.inv_consumos.cons_fecha = '" + consFecha + "' AND "
                + "inv_consumos_detalle.pis_numero='" + pool + "' AND inv_consumos_detalle.pis_sector='" + productCenter + "' "
                + "ORDER BY inventario.inv_consumos.cons_fecha, inv_consumos_detalle.sec_codigo, inv_consumos_detalle.pis_numero, inv_consumos.cons_fecha";
        return genericSQLDao.obtenerPorSql(sql, VariablesTO.class);
    }

    @Override
    public List<PoolTO> listPoolEdit(String regDateStart, String farmcode, String productCenter) throws Exception {
        List<PoolTO> listPoolEditaGramaje = new ArrayList<>();
        List<PoolTO> listPoolDeleteGramaje = new ArrayList<>();
        List<PoolTO> listPoolEditaConsumos = new ArrayList<>();
        List<PoolTO> listPoolDeleteConsumos = new ArrayList<>();
        List<PoolTO> listPool = new ArrayList<>();
        String sql;
        // Pool Edit Gramaje
        sql = "SELECT DISTINCT prd_piscina.pis_numero  as poolcode, prd_piscina.pis_nombre as poolname FROM produccion.prd_grameaje "
                + "INNER JOIN produccion.prd_piscina ON "
                + "produccion.prd_grameaje.pis_empresa = produccion.prd_piscina.pis_empresa AND "
                + "produccion.prd_grameaje.pis_sector = produccion.prd_piscina.pis_sector AND "
                + "produccion.prd_grameaje.pis_numero = produccion.prd_piscina.pis_numero "
                + "WHERE prd_grameaje.usr_fecha_inserta > '" + regDateStart + "' and gra_empresa = '" + farmcode + "' and gra_sector ='" + productCenter + "'";
        listPoolEditaGramaje = (genericSQLDao.obtenerPorSql(sql, PoolTO.class));

        sql = "SELECT * FROM produccion.fun_list_pool_edita_gramaje('" + farmcode + "', '" + regDateStart + "', '" + productCenter + "')";
        listPoolDeleteGramaje = (genericSQLDao.obtenerPorSql(sql, PoolTO.class));
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
                + "WHERE inv_consumos.cons_empresa ='" + farmcode + "' AND inv_consumos_detalle.pis_sector = '" + productCenter + "' AND COALESCE(inv_consumos.usr_fecha_modifica, inv_consumos.usr_fecha_inserta) > '" + regDateStart + "'";        
        listPoolEditaConsumos = (genericSQLDao.obtenerPorSql(sql, PoolTO.class));
        
        sql = "SELECT * FROM inventario.fun_list_pool_delete_consumo('" + farmcode + "', '" + regDateStart + "', '" + productCenter + "')";
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
        List<IntegratedPool> lisIntegratedPool = new ArrayList<>();
        String sql;
        sql = "SELECT row_number() over(partition by '' order by '') as id , sis_empresa.emp_codigo as idCompany, sis_empresa.emp_razon_social as nameCompany, "
                + "prd_sector.sec_codigo as idProduccionCenter, prd_sector.sec_nombre as nameProduccionCenter, prd_piscina.pis_numero as idPool, prd_piscina.pis_nombre as namePool "
                + "FROM produccion.prd_piscina INNER JOIN produccion.prd_sector ON "
                + "prd_piscina.sec_empresa = prd_sector.sec_empresa AND "
                + "prd_piscina.sec_codigo = prd_sector.sec_codigo "
                + "INNER JOIN sistemaweb.sis_empresa ON "
                + "prd_sector.sec_empresa = sis_empresa.emp_codigo "
                + "WHERE prd_sector.sec_integracion_con = '" + integration + "' ";
        lisIntegratedPool = (genericSQLDao.obtenerPorSql(sql, IntegratedPool.class));
        return lisIntegratedPool;
    }

    @Override
    public List<ProductIntegrationTO> getListProductIntegration(String farmCode, String codeIntegracion, boolean listAll) throws Exception {
        List<ProductIntegrationTO> listProductIntegration = new ArrayList();
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
