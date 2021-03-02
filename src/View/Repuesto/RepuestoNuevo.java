/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Repuesto;

import Models.Repuesto;
import Models.Usuario;
import View.Validaciones;
import control.EquipoController;
import control.RepuestoController;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import logica.Fachada;

/**
 *
 * @author blade
 */
public class RepuestoNuevo extends javax.swing.JInternalFrame {

    private static RepuestoNuevo repuestoNuevo;
    private boolean editable = false;
    private Repuesto repuesto;
    ArrayList<Repuesto> repuestos;
    private DefaultTableModel model;

    public static RepuestoNuevo getInstancia() {
        if (repuestoNuevo == null) {
            repuestoNuevo = new RepuestoNuevo();
        }
        
        return repuestoNuevo;
    }
    private RepuestoController repuestoControl;

    /**
     * Creates new form RepuestoNuevo
     */
    private RepuestoNuevo() {
        initComponents();
        cargar();
        Validaciones.TextMayus(textNombre);
        Validaciones.soloNumeros(textCosto);
        Validaciones.soloNumeros(textValor);

    }

    private void cargar() {
        repuestos = Fachada.getInstancia().getAllRepuesto();
        String data[][] = {};
        String col[] = {"ID", "NOMBREs","TIPO","COSTO","VALOR","IVA","SUB-TOTAL","TOTAL"};
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
        jPanel2 = new javax.swing.JPanel();
        boton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setResizable(true);
        setTitle("Repuesto Nuevo");

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOMBRE", "TIPO", "PRECIO", "VALOR", "IVA", "SUB-TOTAL", "TOTAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textValor, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                    .addComponent(textCosto)
                    .addComponent(textNombre))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(textCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("OPCIONES"));

        boton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add_to_cart.png"))); // NOI18N
        boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editar.png"))); // NOI18N

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
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
                    .addComponent(boton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel4.setText("Por:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Codigo", "Precio" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActionPerformed
        if (this.validar()) {
            if (editable) {
                String nombre = this.getTextNombre().getText();
                int costo = Integer.parseInt(this.getTextCosto().getText());
                int valor = Integer.parseInt(this.getTextValor().getText());
                repuesto.setNombre(nombre);
                repuesto.setValorCosto(costo);
                repuesto.setValorVenta(valor);
                if (RepuestoController.getInstancia().actualizar(repuesto)) {
                    JOptionPane.showMessageDialog(rootPane, "se actualizo el repuesto");
                    boton.setText("Agregar");
                    editable = false;
                } else {
                    JOptionPane.showMessageDialog(rootPane, "error a actulizar repuesto", "Mensjae de Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                RepuestoController.getInstancia().saveRepuesto();
            }

        }


    }//GEN-LAST:event_botonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.cerrar();
    }//GEN-LAST:event_jButton2ActionPerformed

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

    private void opcionesEquipo(int id, int opc) {
        this.repuesto = RepuestoController.getInstancia().buscar(id);
        switch (opc) {
            case 0:
                textNombre.setText(this.repuesto.getNombre());
                textCosto.setText(String.valueOf(this.repuesto.getValorCosto()));
                textValor.setText(String.valueOf(this.repuesto.getValorVenta()));
                this.editable = true;
                boton.setText("Editar");
                //Principal.getInstance().EquipoEditar(id);
                //EquipoController.getInstancia().editarGui(id);

                break;
            case 1:

                String respuesta = JOptionPane.showInputDialog("Escribe " + repuesto.getNombre() + " para confirmar que desea eliminar el purificador.");
                if (repuesto.getNombre().equalsIgnoreCase(respuesta)) {

                    if (RepuestoController.getInstancia().eliminar(id)) {
                        JOptionPane.showMessageDialog(rootPane, "se eliminno el Repuesto");
                        // RepuestoController.getInstancia().listarGui();
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
            java.util.logging.Logger.getLogger(RepuestoNuevo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

    private boolean validar() {
        if (textNombre.getText().isEmpty() || textCosto.getText().isEmpty() || textValor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Llene todos los campos", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField textCosto;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textValor;
    // End of variables declaration//GEN-END:variables
}
