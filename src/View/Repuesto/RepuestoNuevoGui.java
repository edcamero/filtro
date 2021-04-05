/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Repuesto;

import Models.Repuesto;
import Models.TipoRepuesto;
import View.Validaciones;
import control.RepuestoController;
import java.awt.event.ItemEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import logica.Fachada;

/**
 *
 * @author blade
 */
public class RepuestoNuevoGui extends javax.swing.JInternalFrame {

    private static RepuestoNuevoGui repuestoNuevoGui;
    private boolean editable = false;
    private Repuesto repuesto;
    ArrayList<Repuesto> repuestos;
    ArrayList<TipoRepuesto> tipoRepuestos;
    private DefaultTableModel model;

    public static RepuestoNuevoGui getInstancia() {
        if (repuestoNuevoGui == null) {
            repuestoNuevoGui = new RepuestoNuevoGui();
        }
        repuestoNuevoGui.cargar();
        repuestoNuevoGui.cargaComponentes();

        return repuestoNuevoGui;
    }
    private RepuestoController repuestoControl;

    /**
     * Creates new form RepuestoNuevo
     */
    private RepuestoNuevoGui() {
        initComponents();
        Validaciones.TextMayus(textNombre);
        Validaciones.soloNumeros(textCosto);
        Validaciones.soloNumeros(textValor);
        cargarTiposRepuesto();

    }

    private void limpiar() {
        textNombre.setText("");
        textCosto.setText("");
        textValor.setText("");
        comboBoxTipoRepuesto.setSelectedIndex(0);
    }

    private void cargaComponentes() {
        btnEditar.setEnabled(false);
        btnBorrar.setEnabled(false);
    }

    private void cargarTiposRepuesto() {
        tipoRepuestos = Fachada.getInstancia().getTipoRepuestos();
        comboBoxTipoRepuestoFilter.addItem("TODOS");
        for (TipoRepuesto tipo : tipoRepuestos) {
            comboBoxTipoRepuesto.addItem(tipo.getNombre());
            comboBoxTipoRepuestoFilter.addItem(tipo.getNombre());
        }

    }

    private void cargar() {
        repuestos = Fachada.getInstancia().getAllRepuesto();
        String data[][] = {};
        String col[] = {"CODIGO", "NOMBRE", "TIPO", "COSTO", "VALOR", "IVA", "TOTAL"};
        model = new DefaultTableModel(data, col);
        if (repuestos.size() != 0) {
            for (Repuesto repuesto : repuestos) {
                Object[] fila = new Object[7];
                fila[0] = repuesto.getId();
                fila[1] = repuesto.getNombre();
                fila[2] = repuesto.getTipo();
                fila[3] = repuesto.getValorCosto();
                fila[4] = repuesto.getValorVenta();
                fila[5] = repuesto.getIva();
                fila[6] = repuesto.getValorVentaIva();
                model.addRow(fila);
            }
        }
        tabla.setModel(model);
        TableColumnModel columnModel = tabla.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(10);
        columnModel.getColumn(1).setPreferredWidth(50);
        columnModel.getColumn(2).setPreferredWidth(10);
        columnModel.getColumn(3).setPreferredWidth(10);
        columnModel.getColumn(4).setPreferredWidth(10);
        columnModel.getColumn(5).setPreferredWidth(10);
        columnModel.getColumn(6).setPreferredWidth(10);
    }

    public JTextField getTextCosto() {
        return textCosto;
    }

    public void setTextCosto(JTextField textCosto) {
        this.textCosto = textCosto;
    }

    public JTextField getTextNombre() {
        return textNombre;
    }

    public void setTextNombre(JTextField textNombre) {
        this.textNombre = textNombre;
    }

    public JTextField getTextValor() {
        return textValor;
    }

    public void setTextValor(JTextField textValor) {
        this.textValor = textValor;
    }

    public JTable getTabla() {
        return tabla;
    }

    public Repuesto getRepuesto() {
        return repuesto;
    }

    public void setRepuestoControl(RepuestoController repuestoControl) {
        this.repuestoControl = repuestoControl;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        textNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        textCosto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textValor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        comboBoxTipoRepuesto = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnCrear = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        comboBoxTipoRepuestoFilter = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        textSearch = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();

        setResizable(true);
        setTitle("Repuesto Nuevo");

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "TIPO", "PRECIO", "VALOR", "IVA", "TOTAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("DATOS"));

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel2.setText("Nombre:");

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel1.setText("Costo");

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel3.setText("Valor");

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel5.setText("Tipo Repuesto");

        comboBoxTipoRepuesto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(textCosto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                    .addComponent(textNombre, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboBoxTipoRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(48, 48, 48)
                        .addComponent(textValor)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(comboBoxTipoRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(textCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("OPCIONES"));

        btnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/plus.png"))); // NOI18N
        btnCrear.setMinimumSize(new java.awt.Dimension(60, 60));
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editar.png"))); // NOI18N
        btnEditar.setMinimumSize(new java.awt.Dimension(60, 60));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btnBorrar.setMinimumSize(new java.awt.Dimension(60, 60));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        jButton2.setMinimumSize(new java.awt.Dimension(60, 60));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCrear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel6.setText("Tipo/Repuesto:");

        comboBoxTipoRepuestoFilter.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxTipoRepuestoFilterItemStateChanged(evt);
            }
        });

        jLabel7.setText("Nombre/Repuesto:");

        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 953, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)))
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxTipoRepuestoFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(comboBoxTipoRepuestoFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(textSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            opcionesEquipo(id, seleccion);
            //JOptionPane.showMessageDialog(null, s); // TODO add your handling code here:  
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.limpiar();
        repuesto = null;
        this.cerrar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (this.validar() && editable) {
            String nombre = this.getTextNombre().getText();
            int costo = Integer.parseInt(this.getTextCosto().getText());
            int valor = Integer.parseInt(this.getTextValor().getText());
            repuesto.setNombre(nombre);
            repuesto.setValorCosto(costo);
            repuesto.setValorVenta(valor);
            repuesto.setTipo_id(getIdTipoRepuestoComboBox());
            if (Fachada.getInstancia().updateRepuesto(repuesto)) {
                JOptionPane.showMessageDialog(rootPane, "se actualizó el repuesto");
                editable = false;
                btnEditar.setEnabled(false);
                btnCrear.setEnabled(true);
                cargar();
                limpiar();
            } else {
                JOptionPane.showMessageDialog(rootPane, "error a actulizar repuesto", "Mensajae de Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    public int getIdTipoRepuestoComboBox() {
        return tipoRepuestos.get(comboBoxTipoRepuesto.getSelectedIndex() - 1).getId();
    }
    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        if (this.validar()) {
            RepuestoController.getInstancia().saveRepuesto();
        }
    }//GEN-LAST:event_btnCrearActionPerformed

    private void comboBoxTipoRepuestoFilterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxTipoRepuestoFilterItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            cargarRepuestos(comboBoxTipoRepuestoFilter.getSelectedItem().toString());
        }
    }//GEN-LAST:event_comboBoxTipoRepuestoFilterItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String keyWord = textSearch.getText();
        cargarRepuestos(Fachada.getInstancia().searchAllRepuesto(keyWord));
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cargarRepuestos(String typeSpare) {
        repuestos = Fachada.getInstancia().getAllRepuesto(typeSpare);
        cargarRepuestos(repuestos);
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

    private void opcionesEquipo(int id, int opc) {
        this.repuesto = RepuestoController.getInstancia().buscar(id);
        switch (opc) {
            case 0:
                textNombre.setText(this.repuesto.getNombre());
                textCosto.setText(String.valueOf(this.repuesto.getValorCosto()));
                textValor.setText(String.valueOf(this.repuesto.getValorVenta()));
                int index = 0;
                for (TipoRepuesto tipo : tipoRepuestos) {
                    comboBoxTipoRepuesto.setSelectedIndex(index);
                    if (tipo.getId() == repuesto.getTipo_id()) {
                        System.out.println(tipo.getNombre());

                        return;
                    }
                    index++;
                }
                this.editable = true;
                btnCrear.setEnabled(false);
                btnEditar.setEnabled(true);
                //EquipoController.getInstancia().editarGui(id);

                break;
            case 1:

                String respuesta = JOptionPane.showInputDialog("Escribe " + repuesto.getNombre() + " para confirmar que desea eliminar el purificador.");
                if (repuesto.getNombre().equalsIgnoreCase(respuesta)) {

                    if (RepuestoController.getInstancia().eliminar(id)) {
                        JOptionPane.showMessageDialog(rootPane, "se eliminno el Repuesto");
                        cargar();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "No se eliminno el Repuesto ya esta asociado a un cliente");
                    }

                } else {
                    JOptionPane.showMessageDialog(rootPane, "No se eliminno el Repuesto");
                }
                break;
        }
    }

    private void cerrar() {

        try {
            //this.dispose();        // TODO add your handling code here:

            this.setClosed(true);
            this.dispose();
        } catch (PropertyVetoException ex) {
            java.util.logging.Logger.getLogger(RepuestoNuevoGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    private boolean validar() {
        if (textNombre.getText().isEmpty() || textCosto.getText().isEmpty() || textValor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Llene todos los campos", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (comboBoxTipoRepuesto.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione un tipo de repuesto", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnEditar;
    private javax.swing.JComboBox<String> comboBoxTipoRepuesto;
    private javax.swing.JComboBox<String> comboBoxTipoRepuestoFilter;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField textCosto;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textSearch;
    private javax.swing.JTextField textValor;
    // End of variables declaration//GEN-END:variables
}
