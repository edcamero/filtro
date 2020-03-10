/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Models.Bujia;
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
public class BujiaDao implements InterfaceDao<Bujia> {

    private Conexion con;
    private PreparedStatement pst = null;
    private ResultSet rs = null;

    public BujiaDao(Conexion con) {
        this.con = con;
    }

    @Override
    public boolean save(Bujia bujia) {

        String consulta = "insert into bujia (nombre_buj,vida_util_buj,valor_costo,valor_venta) values\n"
                + "		  (?,?,?,?) returning id_buj";
        try {

            pst = con.getCon().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, bujia.getNombre());
            pst.setInt(2, bujia.getVidaUtil());
            pst.setInt(3, bujia.getValorCosto());
            pst.setInt(4, bujia.getValorVenta());

            rs = pst.executeQuery();
            while (rs.next()) {
                bujia.setId(rs.getInt(1));

            }
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    @Override
    public ArrayList<Bujia> getAll() {
        ArrayList<Bujia> lista = new ArrayList<Bujia>();
        try {

            String query = "select * FROM bujia where id_buj<>0 ;";

            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();

            while (rs.next()) {

                Bujia c = new Bujia(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
                //Equipo c=new Equipo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getBoolean(6));
                //System.out.println(c.toString());
                lista.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BujiaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public boolean update(Bujia bujia) {

        String query = "update bujia set\n"
                + "nombre_buj=?,\n"
                + "vida_util_buj=?,\n"
                + "valor_costo=?,\n"
                + "valor_venta=? \n"
                + "where id_buj=?";
        try {
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, bujia.getNombre());
            pst.setInt(2, bujia.getVidaUtil());
            pst.setInt(3, bujia.getValorCosto());
            pst.setInt(4, bujia.getValorVenta());
            pst.setInt(5, bujia.getId());

            pst.execute();
            pst.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(BujiaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        String query = "delete from bujia\n"
                + "where id_buj=?";
        try {
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            pst.setInt(1, id);

            pst.execute();
            pst.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(BujiaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Bujia get(int id) {

        try {
            String query = "select * FROM bujia where id_buj=?;";

            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {

                Bujia bujia = new Bujia(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5));
                //Equipo c=new Equipo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getBoolean(6));
                //System.out.println(c.toString());
                return bujia;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BujiaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
