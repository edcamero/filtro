/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Models.Bujia;
import View.Bujia.BujiaLista;
import View.Bujia.BujiaNueva;
import View.Principal;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import logica.Fachada;

/**
 *
 * @author blade
 */
public class BujiaController {

    private static BujiaController bujiaController;
    Principal principal;
    BujiaNueva bujiaNuevaGui;
    Bujia bujia;

    private BujiaController() {
        principal = Principal.getInstance();

    }

    public static BujiaController getInstancia() {
        if (bujiaController == null) {
            bujiaController = new BujiaController();
        }
        return bujiaController;
    }

    public void agregarGui() {
        bujiaNuevaGui = BujiaNueva.getInstancia();
        principal.mostrarInternal(bujiaNuevaGui);

    }

    public void saveBujia() {
        String nombre = bujiaNuevaGui.getTextNombre().getText();
        int costo = Integer.parseInt(bujiaNuevaGui.getTextCosto().getText());
        int valor = Integer.parseInt(bujiaNuevaGui.getTextValor().getText());
        int vidaUtil = Integer.parseInt(bujiaNuevaGui.getTextVida().getText());
        Bujia bujia = new Bujia(nombre, vidaUtil, costo, valor);
        if (Fachada.getInstancia().saveBujia(bujia)) {
            JOptionPane.showMessageDialog(bujiaNuevaGui, "se agrego la bujia");
            bujiaNuevaGui.limpiar();
        } else {
            JOptionPane.showMessageDialog(bujiaNuevaGui, "Error a agregar la bujia");
        }

    }

    public void listaGui() {
        BujiaLista bujiaListaGui = BujiaLista.getInstancia();
        principal.mostrarInternal(bujiaListaGui);
        this.listar(bujiaListaGui);
    }

    private void listar(BujiaLista bujiaListaGui) {

        DefaultTableModel model = (DefaultTableModel) bujiaListaGui.getTabla().getModel();
        Object[] fila;
        model.setRowCount(0);
        for (Bujia bujia : Fachada.getInstancia().getAllBujias()) {
            fila = new Object[5];
            fila[0] = bujia.getId();
            fila[1] = bujia.getNombre();
            fila[2] = bujia.getVidaUtil();
            fila[3] = bujia.getValorCosto();
            fila[4] = bujia.getValorVenta();
            model.addRow(fila);
        }

    }

    public Bujia buscar(int id) {
        return Fachada.getInstancia().getBujia(id);

    }

    public void editarBujiaGui(int id) {
        Bujia bujia = Fachada.getInstancia().getBujia(id);
//        editarGui = BujiaEditar.getinstancia(bujia);
//        principal.mostrarInternal(editarGui);

    }

    public boolean editarBujia(Bujia bujia) {
        if (Fachada.getInstancia().updateBujia(bujia)) {
            listaGui();
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminarBujia(int id) {
        if (Fachada.getInstancia().deleteBujia(id)) {
            this.listar(BujiaLista.getInstancia());
            return true;
        }
        return false;
    }
}
