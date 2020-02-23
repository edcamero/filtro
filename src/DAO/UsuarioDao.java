/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Models.Usuario;

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
public class UsuarioDao implements InterfaceDao<Usuario> {

    private Conexion con;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public UsuarioDao(Conexion con) {
        this.con = con;
    }

     
    
    @Override
    public boolean save(Usuario usuario) {
        try {
            String query = "insert into usuario (name_user,password)\n"
                    + "values (?,?) returning id_user";
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, usuario.getUsername());
            pst.setString(2, usuario.getPassword());
            rs = pst.executeQuery();
            while (rs.next()) {
                usuario.setId(rs.getInt("id_user"));
            }
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ArrayList<Usuario> getAll() {
        String query = "select * from usuario;";
        ArrayList<Usuario> lista = new ArrayList<>();
        try {

            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt("id_user"), rs.getString("name_user"), rs.getString("password"));
                lista.add(usuario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public boolean update(Usuario usuario) {
        try {
            String query = "update usuario\n"
                    + "set\n"
                    + "name_user=?,\n"
                    + "password=?\n"
                    + "where id_user=?";
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, usuario.getUsername());
            pst.setString(2, usuario.getPassword());
            pst.setInt(3, usuario.getId());
            rs = pst.executeQuery();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            String query = "delete from usuario\n"
                    + "where id_user=?";
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public Usuario get(int id) {
        
        String query = "select * from usuario;";
        try {

            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt("id_user"), rs.getString("name_user"), rs.getString("password"));
                return usuario;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    
    }

}
