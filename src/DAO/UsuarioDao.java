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
import javax.swing.JOptionPane;

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
            String query = "insert into usuario (user_name,user_password,tius_id)\n"
                    + "values (?,?,2) returning user_id";
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, usuario.getUsername());
            pst.setString(2, usuario.getPassword());
            rs = pst.executeQuery();
            while (rs.next()) {
                usuario.setId(rs.getInt("user_id"));
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
                Usuario usuario = new Usuario(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_password"));
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
                    + "user_name=?,\n"
                    + "user_password=?\n"
                    + "where user_id=?";
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
                    + "where user_id=?";
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, id);
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
    public Usuario get(int id) {

        String query = "select * from usuario;";
        try {

            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_password"));
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

    public Usuario login(String username, String password) {
        String query = "select * from usuario where user_name=? and user_password=?";
        Usuario usuario = null;
        try {
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setString(1, username);
            pst.setString(2, password);

            rs = pst.executeQuery();

            while (rs.next()) {
                usuario = new Usuario(rs.getInt("user_id"), rs.getString("user_name"), rs.getInt("tius_id"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error Base de Datos:\n"
                    + ex, "Error en la operación", JOptionPane.ERROR_MESSAGE);

        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }

        return usuario;
    }

}
