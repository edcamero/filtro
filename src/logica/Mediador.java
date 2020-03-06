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
import DAO.MantenimientoEquipoDao;
import DAO.RepuestoDao;
import DAO.TecnicoDao;
import DAO.UsuarioDao;

import Models.Bujia;
import Models.Cliente;
import Models.Equipo;
import Models.EquipoCliente;
import Models.Mantenimiento;
import Models.MantenimientoEquipo;
import Models.Repuesto;
import Models.Tecnico;
import Models.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    TecnicoDao tecnicoDao = new TecnicoDao(conexion);
    UsuarioDao usuarioDao = new UsuarioDao(conexion);
    MantenimientoDao mantenimientoDao = new MantenimientoDao(conexion);
    MantenimientoEquipoDao mantEquiDao=new MantenimientoEquipoDao(conexion);

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

    public ArrayList<Cliente> buscarCliente(String palabra) {

        try {
            conexion.ConexionPostgres();
            return clienteDao.buscarClientes(palabra);
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
    public boolean updateCliente(Cliente cliente) {
        boolean respuesta = false;
        try {

            conexion.ConexionPostgres();
            clienteDao.update(cliente);
            for (EquipoCliente equipo : cliente.getEquiposCliente()) {
                if (equipo.getId() == 0) {
                    System.out.println(equipo.getId() + " " + equipo.getEquipo().getNombre());
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
        try {
            conexion.ConexionPostgres();
            return bujiaDao.save(bujia);
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
            respuesta = bujiaDao.update(bujia);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
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
    public boolean saveMantenimiento(Mantenimiento manteniemiento) {
        boolean respuesta = false;
        try {
            conexion.ConexionPostgres();
            conexion.getCon().setAutoCommit(false);
            
            respuesta = mantenimientoDao.save(manteniemiento);
            
            for(MantenimientoEquipo mantEquipo:manteniemiento.getMantenimientoEquipo()){
               respuesta=respuesta&&mantEquiDao.save(mantEquipo); 
            }
            
            conexion.getCon().commit(); 
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Mediador.class.getName()).log(Level.SEVERE, null, ex);
             try {
              conexion.getCon().rollback();
             } catch (SQLException ex2) {
                      System.out.println(ex2.toString());
            }
        }
        return respuesta;

    }
}
