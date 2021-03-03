/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Models.Equipo;
import Models.Repuesto;
import View.Principal;
import View.Repuesto.RepuestoListar;
import View.Repuesto.RepuestoNuevoGui;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.Fachada;

/**
 *
 * @author blade
 */
public class RepuestoController {

    private static RepuestoController repuestoController;
    private EquipoController equipoControl;
    Principal principal;
    private static ArrayList<Repuesto> repuestos;
    RepuestoNuevoGui repuestoGui;
    Fachada fachada;
    private JInternalFrame gui;
    RepuestoListar repuestoListaGui;

    private RepuestoController() {
        principal = Principal.getInstance();
        fachada = Fachada.getInstancia();
    }

    public static RepuestoController getInstancia() {
        if (repuestoController == null) {
            repuestoController = new RepuestoController();
        }
        return repuestoController;
    }

    public void setGui(JInternalFrame gui) {
        this.gui = gui;
    }

    public void agregarGui() {
        repuestoGui = RepuestoNuevoGui.getInstancia();
        principal.mostrarInternal(repuestoGui);
        this.getListar();
    }

    public void listarGui() {
        repuestoListaGui = RepuestoListar.getEstancia();
        //this.getListar();
        principal.mostrarInternal(repuestoListaGui);
    }

    public void saveRepuesto() {
        String nombre = repuestoGui.getTextNombre().getText();
        int costo = Integer.parseInt(repuestoGui.getTextCosto().getText());
        int valor = Integer.parseInt(repuestoGui.getTextValor().getText());
        int tipo= repuestoGui.getIdTipoRepuestoComboBox();
        
        Repuesto repuesto = new Repuesto(nombre, costo, valor ,tipo);
        if (Fachada.getInstancia().saveRepuesto(repuesto)) {
            JOptionPane.showMessageDialog(repuestoGui, "se agrego el Repuesto");

            this.getListar();
            this.limpiar();

        } else {
            JOptionPane.showMessageDialog(repuestoGui, "Error al agregar Repuesto");
        }
    }

    public void getListar() {
        DefaultTableModel model = (DefaultTableModel) this.repuestoGui.getTabla().getModel();
        Object[] fila;
        model.setRowCount(0);
        for (Repuesto repuesto : Fachada.getInstancia().getAllRepuesto()) {
                fila = new Object[7];
                fila[0] = repuesto.getId();
                fila[1] = repuesto.getNombre();
                fila[2] = repuesto.getTipo();
                fila[3] = repuesto.getValorCosto();
                fila[4] = repuesto.getValorVenta();
                fila[5] = repuesto.getIva();
                fila[6] = repuesto.getValorVentaIva();
                model.addRow(fila);
        }
    }

    private void limpiar() {
        repuestoGui.getTextNombre().setText("");
        repuestoGui.getTextCosto().setText("");
        repuestoGui.getTextValor().setText("");

    }

    public Repuesto buscar(int id) {
        return fachada.getRepuesto(id);
    }

    public boolean actualizar(Repuesto repuesto) {

        if (fachada.updateRepuesto(repuesto)) {
            this.getListar();
            this.limpiar();
            return true;
        }
        return false;
    }

    public boolean eliminar(int id) {
        if (fachada.deleteRepuesto(id)) {
            this.getListar();
            return true;
        }
        return false;
    }

}
