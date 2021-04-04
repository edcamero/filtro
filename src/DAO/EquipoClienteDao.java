/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Models.Equipo;
import Models.EquipoCliente;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blade
 */
public class EquipoClienteDao implements InterfaceDao<EquipoCliente> {

    private Conexion con;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public EquipoClienteDao(Conexion con) {
        this.con = con;
    }

    @Override
    public boolean save(EquipoCliente equipoCliente) {
        try {
            String query = "insert into equipo_cliente\n"
                    + "(id_equi,id_cli) values (?,?) returning id_eqcl";

            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, equipoCliente.getIdEquipo());
            pst.setInt(2, equipoCliente.getIdCliente());
            rs = pst.executeQuery();
            while (rs.next()) {
                equipoCliente.setId(rs.getInt(1));

            }
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(EquipoClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ArrayList<EquipoCliente> getAll() {
        ArrayList<EquipoCliente> lista = new ArrayList<>();
        try {

            String query = "select * from equipo_cliente;";
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();

            while (rs.next()) {

                EquipoCliente equipoCliente = new EquipoCliente(rs.getInt("id_eqcl"), rs.getInt("id_equi"), rs.getInt("id_id_cli"),rs.getDate(""));
                lista.add(equipoCliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EquipoClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public ArrayList<EquipoCliente> getAll(int id_cliente) {
        ArrayList<EquipoCliente> equipos = new ArrayList<>();
        try {

            String query = "SELECT * FROM public.divice_customer"
                    + " where cust_id=?;";
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, id_cliente);
            rs = pst.executeQuery();

            while (rs.next()) {
                EquipoCliente equipoCliente = new EquipoCliente(rs.getInt("dicu_id"), rs.getInt("cust_id"), rs.getInt("divi_id"),rs.getDate("divi_date"));
                equipos.add(equipoCliente);
            }

        } catch (SQLException ex) {
            Logger.getLogger(EquipoClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return equipos;
    }

    @Override
    public boolean update(EquipoCliente equipoCliente) {
        try {
            String query = "update equipo_cliente\n"
                    + "set \n"
                    + "id_equi=?,\n"
                    + "id_cli=?\n"
                    + "where id_eqcl=?;";

            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, equipoCliente.getIdEquipo());
            pst.setInt(2, equipoCliente.getIdCliente());
            pst.setInt(3, equipoCliente.getId());

            rs = pst.executeQuery();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EquipoClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            String query = "delete from equipo_cliente\n"
                    + "where id_eqcl=?;";
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setInt(1, id);

            pst.execute();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(EquipoClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public EquipoCliente get(int id) {

        try {
            String query = "select * from equipo_cliente"
                    + "where id_eqcl=?;";
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();

            while (rs.next()) {

                EquipoCliente equipoCliente = new EquipoCliente(rs.getInt("dicu_id"), rs.getInt("cust_id"), rs.getInt("divi_id"),rs.getDate("divi_date"));

                return equipoCliente;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipoClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
