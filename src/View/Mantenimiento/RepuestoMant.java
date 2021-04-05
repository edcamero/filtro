/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Mantenimiento;

import Models.Bujia;
import Models.MantenimientoEquipo;
import Models.Repuesto;
import Models.TipoRepuesto;
import control.MantenimientoController;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import logica.Fachada;

/**
 *
 * @author blade
 */
public class RepuestoMant extends javax.swing.JInternalFrame {

    private static RepuestoMant repuestosGui;
    ArrayList<TipoRepuesto> tipoRepuestos;
    private ArrayList<Repuesto> repuestos;
    private ArrayList<Bujia> bujias;
    private MantenimientoEquipo mantEquipo;
    private DefaultTableModel model;

    /**
     * Creates new form RepuestoMant
     */
    private RepuestoMant() {
        initComponents();
    }

    public static RepuestoMant getInstancia() {
        if (repuestosGui == null) {
            repuestosGui = new RepuestoMant();
            repuestosGui.cargarTiposRepuesto();
        }
        repuestosGui.cargarRepuestos(Fachada.getInstancia().getAllRepuesto());
        return repuestosGui;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboBoxTipoRepuesto = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        textSearch = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "PRECIO", "IVA", "TOTAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BUSCAR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setText("Tipo/Repuesto:");

        comboBoxTipoRepuesto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxTipoRepuestoItemStateChanged(evt);
            }
        });

        jLabel2.setText("Nombre/Repuesto:");

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxTipoRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboBoxTipoRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        if (evt.getButton() == 3) {
            JTable source = (JTable) evt.getSource();
            int row = source.rowAtPoint(evt.getPoint());
            if (comboBoxTipoRepuesto.getSelectedItem().toString().equalsIgnoreCase("bujia")) {
                Repuesto repuesto = repuestos.get(row);
                Bujia bujia = Fachada.getInstancia().getBujia(repuesto.getId());
                MantenimientoController.getInstancia().agregarBujia(bujia);
                JOptionPane.showMessageDialog(rootPane, "Se agrego la bujia " + bujia.getNombre());
            } else {

                String dato = JOptionPane.showInputDialog("ingresa la cantidad del repuesto "
                        + "para el mantenimiento");
                if (dato == null) {
                    dato = "";
                }
                if (!dato.equals("")) {
                    int cantidad = Integer.parseInt(dato);

                    Repuesto repuesto = repuestos.get(row);
                    if (cantidad > 0) {
                        MantenimientoController.getInstancia().agregarRepuesto(cantidad, repuesto);
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Ingrese una cantidad valida");
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "No ingreso datos en cantidad del articulo");
                }
            }

        }
    }//GEN-LAST:event_tablaMouseClicked

    private void comboBoxTipoRepuestoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxTipoRepuestoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cargarRepuestos(comboBoxTipoRepuesto.getSelectedItem().toString());
        }
    }//GEN-LAST:event_comboBoxTipoRepuestoItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String keyWord = textSearch.getText();
        cargarRepuestos(Fachada.getInstancia().searchAllRepuesto(keyWord));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cargarTiposRepuesto() {
        tipoRepuestos = Fachada.getInstancia().getTipoRepuestos();
        comboBoxTipoRepuesto.addItem("TODOS");
        for (TipoRepuesto tipo : tipoRepuestos) {
            comboBoxTipoRepuesto.addItem(tipo.getNombre());
        }
    }

    private void cargarRepuestos(ArrayList<Repuesto> repuestosX) {
        repuestos = repuestosX;
        String data[][] = {};
        String col[] = {"ID", "NOMBRE", "TIPO", "VALOR", "IVA", "TOTAL"};
        model = new DefaultTableModel(data, col);
        if (repuestos.size() != 0) {
            for (Repuesto repuesto : repuestos) {
                Object[] fila = new Object[6];
                fila[0] = repuesto.getId();
                fila[1] = repuesto.getNombre();
                fila[2] = repuesto.getTipo();
                fila[3] = repuesto.getValorVenta();
                fila[4] = repuesto.getIva();
                fila[5] = repuesto.getValorVentaIva();
                model.addRow(fila);
            }
        }
        tabla.setModel(model);

    }

    private void cargarRepuestos(String typeSpare) {
        repuestos = Fachada.getInstancia().getAllRepuesto(typeSpare);
        cargarRepuestos(repuestos);
    }

    public void setMantEquipo(MantenimientoEquipo mantEquipo) {
        this.mantEquipo = mantEquipo;
    }

    public ArrayList<Repuesto> getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(ArrayList<Repuesto> repuestos) {
        this.repuestos = repuestos;
        this.cargarRepuestos(repuestos);
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
            fila[1] = bujia.getNombre() + " (" + bujia.getVidaUtil() + " dias de duracion.)";
            fila[2] = bujia.getValorVenta();
            model.addRow(fila);
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBoxTipoRepuesto;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField textSearch;
    // End of variables declaration//GEN-END:variables
}
