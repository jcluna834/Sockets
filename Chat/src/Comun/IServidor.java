/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Comun;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author CristianCamilo
 */
public interface IServidor extends Remote{
    public String[] getMensajes(String nombreSala) throws RemoteException;
    public void sendMensaje(String mensaje, String nombreSala) throws RemoteException;
    public void agregarUsuario(String nombreSala, ICliente usuario) throws RemoteException;
    public void sacarUsuario(String nombreSala, ICliente usuario) throws RemoteException;
    public String[] getUsuarios(String nombreSala) throws RemoteException;
    public boolean crearNuevaSala(String nombreSala) throws RemoteException;
    public boolean eliminarSala(String nombreSala) throws RemoteException;
    public void agregarUsuarioSala(String nombreSala, String usuario, int opc)throws RemoteException;
    public void sendMensajePrivado(String mensaje, String nombreSala)throws RemoteException;
}
