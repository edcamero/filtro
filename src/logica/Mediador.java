/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import Conexion.Conexion;
import DAO.BujiaDao;
import DAO.ClienteDao;
import DAO.EquipoClienteDao;
import DAO.EquipoDao;
import DAO.MantenimientoDao;
import DAO.DetailMaintenanceDao;
import DAO.RepuestoDao;
import DAO.RepuestoManteDao;
import DAO.TecnicoDao;
import DAO.TipoRepuestoDao;
import DAO.UsuarioDao;

import Models.Bujia;
import Models.Cliente;
import Models.Equipo;
import Models.EquipoCliente;
import Models.Mantenimiento;
import Models.MantenimientoEquipo;
import Models.Repuesto;
import Models.MantenimientoRepuesto;
import Models.Tecnico;
import Models.TipoRepuesto;
import Models.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import reportes.Recibo;

/**
 *
 * @author blade
 */
public class Mediador implements InterfaceMediador {

    Conexion conexion = Conexion.getConexion();
    ClienteDao clienteDao = new ClienteDao(conexion);
    EquipoClienteDao equipoClienteDao = new EquipoClienteDao(conexion);
    BujiaDao bujiaDao = new BujiaDao(conexion);
    EquipoDao equipoDao = new EquipoDao(conexion);
    RepuestoDao repuestoDao = new RepuestoDao(conexion);
    RepuestoManteDao repuestoManteDao = new RepuestoManteDao(conexion);
    TecnicoDao tecnicoDao = new TecnicoDao(conexion);
    UsuarioDao usuarioDao = new UsuarioDao(conexion);
    MantenimientoDao mantenimientoDao = new MantenimientoDao(conexion);
    DetailMaintenanceDao detailMaintenanceDao = new DetailMaintenanceDao(conexion);
    TipoRepuestoDao tipoRepuestoDao = new TipoRepuestoDao(conexion);

    @Override
    public boolean saveEquipo(Equipo equipo) {
        try {
            conexion.ConexionPostgres();

            return equipoDao.save(equipo);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return false;
    }

    @Override
    public ArrayList<Equipo> getAllEquipo() {

        try {
            conexion.ConexionPostgres();
            equipoDao = new EquipoDao(conexion);
            return equipoDao.getAll();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return new ArrayList<Equipo>();
    }

    @Override
    public Equipo getEquipo(int id) {
        try {

            conexion.ConexionPostgres();
            return equipoDao.get(id);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return null;

    }

    @Override
    public boolean updateEquipo(Equipo equipo) {
        boolean respuesta = false;
        try {
            conexion.ConexionPostgres();
            respuesta = equipoDao.update(equipo);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return respuesta;

    }

    @Override
    public boolean deleteEquipo(int id) {
        boolean respuesta = false;
        try {
            conexion.ConexionPostgres();
            respuesta = equipoDao.delete(id);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return respuesta;
    }

    @Override
    public boolean saveCliente(Cliente cliente) {
        try {
            conexion.ConexionPostgres();
            return clienteDao.save(cliente);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return false;
    }

    @Override
    public ArrayList<Cliente> getAllCliente() {

        try {
            conexion.ConexionPostgres();
            return clienteDao.getAll();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return new ArrayList<Cliente>();
    }

    public ArrayList<Cliente> buscarCliente(String palabra, String column) {

        try {
            conexion.ConexionPostgres();
            return clienteDao.buscarClientes(palabra, column);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return new ArrayList<Cliente>();
    }

    @Override
    public Cliente getCliente(int id) {
        Cliente cliente = null;
        try {
            conexion.ConexionPostgres();
            cliente = clienteDao.get(id);
            ArrayList<EquipoCliente> equiposCliente = equipoClienteDao.getAll(id);
            equiposCliente.stream().forEach(equi -> {
                equi.setEquipo(equipoDao.get(equi.getIdEquipo()));
            });
            cliente.setEquiposCliente(equiposCliente);

        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return cliente;
    }

    @Override
    public Cliente getCliente(String word, String column) {
        Cliente cliente = null;
        try {
            conexion.ConexionPostgres();
            cliente = clienteDao.get(word, column);
            ArrayList<EquipoCliente> equiposCliente = equipoClienteDao.getAll(cliente.getId());
            equiposCliente.stream().forEach(equi -> {
                equi.setEquipo(equipoDao.get(equi.getIdEquipo()));
            });
            cliente.setEquiposCliente(equiposCliente);

        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return cliente;
    }

    @Override
    public boolean updateCliente(Cliente cliente) {
        boolean respuesta = false;
        try {

            conexion.ConexionPostgres();
            respuesta = clienteDao.update(cliente);
            for (EquipoCliente equipo : cliente.getEquiposCliente()) {
                if (equipo.getId() == 0) {
                    equipoClienteDao.save(equipo);
                }
            }

        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return respuesta;

    }

    @Override
    public boolean deleteCliente(int id) {
        boolean respuesta = false;
        try {
            conexion.ConexionPostgres();
            respuesta = clienteDao.delete(id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return respuesta;

    }

    @Override
    public boolean saveBujia(Bujia bujia) {
        boolean result = false;
        try {
            conexion.ConexionPostgres();
            conexion.setAutoCommit(false);
            Repuesto repuesto = bujia.getRepuesto();
            repuestoDao.save(repuesto);
            bujia.setId(repuesto.getId());
            result = bujiaDao.save(bujia);
            conexion.commit();
            return result;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            try {
                conexion.rollback();
                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return false;
    }

    @Override
    public ArrayList<Bujia> getAllBujia() {
        ArrayList<Bujia> lista = null;
        try {
            conexion.ConexionPostgres();
            lista = bujiaDao.getAll();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return lista;
    }

    @Override
    public Bujia getBujia(int id) {
        Bujia bujia = null;
        try {
            conexion.ConexionPostgres();
            bujia = bujiaDao.get(id);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return bujia;

    }

    @Override
    public boolean updateBujia(Bujia bujia) {
        boolean respuesta = false;
        try {
            conexion.ConexionPostgres();
            conexion.setAutoCommit(false);
            Repuesto repuesto = bujia.getRepuesto();
            repuestoDao.update(repuesto);
            respuesta = bujiaDao.update(bujia);
            conexion.commit();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            conexion.cerrar();
        }
        return respuesta;

    }

    @Override
    public boolean deleteBujia(int id) {
        boolean respuesta = false;
        try {
            conexion.ConexionPostgres();
            respuesta = bujiaDao.delete(id);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return respuesta;

    }

    @Override
    public boolean saveRepuesto(Repuesto repuesto) {
        boolean respuesta = false;
        try {

            conexion.ConexionPostgres();
            respuesta = repuestoDao.save(repuesto);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return respuesta;
    }

    @Override
    public ArrayList<Repuesto> getAllRepuesto() {
        ArrayList<Repuesto> lista = null;

        try {
            conexion.ConexionPostgres();
            lista = repuestoDao.getAll();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return lista;

    }

    @Override
    public ArrayList<Repuesto> getAllRepuesto(String typeSpare) {
        ArrayList<Repuesto> lista = null;
        try {
            conexion.ConexionPostgres();
            if (typeSpare.equalsIgnoreCase("TODOS")) {
                lista = repuestoDao.getAll();
            } else {
                lista = repuestoDao.getAll(typeSpare);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }

        return lista;
    }

    @Override
    public ArrayList<Repuesto> searchAllRepuesto(String keyWord) {
        ArrayList<Repuesto> lista = null;
        try {
            conexion.ConexionPostgres();
            lista = repuestoDao.getAllSearch(keyWord);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }

        return lista;
    }

    @Override
    public Repuesto getRepuesto(int id) {
        Repuesto repuesto = null;
        try {
            conexion.ConexionPostgres();
            repuesto = repuestoDao.get(id);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return repuesto;
    }

    @Override
    public boolean updateRepuesto(Repuesto repuesto) {
        boolean respuesta = false;
        try {
            conexion.ConexionPostgres();
            respuesta = repuestoDao.update(repuesto);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();

        }
        return respuesta;
    }

    @Override
    public boolean deleteRepuesto(int id) {
        boolean respuesta = false;
        try {
            conexion.ConexionPostgres();
            respuesta = repuestoDao.delete(id);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return respuesta;
    }

    @Override
    public boolean saveTecnico(Tecnico tecnico) {
        boolean respuesta = false;
        try {
            conexion.ConexionPostgres();
            respuesta = tecnicoDao.save(tecnico);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return respuesta;
    }

    @Override
    public ArrayList<Tecnico> getAllTecnico() {
        ArrayList<Tecnico> lista = null;
        try {
            conexion.ConexionPostgres();
            lista = tecnicoDao.getAll();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return lista;
    }

    @Override
    public Tecnico getTecnico(int id) {
        Tecnico tecnico = null;
        try {
            conexion.ConexionPostgres();
            tecnico = tecnicoDao.get(id);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return tecnico;
    }

    @Override
    public boolean updateTecnico(Tecnico tecnico) {
        boolean respuesta = false;
        try {
            conexion.ConexionPostgres();
            respuesta = tecnicoDao.update(tecnico);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return respuesta;
    }

    @Override
    public boolean deleteTecnico(int id) {
        boolean respuesta = false;
        try {
            conexion.ConexionPostgres();
            respuesta = tecnicoDao.delete(id);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return respuesta;
    }

    @Override
    public boolean saveUsuario(Usuario usuario) {
        boolean respuesta = false;
        try {
            conexion.ConexionPostgres();
            respuesta = usuarioDao.save(usuario);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return respuesta;
    }

    @Override
    public ArrayList<Usuario> getAllUsuario() {
        ArrayList<Usuario> lista = null;
        try {
            conexion.ConexionPostgres();
            lista = usuarioDao.getAll();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return lista;
    }

    @Override
    public Usuario getUsuario(int id) {
        Usuario usuario = null;
        try {
            conexion.ConexionPostgres();
            usuario = usuarioDao.get(id);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return usuario;
    }

    @Override
    public boolean updateUsuario(Usuario usuario) {
        boolean respuesta = false;
        try {
            conexion.ConexionPostgres();
            respuesta = usuarioDao.update(usuario);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return respuesta;
    }

    @Override
    public boolean deleteUsuario(int id) {
        boolean respuesta = false;
        try {
            conexion.ConexionPostgres();
            respuesta = usuarioDao.delete(id);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return respuesta;
    }

    public boolean deleteEquipoCliente(int id) {
        boolean respuesta = false;
        try {

            conexion.ConexionPostgres();
            respuesta = equipoClienteDao.delete(id);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

    @Override
    public boolean saveMantenimiento(Mantenimiento mantenimiento) {
        boolean respuesta = false;
        try {
            conexion.ConexionPostgres();
            conexion.getCon().setAutoCommit(false);

            respuesta = mantenimientoDao.save(mantenimiento);

            for (MantenimientoEquipo mantEquipo : mantenimiento.getMantenimientoEquipo()) {
                for (MantenimientoRepuesto respuestoMant : mantEquipo.getRepuestos()) {
                    respuesta = respuesta && detailMaintenanceDao.save(respuestoMant);
                }
            }

            conexion.getCon().commit();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
            try {
                conexion.getCon().rollback();
            } catch (SQLException ex2) {

            }
        }
        if (respuesta) {
            System.out.println(mantenimiento.getId());
            generarRecibo(mantenimiento);
        }
        return respuesta;

    }

    public void generarRecibo(Mantenimiento mantenimiento) {
        try {
            Recibo.generarReporte(mantenimiento);
        } catch (JRException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Usuario loginUsuario(String username, String paswword) {
        Usuario usuario = null;
        try {
            conexion.ConexionPostgres();
            usuario = usuarioDao.login(username, paswword);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    //****************************************************** TIPO REPUESTO
    @Override
    public ArrayList<TipoRepuesto> getTipoRepuestos() {
        ArrayList<TipoRepuesto> lista = null;
        try {
            conexion.ConexionPostgres();
            lista = tipoRepuestoDao.getAll();
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return lista;
    }

    @Override
    public boolean saveTipoRepuesto(TipoRepuesto tipoRepuesto) {
        boolean respuesta = false;
        try {
            conexion.ConexionPostgres();
            respuesta = tipoRepuestoDao.save(tipoRepuesto);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return respuesta;
    }

    @Override
    public boolean updateTipoRepuesto(TipoRepuesto tipoRepuesto) {
        boolean respuesta = false;
        try {
            conexion.ConexionPostgres();
            respuesta = tipoRepuestoDao.update(tipoRepuesto);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexion.cerrar();
        }
        return respuesta;
    }

    @Override
    public boolean deleteTipoRepuesto(int id) {
        boolean respuesta = false;
        try {
            conexion.ConexionPostgres();
            respuesta = tipoRepuestoDao.delete(id);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

}
