/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Models.RepuestoMante;
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
public class RespuestoManteDAO {
    private Conexion con;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public boolean agregar(RepuestoMante repuestoMante) {
        boolean respuesta = false;
        try {
            
            String consulta = "INSERT INTO repuesto_equipo(\n"
                    + "             id_maeq,id_rep, cantidad_rep)\n"
                    + "        VALUES ( ?, ?, ?) returning id_reeq;";
            pst = con.getCon().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, repuestoMante.getIdManEqui());
            pst.setInt(2, repuestoMante.getIdRepuesto());
            pst.setInt(3, repuestoMante.getCantidadRep());
            rs = pst.executeQuery();
             while (rs.next()) {
               repuestoMante.setId(rs.getInt("id_reeq"));

            }
            respuesta=true;
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(RespuestoManteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(pst!=null){
                try {
                    pst.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RespuestoManteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
