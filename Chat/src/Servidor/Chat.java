/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servidor;

import Comun.ICliente;
import Comun.Mensaje;
import java.rmi.RemoteException;
import java.util.LinkedList;

/**
 *
 * @author CristianCamilo
 */
public abstract class Chat {
    protected LinkedList<Mensaje> mensajes;
    protected LinkedList<ICliente> usuarios;
    protected String nombreSala;
    
    public void addMensaje(Mensaje mensaje) throws RemoteException {
        mensajes.add(mensaje);
        notificar(3);
    }
    
    public LinkedList<Mensaje> getMensajes() {
        return mensajes;
    }
    
    public void agregarUsuario(ICliente nuevo) throws RemoteException {
        if(!usuarios.contains(nuevo)) {
            usuarios.add(nuevo);
            notificar(1);
        }
    }
    
    public void notificar(int opcion) throws RemoteException {
        for(ICliente usuario:usuarios)
            usuario.actualizar(opcion);
    }
    
    public synchronized void sacarUsuario(ICliente usuario) throws RemoteException {
        if(usuarios.contains(usuario)) {
            usuarios.remove(usuario);
            notificar(1);
        }
    }

    public String getNombreSala() {
        return nombreSala;
    }
    
    public LinkedList<ICliente> getUsuarios() {
        return usuarios;
    }
}
