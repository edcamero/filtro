/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author blade
 */
public class Validaciones {
    
    
    
      public static void TextMayus(JTextField texto){
        texto.addKeyListener(new KeyListener() {
            @Override
           public void keyTyped(KeyEvent e) {
            //se agrega funcion que obliga las letras en el textbox a ser mayusculas 
                char c=e.getKeyChar();
                //"e" es el nombre asignado al evento de tecla "c" representa a las letras
           if (Character.isLowerCase(c)){
           String cad=(""+c).toUpperCase();
           c=cad.charAt(0);
           e.setKeyChar(c);
        }
            
           
        }

            @Override
            public void keyPressed(KeyEvent e) {
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            
        });
    }
      
      public static void soloNumeros(JTextField texto){
          texto.addKeyListener(new KeyListener(){
              @Override
              public void keyTyped(KeyEvent e) {
                  //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              }

              @Override
              public void keyPressed(KeyEvent EVT) {
                        String value = texto.getText();
                        int l = value.length();
                        if ((EVT.getKeyChar() >= '0' && EVT.getKeyChar() <= '9') 
                                ||EVT.getKeyCode()==KeyEvent.VK_DELETE||EVT.getKeyCode()==8 
                                ||EVT.getKeyCode()==KeyEvent.VK_CONTROL ||  EVT.getKeyCode() == KeyEvent.VK_C || EVT.getKeyCode() == KeyEvent.VK_V) {
                            //System.out.println(EVT.getKeyCode());
                            texto.setEditable(true);
                            
                        } else {
                            //System.out.println(EVT.getKeyCode());
                            texto.setEditable(false);
                            JOptionPane.showMessageDialog(null,"Digite solo numeros");
                        }
                    }

              @Override
              public void keyReleased(KeyEvent e) {
                  //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              }
              
          });
      }
}
