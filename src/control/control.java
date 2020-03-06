/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import View.Principal;
import logica.Config;

/**
 *
 * @author blade
 */
public class control {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Config.loadConfig();
        Principal panel=Principal.getInstance();
        panel.mostrar();
    }
    
}
