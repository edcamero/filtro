/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Models.Bujia;
import Models.Cliente;
import Models.Tecnico;
import Models.Mantenimiento;
import Models.MantenimientoEquipo;
import Models.Repuesto;
import View.Mantenimiento.MantenimientoGui;
import View.Mantenimiento.RepuestoMant;
import View.Principal;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import logica.Fachada;

/**
 *
 * @author blade
 */
public class MantenimientoController {

    private Fachada fachada;
    private MantenimientoGui mantenimientoGui;
    private RepuestoMant repuestoGui;
    private static MantenimientoController mantenimientoControl;
    private Cliente cliente;
    private Mantenimiento mantenimiento;
    private ArrayList<Tecnico> tecnicos;
    MantenimientoEquipo mantEquipo;

    private MantenimientoController() {
        fachada = Fachada.getInstancia();
    }

    public static MantenimientoController getInstancia() {
        if (mantenimientoControl == null) {
            mantenimientoControl = new MantenimientoController();
        }

        return mantenimientoControl;
    }

    public void setCliente(Cliente cliente) {
        mantenimiento = new Mantenimiento(cliente);
        this.cliente = cliente;
    }

    public MantenimientoGui getMantenimientoGui() {
        return mantenimientoGui;
    }

    public void setMantenimientoGui(MantenimientoGui mantenimientoGui) {
        this.mantenimientoGui = mantenimientoGui;
    }

    public void nuevoMantenimientoGui(int id) {
        this.cliente = fachada.getCliente(id);
        mantenimiento = new Mantenimiento(cliente);
        this.tecnicos = fachada.getAllTecnico();
        this.mantenimientoGui = (MantenimientoGui) MantenimientoGui.getInstancia(mantenimiento);
        this.mantenimientoGui.mostrarDatos();
        Principal.getInstance().mostrarInternal(mantenimientoGui);

    }

    public void actualizarFormulario() {
        mantenimientoGui.mostrarDatos();
        mantenimientoGui.mostrarDatosTabla();
        mantenimientoGui.mostrarDetalle(mantEquipo.getId());

    }

    public Mantenimiento getMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(Mantenimiento mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

    public void agregarRepuestoGui() {
        repuestoGui = RepuestoMant.getInstancia();
        repuestoGui.setRepuestos(fachada.getAllRepuesto());
        repuestoGui.setBujias(fachada.getAllBujias());
        Principal.getInstance().mostrarInternal(repuestoGui);
    }

    public void agregarRepuesto(int cant, Repuesto repuesto) {

        //RepuestoMante repuestoMant=new RepuestoMante(mantEquipo, repuesto, cant);
        mantEquipo.agregarRepuestos(repuesto, cant);
        mantenimientoGui.mostrarDetalle();
    }

    public void eliminarRepuesto(int id) {
        mantEquipo.eliminarRepuesto(id);
    }

    public void agregarBujia(Bujia bujia) {
        mantEquipo.eliminarBujia();
        mantEquipo.setBujia(bujia);
    }

    public void agregarRepuestoGui(int index) {
        mantEquipo = mantenimiento.getMantenimientoEquipo().get(index);
        //repuestoGui.setMantEquipo(mantEquipo);

        repuestoGui = RepuestoMant.getInstancia();
        repuestoGui.setRepuestos(fachada.getAllRepuesto());
        repuestoGui.setBujias(fachada.getAllBujias());
        Principal.getInstance().mostrarInternal(repuestoGui);
    }

    public void nuevoMantenimientoEquipo(int index) {
        // Equipo equipo=mantenimiento.getCliente().getEquiposCliente().get(index).getEquipo();
        MantenimientoEquipo mantEquipo = new MantenimientoEquipo(mantenimiento, mantenimiento.getCliente().getEquiposCliente().get(index - 1));
        if (mantenimiento.getMantenimientoEquipo().indexOf(mantEquipo) >= 0) {
            JOptionPane.showMessageDialog(null, "Ya selecciono este equipo", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            mantenimiento.agregarMantenimientoEquipo(mantEquipo);
            mantenimientoGui.agregarEquipotabla(mantEquipo);
        }

    }

    public void seleccionarTecnico(int index) {
        Tecnico tecnico = tecnicos.get(index - 1);
        mantenimiento.setTecnico(tecnico);
    }

    public void agregarMantenimiento() {
        if (fachada.saveMantenimiento(mantenimiento)) {
            JOptionPane.showMessageDialog(repuestoGui, "se agrego el mantenimieto con exito");
        }
    }

}
