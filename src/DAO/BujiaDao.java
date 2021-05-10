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

        String consulta = "insert into spark_plug (spar_id,sppl_useful_life) values\n"
                + "		  (?,?) returning sppl_id";
        try {

            pst = con.getCon().prepareStatement(consulta, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, bujia.getId());
            pst.setInt(2, bujia.getVidaUtil());

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

            String query = "select * ,COALESCE((select sppl_useful_life from spark_plug where spar_id = spare.spar_id ),0) as life from spare where tysp_id = 2 AND spar_status = true;";

            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();

            while (rs.next()) {
                Bujia bujia = new Bujia(rs.getInt("spar_id"), rs.getString("spar_name"), rs.getInt("life"), rs.getInt("spar_cost"), rs.getInt("spar_price_without_iva"), rs.getInt("iva"), rs.getInt("spar_price_without_iva"),2);
                lista.add(bujia);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BujiaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public boolean update(Bujia bujia) {

        String query = "update spark_plug set\n"
                + "sppl_useful_life=? \n"
                + "where spar_id = ?;";
        try {
            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, bujia.getVidaUtil());
            pst.setInt(2, bujia.getId());

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
        String query = "delete from spare\n"
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
            String query = "select * ,COALESCE((select sppl_useful_life from spark_plug where spar_id = spare.spar_id ),0) as life from spare where spar_id = ? ;";

            pst = con.getCon().prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {

              Bujia bujia = new Bujia(rs.getInt("spar_id"), rs.getString("spar_name"), rs.getInt("life"), rs.getInt("spar_cost"), rs.getInt("spar_price_without_iva"), rs.getInt("iva"), rs.getInt("spar_price_without_iva"),2);
              
                return bujia;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BujiaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
