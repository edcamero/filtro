/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import logica.Fachada;
import Models.Cliente;
import Models.Equipo;
import Models.EquipoCliente;
import View.Cliente.ClienteEquipos;
import View.Cliente.ClienteLista;
import View.Cliente.ClienteNuevo;
import View.Cliente.ClienteVer;
import View.Principal;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author blade
 */
public class ClienteController {

    private static ClienteController Controlador;

    private ClienteNuevo nuevoGui;
    private ClienteLista listaGui;
    private ClienteVer verGui;
    private ClienteEquipos equiposGui;
    private static ArrayList<Cliente> clientes;
    private Principal principal;
    private Fachada fachada;
    private Cliente cliente;

    private ClienteController() {
        principal = Principal.getInstance();
        fachada = Fachada.getInstancia();
    }

    public static ClienteController getInstancia() {
        if (Controlador == null) {
            Controlador = new ClienteController();
            clientes = new ArrayList<>();
        }

        return Controlador;
    }

    public void NuevoClienteGui() {
        nuevoGui = ClienteNuevo.getInstancia();
        principal.mostrarInternal(nuevoGui);
    }

    public boolean agregar() {
        nuevoGui = ClienteNuevo.getInstancia();
        String documento = nuevoGui.getTextDoc().getText();
        String nombre = nuevoGui.getTextNombre().getText();
        String tel_uno = nuevoGui.getTextTel1().getText();
        String tel_dos = nuevoGui.getTextTel2().getText();
        String direccion = nuevoGui.getTextDir().getText();
        String email = nuevoGui.getTextEmail().getText();
        Cliente nuevo = new Cliente(documento, nombre, tel_uno, tel_dos, direccion, email);

        //System.out.println(nuevo.toString());
        return fachada.saveCliente(nuevo);

    }

    public void Listar() {
        listaGui = ClienteLista.getInstancia();
        this.clientes.clear();
        this.clientes = Fachada.getInstancia().getAllClientes();

        DefaultTableModel model = (DefaultTableModel) listaGui.getTabla().getModel();
        model.setRowCount(0);

        clientes.forEach((a) -> {
            model.addRow(new Object[]{a.getId(), a.getDocumento(), a.getNombre(), a.getTelefonoUno() + "-" + a.getTelefonoDos(), a.getDireccion(), a.getEmail()});
        });

        principal.mostrarInternal(listaGui);

    }

    private String nameColumn(int column)
    {
        return "";
    }
    public void Buscar(String palabra, int column) {
        listaGui = ClienteLista.getInstancia();
        this.clientes.clear();
        
        this.clientes = Fachada.getInstancia().buscarClientes(palabra,nameColumn(column));

        DefaultTableModel model = (DefaultTableModel) listaGui.getTabla().getModel();
        model.setRowCount(0);

        clientes.forEach((a) -> {
            model.addRow(new Object[]{a.getId(), a.getDocumento(), a.getNombre(), a.getTelefonoUno() + "-" + a.getTelefonoDos(), a.getDireccion(), a.getEmail()});
        });

        principal.mostrarInternal(listaGui);

    }

    public boolean editar() {
        boolean res = false;
        Cliente cliente = verGui.getCliente();
        cliente.setDocumento(verGui.getTextDoc().getText());
        cliente.setNombre(verGui.getTextNombre().getText());
        cliente.setTelefonoUno(verGui.getTextTel1().getText());
        cliente.setTelefonoDos(verGui.getTextTel2().getText());
        cliente.setDireccion(verGui.getTextDir().getText());
        cliente.setEmail(verGui.getTextEmail().getText());

        res = fachada.updateCliente(cliente);

        this.VerGui(cliente.getId());
        listaGui.setVisible(false);

        return res;

    }

    public boolean eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Cliente buscar(int id) {
        return fachada.getCliente(id);

    }

    public void seleccionEquiposGui() {
        equiposGui = ClienteEquipos.getInstancia();
        principal.mostrarInternal(equiposGui);
        ArrayList<Equipo> equipos = Fachada.getInstancia().getAllEquipo();

        DefaultTableModel model = (DefaultTableModel) equiposGui.getTabla().getModel();
        model.setRowCount(0);

        for (Equipo e : equipos) {
            model.addRow(new Object[]{e.getId(), e.getMaterial(), e.getModelo(), e.getNombre(), e.getPrecio()});
        }
        //equipoListaGui.getTabla().setModel(model);
    }

    public void seleccionEquipos(int idEquipo) {
        Equipo equipo = fachada.getEquipo(idEquipo);
        EquipoCliente equipoCliente = new EquipoCliente(cliente, equipo);
        this.cliente.getEquiposCliente().add(equipoCliente);
    }

    public void guardarEquipos() {

        if (cliente != null) {
            fachada.updateCliente(cliente);
            listaGui.setVisible(false);
            VerGui(cliente.getId());

        }

    }

    public void VerGui(int id) {
        this.cliente = buscar(id);
        verGui = ClienteVer.getInstancia(cliente);

        principal.mostrarInternal(verGui);

    }

    public void verGui() {
        if (this.cliente != null) {

            principal.mostrarInternal(verGui);

        }

    }

    public boolean eliminarEquipo(int index) {
        EquipoCliente equipoCliente = cliente.getEquiposCliente().get(index);
        if (fachada.deleteEquipoCliente(equipoCliente.getId())) {
            cliente.getEquiposCliente().remove(index);
            return true;
        }
        return false;
    }

}
