/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Models.TipoRepuesto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class TipoRepuestoDao implements InterfaceDao<TipoRepuesto>{
    
    
    private Conexion con;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public TipoRepuestoDao(Conexion con) {
        this.con = con;
    }
    
    @Override
    public boolean save(TipoRepuesto tipoRepuesto) {
       String consulta = "insert into type_spare (tysp_name) values\n"
                + "		  (?) returning tysp_id";
        try {
            pst = con.getCon().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, tipoRepuesto.getNombre());

            rs = pst.executeQuery();
            while (rs.next()) {
                tipoRepuesto.setId(rs.getInt(1));
            }
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(TipoRepuestoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ArrayList<TipoRepuesto> getAll() {
        ArrayList<TipoRepuesto> lista = new ArrayList<>();
        String query = "SELECT * FROM type_spare order by tysp_id;";
        try {

            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            while (rs.next()) {
                TipoRepuesto tipoRepuesto = new TipoRepuesto(rs.getInt("tysp_id"), rs.getString("tysp_name"));
                lista.add(tipoRepuesto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TipoRepuestoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(TipoRepuestoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public boolean update(TipoRepuesto tipoRepuesto) {
        String query = "update type_spare set tysp_name=? where tysp_id=?";
        try {
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, tipoRepuesto.getNombre());
            pst.setInt(2, tipoRepuesto.getId());

            pst.execute();
            pst.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(TipoRepuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipoRepuesto get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
