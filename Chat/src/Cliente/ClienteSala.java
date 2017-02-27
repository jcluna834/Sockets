/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Cliente;

import Comun.IServidor;
import RMI.ClienteRMI;
import java.rmi.RemoteException;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author CristianCamilo
 */
public class ClienteSala extends ClienteRMI {
    private final InterfaceSala chatCliente;
    private final String nombreSala;
    
    public ClienteSala(String nick, IServidor server, String nombreSala) throws RemoteException {
        super(nick, server);
        this.nombreSala = nombreSala;
        chatCliente = new InterfaceSala(server, this, nombreSala);
    }
    
    public void run() {chatCliente.setVisible(true);}
    
    @Override
    public void actualizar(int opc) throws RemoteException {
        if(chatCliente != null) {
            if(opc == 1 || opc == 2) {
                chatCliente.getConectados().setText("");
                String[] users = server.getUsuarios(chatCliente.getNombreSala());
                if(users != null)
                    for(String u : users)
                        chatCliente.getConectados().append(u+"\n");
            }else{
                if(opc == 3){
                    chatCliente.getSala().setText("");
                    String[] mensajes = server.getMensajes(chatCliente.getNombreSala());
                    if(mensajes != null)
                        for(String m : mensajes)
                            chatCliente.getSala().append(m+"\n");
                }else{
                    if(opc == 4){
                        String [] users = server.getUsuarios(null);
                        chatCliente.getLista().setModel(new DefaultComboBoxModel(users));
                    }
                }
            }
        }
    }

    @Override
    public void actualizar(String nombreSala, int opc) throws RemoteException {
        if(opc == 0 || opc == 1) {
            ClienteSala sala = new ClienteSala(nickname, server, nombreSala);
            sala.run();
            actualizar(1);
            actualizar(3);
        }
        else{
            if(opc == 2) {
                actualizar(4);
            }else{
                if(opc == 3){
                    actualizar(3);
                }else{
                    
                }
            }
        }
    }
}
