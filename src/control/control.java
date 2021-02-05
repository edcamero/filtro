/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Models.Usuario;
import View.Principal;
import View.Usuario.UsuarioLogin;
import logica.Config;

/**
 *
 * @author blade
 */
public class control {

    static Usuario user;
    static Principal panel;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Config.loadConfig();
        UsuarioLogin loginGui = new UsuarioLogin(user);
        loginGui.setVisible(true);
        if (user != null) {
//        if (true) {
            panel = Principal.getInstance();
            panel.mostrar();
        }

    }

}
