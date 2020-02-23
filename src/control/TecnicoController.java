/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Models.Tecnico;
import View.Principal;
import View.Tecnico.TecnicoNuevo;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.Fachada;

/**
 *
 * @author blade
 */
public class TecnicoController {

    private static TecnicoController tecnicoController;
    Principal principal;
    TecnicoNuevo tecnicoNuevo;
    Fachada fachada;

    private TecnicoController() {
        principal = Principal.getInstance();
        fachada=Fachada.getIntacia();
    }

    public static TecnicoController getInstancia() {
        if (tecnicoController == null) {
            tecnicoController = new TecnicoController();
        }
        return tecnicoController;

    }

    public void agregarGui() {
        tecnicoNuevo = TecnicoNuevo.getInstancia();
        principal.mostrarInternal(tecnicoNuevo);
        this.getLista();
    }

    public void saveTecnico() {
        String documento = tecnicoNuevo.getTextDocumento().getText();
        String nombre = tecnicoNuevo.getTextNombre().getText();
        String telefono = tecnicoNuevo.getTextTelefono().getText();
        Tecnico tecnico = new Tecnico(documento, nombre, telefono);
        if (fachada.getIntacia().saveTecnico(tecnico)) {
            JOptionPane.showMessageDialog(tecnicoNuevo, "se agrego el nuevo Tecnico");
            this.getLista();
            this.limpiar();
        } else {
            JOptionPane.showMessageDialog(tecnicoNuevo, "Error al agregar el nuevo Tecnico");
        }

    }

    public void getLista() {
        DefaultTableModel model = (DefaultTableModel) tecnicoNuevo.getTabla().getModel();
        Object[] fila;
        model.setRowCount(0);
        for (Tecnico tecnico : Fachada.getIntacia().getAllTecnico()) {
            fila = new Object[4];
            fila[0] = tecnico.getId();
            fila[1] = tecnico.getNombre();
            fila[2] = tecnico.getDocumento();
            fila[3] = tecnico.getTelefonoUno();
            model.addRow(fila);
        }
    }

    private void limpiar() {
        tecnicoNuevo.getTextNombre().setText("");
        tecnicoNuevo.getTextDocumento().setText("");
        tecnicoNuevo.getTextTelefono().setText("");
    }
    
    public Tecnico getTecnico(int id){
        return Fachada.getIntacia().getTecnico(id);
    }

     public boolean eliminar(int id) {
        if (fachada.deleteTecnico(id)) {
            this.getLista();
            return true;
        }
        return false;
    }
     
      public boolean actualizar(Tecnico tecnico) {
        if (fachada.updateTecnico(tecnico)) {
            this.getLista();
            this.limpiar();
            return true;
        }
        return false;
    }
}
