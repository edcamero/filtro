/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Models.Tecnico;
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
public class TecnicoDao implements InterfaceDao<Tecnico> {

    private Conexion con;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public TecnicoDao(Conexion con) {
        this.con = con;
    }

    @Override
    public boolean save(Tecnico tecnico) {
        try {
            String query = "insert into tecnico (documento_tec,nombre_tec,telefono_uno_tec,estado_tec)\n"
                    + "values(?,?,?,?) returning id_tec;";
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, tecnico.getDocumento());
            pst.setString(2, tecnico.getNombre());
            pst.setString(3, tecnico.getTelefonoUno());
            pst.setBoolean(4, tecnico.getEstado());
            rs = pst.executeQuery();

            while (rs.next()) {
                tecnico.setId(rs.getInt("id_tec"));
            }

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TecnicoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ArrayList<Tecnico> getAll() {
        ArrayList<Tecnico> lista = new ArrayList<Tecnico>();
        String query = "select * from tecnico order by tecn_id;";
        try {

            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            while (rs.next()) {
                Tecnico tecnico = new Tecnico(rs.getInt("tecn_id"), rs.getString("tecn_document"), rs.getString("tecn_name"),
                        rs.getString("tecn_telephone"), rs.getBoolean("tecn_status"));
                lista.add(tecnico);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecnicoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public boolean update(Tecnico tecnico) {
        boolean respuesta = false;
        try {
            String query = "update tecnico\n"
                    + "set\n"
                    + "documento_tec=?,\n"
                    + "nombre_tec=?,\n"
                    + "telefono_uno_tec=?,\n"
                    + "estado_tec=?\n"
                    + "where id_tec=?;";
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, tecnico.getDocumento());
            pst.setString(2, tecnico.getNombre());
            pst.setString(3, tecnico.getTelefonoUno());
            pst.setBoolean(4, tecnico.getEstado());
            pst.setInt(5, tecnico.getId());
            pst.execute();
            respuesta=true;
            pst.close();

        } catch (SQLException ex) {
            Logger.getLogger(TecnicoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    @Override
    public boolean delete(int id) {
        boolean respuesta=false;
        try {
            String query = "delete from tecnico "
                    + "where id_tec=?;";
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, id);
            pst.execute();
            respuesta=true;
        } catch (SQLException ex) {
            Logger.getLogger(TecnicoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(TecnicoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return respuesta;
    }

    @Override
    public Tecnico get(int id) {

        String query = "select * from tecnico where id_tec=?";
        try {

            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Tecnico tecnico = new Tecnico(rs.getInt("id_tec"), rs.getString("documento_tec"), rs.getString("nombre_tec"),
                        rs.getString("telefono_uno_tec"), rs.getBoolean("estado_tec"));
                return tecnico;
            }

        } catch (SQLException ex) {
            Logger.getLogger(TecnicoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(TecnicoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;

    }

}
