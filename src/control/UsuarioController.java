/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Models.Usuario;
import View.Principal;
import View.Usuario.UsuarioGui2;
import javax.swing.JOptionPane;
import logica.Fachada;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author blade
 */
public class UsuarioController {
    private static UsuarioController usuarioController;
    Principal principal;
    UsuarioGui2 usuarioGui;
    
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
        usuarioGui=UsuarioGui2.getIntancia();
        principal.mostrarInternal(usuarioGui);
    }
    
    
    
    
    
}
