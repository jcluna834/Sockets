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
public interface ICliente extends Remote {
    public void actualizar(int opc)throws RemoteException;
    public String[] getMensajes(String nombreSala)throws RemoteException;
    public void enviarMensaje(String mensaje, String nombreSala)throws RemoteException;
    public String getNickName()throws RemoteException;
    public void actualizar(String nombreSala, int opc)throws RemoteException;
}
