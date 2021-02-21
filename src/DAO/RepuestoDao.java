/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Models.Repuesto;
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
public class RepuestoDao implements InterfaceDao<Repuesto> {

    private Conexion con;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public RepuestoDao(Conexion con) {
        this.con = con;
    }

    
    
    @Override
    public boolean save(Repuesto repuesto) {
        boolean respuesta = false;
        try {
            String query = "insert into repuesto (nombre_rep,valor_costo,valor_venta)\n"
                    + "values(?,?,?) returning id_rep;";
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, repuesto.getNombre());
            pst.setInt(2, repuesto.getValorCosto());
            pst.setInt(3, repuesto.getValorVenta());
            rs = pst.executeQuery();
            while(rs.next()){
                 repuesto.setId(rs.getInt(1));
            }
           
            respuesta = true;
        } catch (SQLException ex) {
            Logger.getLogger(RepuestoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(RepuestoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return respuesta;
    }

    @Override
    public ArrayList<Repuesto> getAll() {
        ArrayList<Repuesto> lista = new ArrayList<>();
        String query = "select * from SPARE;";
        try {

            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            while (rs.next()) {

                Repuesto repuesto = new Repuesto(rs.getInt("id_rep"), rs.getString("nombre_rep"), rs.getInt("valor_costo"),
                        rs.getInt("valor_venta"));
                lista.add(repuesto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RepuestoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(RepuestoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public boolean update(Repuesto repuesto) {
        boolean respuesta = false;
        try {

            String query = "update repuesto\n"
                    + "set\n"
                    + "nombre_rep=?,\n"
                    + "valor_costo=?,\n"
                    + "valor_venta=?\n"
                    + "where id_rep=?;";
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, repuesto.getNombre());
            pst.setInt(2, repuesto.getValorCosto());
            pst.setInt(3, repuesto.getValorVenta());
            pst.setInt(4, repuesto.getId());
            pst.execute();
            //pst.close();
            respuesta =true;

        } catch (SQLException ex) {
            Logger.getLogger(RepuestoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
              
            } catch (SQLException ex) {
                Logger.getLogger(RepuestoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return respuesta;
    }

    @Override
    public boolean delete(int id) {
        boolean respuesta = false;
            String query = "delete from repuesto "
                    + " where id_rep =?";
        try {
            
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, id);
            
            pst.execute();
            respuesta=true;
        } catch (SQLException ex) {
            Logger.getLogger(RepuestoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(RepuestoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return respuesta;
    }

    @Override
    public Repuesto get(int id) {
        Repuesto repuesto=null;
        
        String query = "select * from repuesto where id_rep=?;";
        try {

            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {

                repuesto = new Repuesto(rs.getInt("id_rep"), rs.getString("nombre_rep"), rs.getInt("valor_costo"),
                        rs.getInt("valor_venta"));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(RepuestoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(RepuestoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return repuesto;
        
    }

}
