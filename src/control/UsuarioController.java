/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Models.Usuario;
import View.Principal;
import View.Usuario.UsuarioNuevo;
import javax.swing.JOptionPane;
import logica.Fachada;

/**
 *
 * @author blade
 */
public class UsuarioController {
    private static UsuarioController usuarioController;
    Principal principal;
    UsuarioNuevo usuarioNuevo;
    
    private UsuarioController(){
        principal = Principal.getInstance();
    }
    
    public static UsuarioController getInstancia(){
        if ( usuarioController==null) {
            usuarioController=new UsuarioController();
        }
        return usuarioController;
    }
    
    public void agregarGui(){
        usuarioNuevo=UsuarioNuevo.getIntancia();
        principal.mostrarInternal(usuarioNuevo);
    }
    
    
    public void saveUsuario(){
        String nombre=usuarioNuevo.getTextNombre().getText();
        String password=usuarioNuevo.getTextPassword().getText();
        Usuario usuario=new Usuario(nombre, password);
        if ( Fachada.getIntacia().saveUsuario(usuario)) {
            JOptionPane.showMessageDialog(usuarioNuevo, "se agrego un nuevo Usuario");
            usuarioNuevo.limpiar();
        }else{
            JOptionPane.showMessageDialog(usuarioNuevo, "Error a agregar el usuario","Mensaje de Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
}
