/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import Models.Bujia;
import Models.Cliente;
import Models.Equipo;
import Models.Mantenimiento;
import Models.Repuesto;
import Models.Tecnico;
import Models.TipoRepuesto;
import Models.Usuario;
import java.util.ArrayList;

/**
 *
 * @author blade
 */
public class Fachada {

    private static Fachada fachada;
    private Mediador mediador;

    private Fachada() {
        mediador = new Mediador();
    }

    public static Fachada getInstancia() {
        if (fachada == null) {
            fachada = new Fachada();
        }
        return fachada;

    }

    public ArrayList<Equipo> getAllEquipo() {
        return mediador.getAllEquipo();

    }

    public boolean saveEquipo(Equipo equipo) {
        return mediador.saveEquipo(equipo);
    }

    public boolean updateEquipo(Equipo equipo) {
        return mediador.updateEquipo(equipo);
    }

    public Equipo getEquipo(int id) {
        return mediador.getEquipo(id);
    }

    public boolean deleteEquipo(int id) {
        return mediador.deleteEquipo(id);
    }

    public boolean saveCliente(Cliente cliente) {
        return mediador.saveCliente(cliente);
    }

    public ArrayList<Cliente> getAllClientes() {
        return mediador.getAllCliente();
    }

    public ArrayList<Cliente> buscarClientes(String palabra, String column) {
        return mediador.buscarCliente(palabra, column);
    }

    public Cliente getCliente(int id) {
        return mediador.getCliente(id);
    }

    public Cliente getCliente(String word, String column) {
        return mediador.getCliente(word, column);
    }

    public boolean updateCliente(Cliente cliente) {
        return mediador.updateCliente(cliente);
    }
    public boolean deleteCliente(int id){
        return mediador.deleteCliente(id);
    }

    public boolean saveBujia(Bujia bujia) {
        return mediador.saveBujia(bujia);
    }

    public boolean updateBujia(Bujia bujia) {
        return mediador.updateBujia(bujia);
    }

    public boolean deleteBujia(int id) {
        return mediador.deleteBujia(id);
    }

    public ArrayList<Bujia> getAllBujias() {
        return mediador.getAllBujia();
    }

    public Bujia getBujia(int id) {
        return mediador.getBujia(id);
    }

    public boolean saveRepuesto(Repuesto repuesto) {
        return mediador.saveRepuesto(repuesto);
    }

    public ArrayList<Repuesto> getAllRepuesto() {
        return mediador.getAllRepuesto();
    }

    public Repuesto getRepuesto(int id) {
        return mediador.getRepuesto(id);

    }

    public boolean updateRepuesto(Repuesto repuesto) {
        return mediador.updateRepuesto(repuesto);
    }

    public boolean deleteRepuesto(int id) {
        return mediador.deleteRepuesto(id);
    }

    public boolean saveTecnico(Tecnico tecnico) {
        return mediador.saveTecnico(tecnico);
    }

    public ArrayList<Tecnico> getAllTecnico() {
        return mediador.getAllTecnico();
    }

    public Tecnico getTecnico(int id) {
        return mediador.getTecnico(id);
    }

    public boolean updateTecnico(Tecnico tecnico) {
        return mediador.updateTecnico(tecnico);
    }

    public boolean deleteTecnico(int id) {
        return mediador.deleteTecnico(id);
    }

    public boolean saveUsuario(Usuario usuario) {
        return mediador.saveUsuario(usuario);
    }

    public ArrayList<Usuario> getAllUsuario() {
        return mediador.getAllUsuario();
    }

    public Usuario getUsuario(int id) {
        return mediador.getUsuario(id);
    }

    public boolean updateUsuario(Usuario usuario) {
        return mediador.updateUsuario(usuario);
    }

    public boolean deleteUsuario(int id) {
        return mediador.deleteUsuario(id);
    }

    public boolean deleteEquipoCliente(int id) {
        return mediador.deleteEquipoCliente(id);
    }

    public boolean saveMantenimiento(Mantenimiento mantenimiento) {
        return mediador.saveMantenimiento(mantenimiento);
    }

    public Usuario login(String username, String password) {
        return mediador.loginUsuario(username, password);
    }

    //**************************************************TIPO REPUESTO*******************************
    public ArrayList<TipoRepuesto> getTipoRepuestos() {
        return mediador.getTipoRepuestos();
    }

    public boolean saveTipoRespuesto(TipoRepuesto tipoRepuesto) {
        return mediador.saveTipoRepuesto(tipoRepuesto);
    }

    public boolean updateTipoRespuesto(TipoRepuesto tipoRepuesto) {
        return mediador.updateTipoRepuesto(tipoRepuesto);
    }

    public boolean deleteTipoRespuesto(int id) {
        return mediador.deleteTipoRepuesto(id);
    }

}
