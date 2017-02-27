package Cliente;

import Comun.ICliente;
import RMI.ClienteRMI;
import Comun.IServidor;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;
import javax.swing.JOptionPane;

//@author CristianCamilo
public class Cliente extends ClienteRMI{
    static String nombre = null;
    private ICliente cliente;
    private final GUIChat chatCliente;
    protected ClienteSala sala;
    
    public Cliente(String nombre, IServidor servidor) throws RemoteException {
        super(nombre,servidor);
        chatCliente = new GUIChat(servidor, this);
        chatCliente.setVisible(true);
        sala = null;
    }
    
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            IServidor servidor = (IServidor) Naming.lookup("rmi://localhost//Chat");
            while(nombre == null)
                nombre = JOptionPane.showInputDialog(null, null, "Ingresa tu nick", JOptionPane.PLAIN_MESSAGE);
            ICliente cliente = new Cliente(nombre, servidor);
            servidor.agregarUsuario(null, cliente);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actualizar(int opc) throws RemoteException {
        if(opc == 1 || opc == 2) {
            chatCliente.getConectados().setText("Conectados\n");
            String []usuarios = server.getUsuarios(null);
            if(usuarios != null)
                for(String u:usuarios)
                    chatCliente.getConectados().append(u+"\n");
        }else{
            if(opc == 3) {
                chatCliente.getComun().setText("");
                String []mensajes = server.getMensajes(null);
                if(mensajes!=null)
                    for(String m:mensajes)
                        chatCliente.getComun().append(m+"\n");
            }else {}
        }
    }

    @Override
    public void actualizar(String nombreSala, int opc) throws RemoteException {
        if(sala == null) sala = new ClienteSala(nickname, server, nombreSala);
        sala.run();
        sala.actualizar(2);
        sala.actualizar(3);
        sala.actualizar(4);
    }
}