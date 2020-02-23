/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.swing.JInternalFrame;

/**
 *
 * @author blade
 */
public interface Controller {
    
    public boolean agregar();
    public void Listar();
    public boolean eliminar(int id);
    public boolean editar();
}
