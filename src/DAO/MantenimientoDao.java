/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Models.Mantenimiento;
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
public class MantenimientoDao {

    private Conexion con;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public MantenimientoDao(Conexion con) {
        this.con = con;
    }

    public boolean save(Mantenimiento mantenimiento) {
        boolean respuesta = false;
        try {
            
            String consulta = "INSERT INTO maintenance( tecn_id,cust_id, main_datail, main_cost_workforce, \n"
                    + "           main_cost_spare, main_cost_total, main_date)\n"
                    + "    VALUES (?, ?, ?, ?, ?, ?, ?) returning main_id;\n"
                    + "";

            pst = con.getCon().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, mantenimiento.getTecnico().getId());
            pst.setInt(2, mantenimiento.getCliente().getId());
            pst.setString(3, mantenimiento.getNota());
            pst.setInt(4, mantenimiento.getCostoServicioTecnico());
            pst.setInt(5, mantenimiento.getValorMantenimiento());
            pst.setInt(6, mantenimiento.getValorTotal());
            pst.setDate(7, new java.sql.Date(mantenimiento.getFechaMan().getTime()));
            rs = pst.executeQuery();
             while (rs.next()) {
                mantenimiento.setId(rs.getInt("main_id"));

            }
            respuesta=true;

        } catch (SQLException ex) {
            Logger.getLogger(MantenimientoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    public void listar(ArrayList lista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void actualizar(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void borrar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
