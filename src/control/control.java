/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import Models.Usuario;
import View.Principal;
import View.Usuario.UsuarioLogin;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Config;

/**
 *
 * @author blade
 */
public class control {

    static Usuario user;
    static Principal panel;

    public static synchronized Usuario getUser() {
        return user;
    }

    public static synchronized void setUser(Usuario user) {
        control.user = user;
    }

    public static Principal getPanel() {
        return panel;
    }

    public static void setPanel(Principal panel) {
        control.panel = panel;
    }

    public static void instanciar() {
        panel = Principal.getInstance(getUser());
        panel.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Config.loadConfig();
        new UsuarioLogin(user).setVisible(true);
    }

}
