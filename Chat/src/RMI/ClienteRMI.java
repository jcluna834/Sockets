/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RMI;

import Comun.IServidor;
import Comun.ICliente;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public abstract class ClienteRMI extends UnicastRemoteObject implements ICliente {
    protected final IServidor server;
    protected String nickname;
    
    public ClienteRMI(String nick, IServidor server)throws RemoteException{
        super();
        this.server = server;
        this.nickname = nick;
    }

    @Override
    public String[] getMensajes(String nombreSala) throws RemoteException {
        return server.getMensajes(nombreSala);
    }

    @Override
    public void enviarMensaje(String mensaje, String nombreSala) throws RemoteException {
        server.sendMensaje(nickname+" >> "+mensaje, nombreSala);
    }

    @Override
    public String getNickName() throws RemoteException {
        return nickname;
    }
    
    @Override
    public abstract void actualizar(int opc) throws RemoteException;
    
    @Override
    public abstract void actualizar(String nombreSala, int opc) throws RemoteException;
}
