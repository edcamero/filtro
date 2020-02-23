/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Models.Equipo;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blade
 */
public class EquipoDao implements InterfaceDao<Equipo> {

    private Conexion con;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public EquipoDao(Conexion con) {
        this.con = con;
    }

    
    
    @Override
    public boolean save(Equipo equipo) {
        String query = "insert into equipo(material_equi,modelo_equi,nombre_equi,precio_equi,disponible)\n"
                + "	values(?,?,?,?,?) returning id_equi";
        try {
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, equipo.getMaterial());
            pst.setString(2, equipo.getModelo());
            pst.setString(3, equipo.getNombre());
            pst.setInt(4, equipo.getPrecio());
            pst.setBoolean(5, equipo.getDisponible());
            rs = pst.executeQuery();
            while (rs.next()) {
                equipo.setId(rs.getInt(1));

            }
            return true;
        } catch (SQLException ex) {
           Logger.getLogger(EquipoDao.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return false;

    }

    @Override
    public ArrayList getAll() {

        ArrayList<Equipo> list = new ArrayList<>();
        try {
            String query = "select * from Equipo";
            pst = this.con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();

            while (rs.next()) {
                Equipo equipo = new Equipo(rs.getInt("id_equi"), rs.getString("material_equi"), rs.getString("modelo_equi"),
                        rs.getString("nombre_equi"), rs.getInt("precio_equi"), rs.getBoolean("disponible"));
                list.add(equipo);
            }
                return list;
        } catch (SQLException ex) {
            Logger.getLogger(EquipoDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(EquipoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public boolean delete(int id) {
        try {
            String query = "delete from equipo"
                    + " where id_equi =?;";

            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setInt(1, id);

            pst.execute();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EquipoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Equipo equipo) {

        try {
            String query = "UPDATE equipo\n"
                    + "   SET  material_equi=?, modelo_equi=?, nombre_equi=?, precio_equi=? \n"
                    + "       , disponible=?\n"
                    + " WHERE id_equi=?;";

            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, equipo.getMaterial());
            pst.setString(2, equipo.getModelo());
            pst.setString(3, equipo.getNombre());
            pst.setInt(4, equipo.getPrecio());
            pst.setBoolean(5, equipo.getDisponible());
            pst.setInt(6, equipo.getId());
            pst.execute();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(EquipoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

  

    @Override
    public Equipo get(int id) {
        
        try {
            String query = "select * from Equipo where id_equi=?;";
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                Equipo equipo = new Equipo(rs.getInt("id_equi"), rs.getString("material_equi"), rs.getString("modelo_equi"),
                        rs.getString("nombre_equi"), rs.getInt("precio_equi"), rs.getBoolean("disponible"));
                return equipo;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    return null;
    }

}
