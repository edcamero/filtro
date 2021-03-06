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
        String consulta = "insert into CUSTOMER (cust_document,cust_name,cust_telephone_one,cust_telephone_two,"
                + "cust_address,cust_email) values(?,?,?,?,?,?) returning cust_id;";
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
        String consulta = "select * FROM CUSTOMER where cust_status = true ORDER BY cust_id ASC;";

        try {
            con.ConexionPostgres();
            pst = con.getCon().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                               clientes.add(cliente);
            }

        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }

    public ArrayList<Cliente> buscarClientes(String palabra, String column) {
        palabra = "%" + palabra + "%";
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        String consulta = "select * FROM CUSTOMER where " + column + " ilike ?";

        try {
            con.ConexionPostgres();
            pst = con.getCon().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, palabra);
            rs = pst.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                
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
            String consulta = "update customer set\n"
                    + "	cust_document=?,\n"
                    + "	cust_name=?,\n"
                    + "	cust_telephone_one=?,\n"
                    + "	cust_telephone_two=?,\n"
                    + "	cust_address=?,\n"
                    + "	cust_email=?,\n"
                    + "	updateat=now()\n"
                    + "where cust_id=? returning *";

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

        String consulta = "update CUSTOMER set \n"
                + "	cust_status=false,\n"
                + "	updateat=now()\n"
                + "where cust_id =? returning *";
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

        String consulta = "select * FROM CUSTOMER where cust_id=?;";

        try {
            con.ConexionPostgres();
            pst = con.getCon().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                return new Cliente(rs.getInt("cust_id"), rs.getString("cust_document"), rs.getString("cust_name"), rs.getString("cust_telephone_one"), rs.getString("cust_telephone_two"), rs.getString("cust_address"), rs.getString("cust_email"));
               }

        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Cliente get(String word, String column) {

        String consulta = "select * FROM CUSTOMER where " + column + " = ?;";

        try {
            con.ConexionPostgres();
            pst = con.getCon().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, word);
            rs = pst.executeQuery();

            while (rs.next()) {
                return new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
               }

        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
