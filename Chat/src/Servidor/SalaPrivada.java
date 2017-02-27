/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servidor;

import Comun.ICliente;
import java.rmi.RemoteException;
import java.util.LinkedList;

/**
 *
 * @author CristianCamilo
 */
public class SalaPrivada extends Chat{
    
    public SalaPrivada(String nombreSala) {
        this.nombreSala = nombreSala;
        mensajes = new LinkedList<>();
        usuarios = new LinkedList<>();
    }

    public void agregarUsuarioSala(ICliente client, int opc) throws RemoteException {
        if(usuarios.size() < 3) {
            if(!usuarios.contains(client)) {
                usuarios.add(client);
                notificar(nombreSala, opc);
            }
        }
    }
    
    public void notificar(String nombreSala, int opc) throws RemoteException {
        for (ICliente client : usuarios) {
            client.actualizar(nombreSala, opc);
        }
    }
}
