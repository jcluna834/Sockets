/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Comun.ICliente;
import Comun.IServidor;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Julio Luna
 */
public class GUIChat extends javax.swing.JFrame{
    
    public GUIChat(IServidor servidor, ICliente cliente) throws RemoteException {
        initComponents();
        this.cliente = cliente;
        this.servidor = servidor;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        txtComun = new javax.swing.JTextArea();
        btnEnviar = new javax.swing.JButton();
        btnCrearSala = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtConectados = new javax.swing.JTextArea();
        txtMensaje = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sala Comun");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        txtComun.setEditable(false);
        txtComun.setColumns(20);
        txtComun.setFont(new java.awt.Font("Comic Sans MS", 0, 13)); // NOI18N
        txtComun.setRows(5);
        jScrollPane2.setViewportView(txtComun);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        btnCrearSala.setText("Nueva Sala");
        btnCrearSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearSalaActionPerformed(evt);
            }
        });

        txtConectados.setEditable(false);
        txtConectados.setColumns(20);
        txtConectados.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        txtConectados.setRows(5);
        jScrollPane3.setViewportView(txtConectados);

        txtMensaje.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txtMensaje.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMensaje)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                    .addComponent(btnCrearSala, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCrearSala))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        // TODO add your handling code here:
        if(!txtMensaje.getText().isEmpty())
            try {
                servidor.sendMensaje(cliente.getNickName()+" >> "+txtMensaje.getText(), null);
                txtMensaje.setText("");
        } catch (Exception ex) {
            //sino se envia por sockets
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void btnCrearSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearSalaActionPerformed
        try {
            String nombreSala = JOptionPane.showInputDialog(this, null, "Ingresa el nombre de la nueva sala", JOptionPane.PLAIN_MESSAGE);
            if(nombreSala!=null) {
                if(servidor.crearNuevaSala(nombreSala)) {
                    servidor.agregarUsuarioSala(nombreSala, cliente.getNickName(),0);
                }
            }
        }catch(RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnCrearSalaActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try{
            servidor.sacarUsuario(null, cliente);
        }catch(RemoteException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_formWindowClosing
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearSala;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea txtComun;
    private javax.swing.JTextArea txtConectados;
    private javax.swing.JTextField txtMensaje;
    // End of variables declaration//GEN-END:variables
    private final IServidor servidor;
    private final ICliente cliente;
    
    public JTextArea getComun(){
        return txtComun;
    }
    public JTextArea getConectados(){
        return txtConectados;
    }
}
