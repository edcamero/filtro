/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import logica.Fachada;
import Models.Equipo;
//import View.Equipo.EquipoEditar;
import View.Equipo.EquipoLista;
import View.Equipo.EquipoNuevo;
import View.Principal;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author blade
 */
public class EquipoController {

    private static EquipoController equipoController;
    private static ArrayList<Equipo> equipos;
    private JInternalFrame gui;
    //private EquipoEditar editarGui;
    private EquipoLista equipoListaGui;
    Principal principal;

    private EquipoController() {
        principal = Principal.getInstance();
    }

    public static EquipoController getInstancia() {
        if (equipoController == null) {
            equipoController = new EquipoController();
            equipos = new ArrayList<Equipo>();
        }
        return equipoController;
    }

    public void setGui(JInternalFrame gui) {
        this.gui = gui;
    }

    public boolean agregar() {
        EquipoNuevo panel = (EquipoNuevo) this.gui;
        String material = (String) panel.getComboMaterial().getSelectedItem();
        String modelo = panel.getTextModelo().getText();
        String nombre = panel.getTextNombre().getText();
        String color = panel.getTextColor().getText();
        int precio = Integer.parseInt(panel.getTextPrecio().getText());
        Equipo nuevo = new Equipo(material, modelo, nombre,color, precio);
        return Fachada.getInstancia().saveEquipo(nuevo);
    }

    public void listarGui() {
        equipoListaGui = EquipoLista.getEstancia();
        this.listar();
        principal.mostrarInternal(equipoListaGui);
    }

    private void listar() {
        this.equipos.clear();
        equipos = Fachada.getInstancia().getAllEquipo();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("MATERIAL");
        model.addColumn("MODELO");
        model.addColumn("NOMBRE");
         model.addColumn("COLOR");
        model.addColumn("PRECIO");

        for (Equipo e : equipos) {
            model.addRow(new Object[]{e.getId(), e.getMaterial(), e.getModelo(), e.getNombre(),e.getColor(), e.getPrecio()});
        }
        equipoListaGui.getTabla().setModel(model);
    }

    public boolean eliminar(int id) {
        return Fachada.getInstancia().deleteEquipo(id);
    }

    public void editarGui(int id) {
        Equipo equipo = Fachada.getInstancia().getEquipo(id);
        System.out.println(equipo.toString());
//        editarGui = EquipoEditar.getInstancia(equipo);
//        principal.mostrarInternal(editarGui);

    }

    public boolean editar(Equipo equipo) {
        if (Fachada.getInstancia().updateEquipo(equipo)) {
            this.listarGui();
            return true;
        }

        return false;
    }

    public Equipo Buscar(int id) {
        //System.out.println(id);
        for (Equipo e : equipos) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

}
