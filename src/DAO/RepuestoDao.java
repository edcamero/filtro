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
            String query = "insert into SPARE (spar_name,spar_cost,spar_price_without_iva,iva,spar_price_with_iva,tysp_id)\n"
                    + "values(?,?,?,?,?,?) returning spar_id;";
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, repuesto.getNombre());
            pst.setInt(2, repuesto.getValorCosto());
            pst.setInt(3, repuesto.getValorVenta());
            pst.setInt(4, repuesto.getIva());
            pst.setInt(5, repuesto.getValorVentaIva());
            pst.setInt(6, repuesto.getTipo_id());

            rs = pst.executeQuery();
            while (rs.next()) {
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
        String query = "SELECT spare.*,type_spare.tysp_name FROM spare inner join type_spare on spare.tysp_id = type_spare.tysp_id where spare.spar_status=true order by spar_id;";
        try {

            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            while (rs.next()) {

                Repuesto repuesto = new Repuesto(rs.getInt("spar_id"), rs.getString("spar_name"), rs.getString("tysp_name"), rs.getInt("spar_cost"),
                        rs.getInt("spar_price_without_iva"), rs.getInt("iva"), rs.getInt("spar_price_with_iva"));
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

    public ArrayList<Repuesto> getAll(String typeSpare) {
        ArrayList<Repuesto> lista = new ArrayList<>();
        String query = "SELECT \n"
                + "  spare.*, \n"
                + "  type_spare.tysp_name \n"
                + "FROM \n"
                + "  spare \n"
                + "  inner join type_spare on spare.tysp_id = type_spare.tysp_id \n"
                + "where \n"
                + "  spare.spar_status = true \n"
                + "  and type_spare.tysp_name = '" + typeSpare + "' \n"
                + "order by \n"
                + "  spar_id;";
        try {

            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            while (rs.next()) {

                Repuesto repuesto = new Repuesto(rs.getInt("spar_id"), rs.getString("spar_name"), rs.getString("tysp_name"), rs.getInt("spar_cost"),
                        rs.getInt("spar_price_without_iva"), rs.getInt("iva"), rs.getInt("spar_price_with_iva"));
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

    public ArrayList<Repuesto> getAllSearch(String keyWord) {
        ArrayList<Repuesto> lista = new ArrayList<>();
        String query = "SELECT \n"
                + "  spare.*, \n"
                + "  type_spare.tysp_name \n"
                + "FROM \n"
                + "  spare \n"
                + "  inner join type_spare on spare.tysp_id = type_spare.tysp_id \n"
                + "where \n"
                + "  spare.spar_status = true \n"
                + "  and (tysp_name ilike '%" + keyWord + "%'\n"
                + "  or spar_name ilike '%" + keyWord + "%')\n"
                + "order by \n"
                + "  spar_id;";
        try {

            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            while (rs.next()) {

                Repuesto repuesto = new Repuesto(rs.getInt("spar_id"), rs.getString("spar_name"), rs.getString("tysp_name"), rs.getInt("spar_cost"),
                        rs.getInt("spar_price_without_iva"), rs.getInt("iva"), rs.getInt("spar_price_with_iva"));
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

            String query = "update spare\n"
                    + "set\n"
                    + "spar_name=?,\n"
                    + "spar_cost=?,\n"
                    + "spar_price_without_iva=?,\n"
                    + "iva=?,\n"
                    + "spar_price_with_iva=?,\n"
                    + "tysp_id=?\n"
                    + "where spar_id=?;";
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, repuesto.getNombre());
            pst.setInt(2, repuesto.getValorCosto());
            pst.setInt(3, repuesto.getValorVenta());
            pst.setInt(4, repuesto.getIva());
            pst.setInt(5, repuesto.getValorVentaIva());
            pst.setInt(6, repuesto.getTipo_id());
            pst.setInt(7, repuesto.getId());
            pst.execute();
            //pst.close();
            respuesta = true;
            System.out.println("hola");

        } catch (SQLException ex) {
            Logger.getLogger(RepuestoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    @Override
    public boolean delete(int id) {
        boolean respuesta = false;
//            String query = "delete from repuesto "
//                    + " where id_rep =?";
        String query = "update spare\n"
                + "set\n"
                + "spar_status = false \n"
                + "where spar_id=?;";
        try {

            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, id);
            pst.execute();
            respuesta = true;
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
    public Repuesto get(int id) {
        Repuesto repuesto = null;

        String query = "SELECT spare.*,type_spare.tysp_name FROM spare inner join type_spare on spare.tysp_id = type_spare.tysp_id where spar_id=?;";
        try {

            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {

                repuesto = new Repuesto(rs.getInt("spar_id"), rs.getString("spar_name"), rs.getString("tysp_name"), rs.getInt("spar_cost"),
                        rs.getInt("spar_price_without_iva"), rs.getInt("iva"), rs.getInt("spar_price_with_iva"));

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
