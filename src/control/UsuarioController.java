/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import View.Principal;
import View.Usuario.UsuarioGui2;
import View.Usuario.UsuarioLista;

/**
 *
 * @author blade
 */
public class UsuarioController {
    private static UsuarioController usuarioController;
    Principal principal;
    UsuarioGui2 usuarioGui;
    UsuarioLista usuarioListaGui;
    
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
    
    public void listarGui() {
        usuarioListaGui = UsuarioLista.getEstancia();
        //this.getListar();
        principal.mostrarInternal(usuarioListaGui);
    }
    
    
    
    
    
}
