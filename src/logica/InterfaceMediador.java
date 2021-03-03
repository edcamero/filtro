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
import Models.Usuario;
import Models.TipoRepuesto;
import java.util.ArrayList;

/**
 *
 * @author blade
 */
public interface InterfaceMediador {

    public boolean saveEquipo(Equipo equipo);

    public ArrayList<Equipo> getAllEquipo();

    public Equipo getEquipo(int id);

    public boolean updateEquipo(Equipo equipo);

    public boolean deleteEquipo(int id);

    
    public boolean saveCliente(Cliente cliente);

    public ArrayList<Cliente> getAllCliente();

    public Cliente getCliente(int id);

    public boolean updateCliente(Cliente cliente);

    public boolean deleteCliente(int id);

    
    
    
    public boolean saveBujia(Bujia bujia);

    public ArrayList<Bujia> getAllBujia();

    public Bujia getBujia(int id);

    public boolean updateBujia(Bujia bujia);

    public boolean deleteBujia(int id);
    
    
    public boolean saveRepuesto(Repuesto repuesto);

    public ArrayList<Repuesto> getAllRepuesto();

    public Repuesto getRepuesto(int id);

    public boolean updateRepuesto(Repuesto repuesto);

    public boolean deleteRepuesto(int id);
    
    
    public boolean saveTecnico(Tecnico tecnico);

    public ArrayList<Tecnico> getAllTecnico();

    public Tecnico getTecnico(int id);

    public boolean updateTecnico(Tecnico tecnico);

    public boolean deleteTecnico(int id);


    public boolean saveUsuario(Usuario usuario);

    public ArrayList<Usuario> getAllUsuario();

    public Usuario getUsuario(int id);

    public boolean updateUsuario(Usuario usuario);

    public boolean deleteUsuario(int id);
    
    public Usuario loginUsuario(String username,String paswword);
    
    public boolean saveMantenimiento(Mantenimiento manteniemiento);
    
    
    
    //********************************************************* METODOS DE TIPO REPUESTOS
    public ArrayList<TipoRepuesto> getTipoRepuestos();
    public boolean saveTipoRepuesto(TipoRepuesto tipoRepuesto);
    
    public boolean updateTipoRepuesto(TipoRepuesto tipoRepuesto);

}