/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Models.Cliente;
import Models.Tecnico;
import View.Mantenimiento.Mantenimiento;
import View.Mantenimiento.RepuestoMant;
import View.Principal;
import java.util.ArrayList;
import logica.Fachada;

/**
 *
 * @author blade
 */
public class MantenimientoController {

    private Fachada fachada;
    private Mantenimiento mantenimientoGui;
    private RepuestoMant repuestoGui;
    private static MantenimientoController mantenimientoControl;
    private Cliente cliente;
    private ArrayList<Tecnico> tecnicos;

    private MantenimientoController() {
        fachada = Fachada.getIntacia();
    }

    public static MantenimientoController getInstancia() {
        if (mantenimientoControl == null) {
            mantenimientoControl = new MantenimientoController();
        }

        return mantenimientoControl;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void nuevoMantenimientoGui(int id) {
        this.cliente = fachada.getCliente(id);

        this.mantenimientoGui = Mantenimiento.getInstancia(cliente);
        this.tecnicos = fachada.getAllTecnico();
        this.tecnicos.stream().forEach((Tecnico tecnico) -> {
            this.mantenimientoGui.getComboTecnico().addItem(tecnico.getNombre());
        });

        Principal.getInstance().mostrarInternal(mantenimientoGui);
       
    }
    
    
    public void agregarRepuestoGui(){
        repuestoGui=RepuestoMant.getInstancia();
        repuestoGui.setRepuestos(fachada.getAllRepuesto());
        repuestoGui.setBujias(fachada.getAllBujias());
        Principal.getInstance().mostrarInternal(repuestoGui);
    }

}
