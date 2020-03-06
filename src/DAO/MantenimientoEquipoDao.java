/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Models.MantenimientoEquipo;
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
public class MantenimientoEquipoDao implements InterfaceDao<MantenimientoEquipo> {

    private Conexion con;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public MantenimientoEquipoDao(Conexion con) {
        this.con = con;
    }

    @Override
    public boolean save(MantenimientoEquipo mantEquipo) {
         boolean respuesta = false;
        try {
           
            
            String consulta = "INSERT INTO mantenimiento_equipo(\n"
                    + "             id_man, id_buj, id_eqcl, costos_repuestos, ingresos_respuestos \n"
                    + "            )\n"
                    + "    VALUES (?, ?, ?, ?, ?) returning id_maeq";
            pst = con.getCon().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, mantEquipo.getIdMante());
            pst.setInt(2, mantEquipo.getIdBujia());
            pst.setInt(3, mantEquipo.getIdEquiCliente());
            pst.setInt(4, mantEquipo.getCostosRepuestos());
            pst.setInt(5, mantEquipo.getIngresosRespuestos());
            rs = pst.executeQuery();
             while (rs.next()) {
                 mantEquipo.setId(rs.getInt("id_maeq"));
                
            }
            respuesta=true;
          
        } catch (SQLException ex) {
            Logger.getLogger(MantenimientoEquipoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
          return respuesta;
    }

    @Override
    public ArrayList<MantenimientoEquipo> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(MantenimientoEquipo objeto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MantenimientoEquipo get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
