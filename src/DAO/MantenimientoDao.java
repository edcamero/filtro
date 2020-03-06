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
            
            String consulta = "INSERT INTO mantenimiento( id_tec,id_cli, nota_man, costo_servicio_tecnico, \n"
                    + "            valor_mantenimiento, fecha_man)\n"
                    + "    VALUES (?, ?, ?, ?,?, ?) returning id_man;\n"
                    + "";

            pst = con.getCon().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, mantenimiento.getTecnico().getId());
            pst.setInt(2, mantenimiento.getCliente().getId());
            pst.setString(3, mantenimiento.getNota());
            pst.setInt(4, mantenimiento.getCostoServicioTecnico());
            pst.setInt(5, mantenimiento.getValorMantenimiento());
            pst.setDate(6, new java.sql.Date(mantenimiento.getFechaMan().getTime()));
            rs = pst.executeQuery();
             while (rs.next()) {
                mantenimiento.setId(rs.getInt("id_man"));

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
