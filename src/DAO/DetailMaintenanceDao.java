/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Models.MantenimientoEquipo;
import Models.MantenimientoRepuesto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blade
 */
public class DetailMaintenanceDao {

    private Conexion con;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public DetailMaintenanceDao(Conexion con) {
        this.con = con;
    }

    public boolean save(MantenimientoRepuesto detailMaintanance) {
        boolean respuesta = false;
        try {

            String consulta = "INSERT INTO detail_maintenance(\n"
                    + "             main_id, spar_id, dicu_id,dema_spar_cant, spar_cost,spar_price_without_iva_unit,spar_price_without_iva_total,iva,spar_price_total \n"
                    + "            )\n"
                    + "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) returning dema_id";
            pst = con.getCon().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, detailMaintanance.getMantenimientoEquipo().getIdMante());
            pst.setInt(2, detailMaintanance.getIdRepuesto());
            pst.setInt(3, detailMaintanance.getMantenimientoEquipo().getIdEquiCliente());
            pst.setInt(4, detailMaintanance.getCantidadRep());
            pst.setInt(5, detailMaintanance.getRepuesto().getValorCosto());
            pst.setInt(6, detailMaintanance.getRepuesto().getValorVenta());
            pst.setInt(7, detailMaintanance.getIngresosRespuestos());
            pst.setInt(8, detailMaintanance.getIva());
            pst.setInt(9, detailMaintanance.getIngresosRespuestos());
            rs = pst.executeQuery();
            while (rs.next()) {
                detailMaintanance.setId(rs.getInt("dema_id"));

            }
            respuesta = true;

        } catch (SQLException ex) {
            Logger.getLogger(DetailMaintenanceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    public ArrayList<MantenimientoEquipo> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean update(MantenimientoEquipo objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public MantenimientoEquipo get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
