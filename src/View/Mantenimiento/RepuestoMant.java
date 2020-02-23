/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Mantenimiento;

import Models.Bujia;
import Models.Repuesto;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author blade
 */
public class RepuestoMant extends javax.swing.JInternalFrame {

    private static RepuestoMant repuestosGui;
    private ArrayList<Repuesto> repuestos;
    private ArrayList<Bujia> bujias;

    /**
     * Creates new form RepuestoMant
     */
    private RepuestoMant() {
        initComponents();
    }

    public static RepuestoMant getInstancia() {
        if (repuestosGui == null) {
            repuestosGui = new RepuestoMant();
        }
        return repuestosGui;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        botonRepuestos = new javax.swing.JToggleButton();
        botonBujias = new javax.swing.JToggleButton();

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "PRECIO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        botonRepuestos.setText("Repuestos");
        botonRepuestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRepuestosActionPerformed(evt);
            }
        });

        botonBujias.setText("Bujias");
        botonBujias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBujiasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonRepuestos)
                        .addGap(18, 18, 18)
                        .addComponent(botonBujias)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonRepuestos)
                    .addComponent(botonBujias))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getButton() == 3) {
            JTable source = (JTable) evt.getSource();
            int row = source.rowAtPoint(evt.getPoint());
            int seleccion = JOptionPane.showOptionDialog(
                    this,
                    "Seleccione opcion",
                    "Selector de opciones",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, // null para icono por defecto.
                    new Object[]{"Editar", "Eliminar", "Cancelar"}, // null para YES, NO y CANCEL
                    "opcion 1");
            //int column = source.columnAtPoint( evt.getPoint() );
            String id_s = "" + source.getModel().getValueAt(row, 0);

            int id = Integer.parseInt(id_s);
            // System.out.println(source.getModel().getValueAt(row, 0));
            //opcionesEquipo(id, seleccion);
            //JOptionPane.showMessageDialog(null, s); // TODO add your handling code here:
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void botonRepuestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRepuestosActionPerformed
        if (botonRepuestos.isSelected()) {
            botonBujias.setSelected(false);
            this.listarRepuestos();
            
        }
    }//GEN-LAST:event_botonRepuestosActionPerformed

    private void botonBujiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBujiasActionPerformed
        if (botonBujias.isSelected()) {
            botonRepuestos.setSelected(false);
            this.listarBujias();
        }
    }//GEN-LAST:event_botonBujiasActionPerformed

    public ArrayList<Repuesto> getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(ArrayList<Repuesto> repuestos) {
        this.repuestos = repuestos;
        this.listarRepuestos();
        botonRepuestos.setSelected(true);
    }

    public ArrayList<Bujia> getBujias() {
        return bujias;
    }

    public void setBujias(ArrayList<Bujia> bujias) {
        this.bujias = bujias;
    }

    
    public void listarRepuestos() {
        DefaultTableModel model = (DefaultTableModel) this.tabla.getModel();
        Object[] fila;
        model.setRowCount(0);
        for (Repuesto repuesto : this.repuestos) {
            fila = new Object[3];
            fila[0] = repuesto.getId();
            fila[1] = repuesto.getNombre();
            fila[2] = repuesto.getValorVenta();
            model.addRow(fila);

        }

    }
    

    public void listarBujias() {
        DefaultTableModel model = (DefaultTableModel) this.tabla.getModel();
        Object[] fila;
        model.setRowCount(0);
        for (Bujia bujia : bujias) {
            fila = new Object[3];
            fila[0] = bujia.getId();
            fila[1] = bujia.getNombre()+" ("+bujia.getVidaUtil()+" dias de duracion.)";
            fila[2] = bujia.getValorVenta();
             model.addRow(fila);
        }
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton botonBujias;
    private javax.swing.JToggleButton botonRepuestos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
