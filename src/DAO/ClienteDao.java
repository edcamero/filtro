/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Models.Cliente;
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
public class ClienteDao implements InterfaceDao<Cliente> {

    private Conexion con;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public ClienteDao(Conexion con) {
        this.con = con;
    }

    @Override
    public boolean save(Cliente cliente) {

        boolean res = false;
        //String consulta="select now()";
        String consulta = "insert into cliente (documento_cli,nombre_cli,telefono_uno_cli,telefono_dos_cli,"
                + "direccion_cli,email_cli) values(?,?,?,?,?,?) returning id_cli;";
        try {
            pst = con.getCon().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, cliente.getDocumento());
            pst.setString(2, cliente.getNombre());
            pst.setString(3, cliente.getTelefonoUno());
            pst.setString(4, cliente.getTelefonoDos());
            pst.setString(5, cliente.getDireccion());
            pst.setString(6, cliente.getEmail());

            rs = pst.executeQuery();
            while (rs.next()) {
                cliente.setId(rs.getInt(1));

            }
            res = true;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res;
    }

    @Override
    public ArrayList<Cliente> getAll() {

        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        String consulta = "select * FROM CUSTOMER;";

        try {
            con.ConexionPostgres();
            pst = con.getCon().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                //System.out.println(c.toString());
                clientes.add(cliente);
            }

        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }

    public ArrayList<Cliente> buscarClientes(String palabra) {
        palabra = "%" + palabra + "%";
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        String consulta = "select * FROM CLIENTE where nombre_cli ilike ?";

        try {
            con.ConexionPostgres();
            pst = con.getCon().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, palabra);
            rs = pst.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                //System.out.println(c.toString());
                clientes.add(cliente);
            }

        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }

    @Override
    public boolean update(Cliente cliente) {

        try {
            String consulta = "update cliente set\n"
                    + "	documento_cli=?,\n"
                    + "	nombre_cli=?,\n"
                    + "	telefono_uno_cli=?,\n"
                    + "	telefono_dos_cli=?,\n"
                    + "	direccion_cli=?,\n"
                    + "	email_cli=?\n"
                    + "where id_cli=? returning *";

            pst = con.getCon().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, cliente.getDocumento());
            pst.setString(2, cliente.getNombre());
            pst.setString(3, cliente.getTelefonoUno());
            pst.setString(4, cliente.getTelefonoDos());
            pst.setString(5, cliente.getDireccion());
            pst.setString(6, cliente.getEmail());
            pst.setInt(7, cliente.getId());

            rs = pst.executeQuery();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {

        String consulta = "delete from cliente\n"
                + "where id_cli=?";
        try {
            pst = con.getCon().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setInt(1, id);

            rs = pst.executeQuery();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    @Override
    public Cliente get(int id) {

        String consulta = "select * FROM CLIENTE where id_cli=?;";

        try {
            con.ConexionPostgres();
            pst = con.getCon().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                return new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                //System.out.println(c.toString());

            }

        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
