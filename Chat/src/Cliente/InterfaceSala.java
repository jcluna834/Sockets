/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Comun.ICliente;
import Comun.IServidor;
import java.rmi.RemoteException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 *
 * @author Julio Luna
 */
public class InterfaceSala extends JFrame {
    
    public InterfaceSala(IServidor servidor, ICliente cliente, String nombreSala) {
        this.cliente = cliente;
        this.servidor = servidor;
        this.nombreSala = nombreSala;
        initComponents();
        try {
            String[] users = this.servidor.getUsuarios(null);
            listUsuarios.setModel(new DefaultComboBoxModel(users));
            this.servidor.agregarUsuarioSala(nombreSala, cliente.getNickName(), 0);
        }catch(RemoteException e){
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtSala = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtConectados = new javax.swing.JTextArea();
        btnEnviar = new javax.swing.JButton();
        listUsuarios = new javax.swing.JComboBox();
        btnAgregar = new javax.swing.JButton();
        txtMensaje = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        txtSala.setEditable(false);
        txtSala.setColumns(20);
        txtSala.setRows(5);
        jScrollPane1.setViewportView(txtSala);

        txtConectados.setEditable(false);
        txtConectados.setColumns(20);
        txtConectados.setRows(5);
        jScrollPane2.setViewportView(txtConectados);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        listUsuarios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAgregar.setText("Agregar Participante");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        txtMensaje.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                    .addComponent(txtMensaje))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(listUsuarios, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(listUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            servidor.sacarUsuario(nombreSala, cliente);
            if(servidor.getUsuarios(nombreSala).length<1){
                servidor.eliminarSala(nombreSala);
            }
        }catch(RemoteException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        try {
            if(!txtMensaje.getText().isEmpty()) {
                servidor.sendMensajePrivado(cliente.getNickName()+" >> "+txtMensaje.getText(), nombreSala);
                txtMensaje.setText("");
            }
        }catch(RemoteException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String usuario = (String) listUsuarios.getSelectedItem();
        try {
            this.servidor.agregarUsuarioSala(nombreSala,usuario,1);
        }catch(RemoteException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        try {
            servidor.sacarUsuario(nombreSala, cliente);
            if(servidor.getUsuarios(nombreSala).length<1){
                servidor.eliminarSala(nombreSala);
            }
            dispose();
        }catch(RemoteException e ){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox listUsuarios;
    private javax.swing.JTextArea txtConectados;
    private javax.swing.JTextField txtMensaje;
    private javax.swing.JTextArea txtSala;
    // End of variables declaration//GEN-END:variables
    private final ICliente cliente;
    private final IServidor servidor;
    private final String nombreSala;
    
    public JTextArea getConectados(){
        return txtConectados;
    }
    
    public JTextArea getSala(){
        return txtSala;
    }
    public String getNombreSala(){
        return nombreSala;
    }
    public JComboBox getLista(){
        return listUsuarios;
    }
}
