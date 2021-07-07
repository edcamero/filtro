/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author blade
 */
public class Config {
    public static int costoMantenimiento;
    public static float iva;
    public static String ruta;

    static Properties config = new Properties();
    static InputStream configInput = null;
   
    public static int getCostoMante() {
        return costoMantenimiento;
    }

    public static String getRuta() {
        return ruta;
    }

    public static float getIva() {
        return iva;
    }
    
    
     public static void loadConfig(){
        try{
            configInput = new FileInputStream("config.properties");
            config.load(configInput);
            costoMantenimiento=Integer.parseInt(config.getProperty("costoMantenimiento"));
            iva= Float.parseFloat(config.getProperty("iva"));
            File miDir = new File (".");
            ruta=miDir.getCanonicalPath();
            } catch(IOException | NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Error cargando configuración\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, ruta, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
