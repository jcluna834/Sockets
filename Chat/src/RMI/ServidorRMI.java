/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RMI;

import Comun.IServidor;
import Comun.ICliente;
import Servidor.Chat;
import Comun.Mensaje;
import Servidor.SalaComun;
import Servidor.SalaPrivada;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;


public class ServidorRMI extends UnicastRemoteObject implements IServidor {
    protected Chat comun;
    protected LinkedList<SalaPrivada> privadas;
    
    public ServidorRMI() throws RemoteException {
        super();
        comun = new SalaComun();
        privadas = new LinkedList<>();
    }
    
    protected Chat getSala(String nombreSala)throws RemoteException {
        if(nombreSala == null) return comun;
        else {
            int i = 0, tam = privadas.size();
            while(i<tam && privadas.get(i).getNombreSala().compareTo(nombreSala)!=0) i++;
            if(i<tam) {return privadas.get(i);}
            else return null;
        }
    }
    
    @Override
    public String[] getMensajes(String nombreSala) throws RemoteException {
        Chat sala = getSala(nombreSala);
        if(sala != null) {
            LinkedList<Mensaje> mensajes = sala.getMensajes();
            if(mensajes != null && mensajes.size()>=1) {
                int tam = mensajes.size(), i = 0;
                String []text = new String[tam];
                for(Mensaje aux:mensajes) {
                    text[i] = aux.getTexto();
                    i++;
                }
                return text;
            }
        }
        return null;
    }
    
    @Override
    public void sendMensaje(String mensaje, String nombreSala) throws RemoteException {
        Chat sala = getSala(nombreSala);
        if(sala != null) sala.addMensaje(new Mensaje(mensaje));
    }

    @Override
    public void agregarUsuario(String nombreSala, ICliente usuario) throws RemoteException {
        Chat sala = getSala(nombreSala);
        if(sala != null) sala.agregarUsuario(usuario);
    }

    @Override
    public void sacarUsuario(String nombreSala, ICliente usuario) throws RemoteException {
        Chat sala = getSala(nombreSala);
        if(sala != null) {
            if(nombreSala == null) {
                if(privadas.size()>0) {
                    for(SalaPrivada p: privadas) {
                        if(p.getUsuarios().contains(usuario)) {
                            p.sacarUsuario(usuario);
                            p.notificar(p.getNombreSala(), 2);
                        }
                    }
                }
            }
            sala.sacarUsuario(usuario);
        }
    }

    @Override
    public String[] getUsuarios(String nombreSala) throws RemoteException {
        Chat sala = getSala(nombreSala);
        if(sala != null) {
            LinkedList<ICliente> usuarios = sala.getUsuarios();
            if(usuarios != null && usuarios.size()>=1) {
                int tam = usuarios.size(), i = 0;
                String []nombres = new String[tam];
                for(ICliente aux:usuarios) {
                    nombres[i] = aux.getNickName();
                    i++;
                }
                return nombres;
            }
        }
        return null;
    }

    @Override
    public boolean crearNuevaSala(String nombreSala) throws RemoteException {
        Chat sala = getSala(nombreSala);
        if(sala == null) {
            privadas.add(new SalaPrivada(nombreSala));
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarSala(String nombreSala) throws RemoteException {
        if(nombreSala != null) {
            SalaPrivada sala = (SalaPrivada) getSala(nombreSala);
            if(sala != null) {
                privadas.remove(sala);
                return true;
            }
        }
        return false;
    }

    @Override
    public void agregarUsuarioSala(String nombreSala, String usuario, int opc) throws RemoteException {
        ICliente client = getUsuario(usuario);
        if(client != null) {
            SalaPrivada p = (SalaPrivada) getSala(nombreSala);
            if(p.getNombreSala() != null)
                p.agregarUsuarioSala(client, opc);
        }
    }
    
    protected ICliente getUsuario(String usuario) throws RemoteException {
        LinkedList<ICliente> users = comun.getUsuarios();
        int i = 0, tam = users.size();
        while(i<tam && users.get(i).getNickName().compareTo(usuario)!=0) i++;
        if(i<tam) return users.get(i);
        else return null;
    }

    @Override
    public void sendMensajePrivado(String mensaje, String nombreSala) throws RemoteException {
        SalaPrivada privada = (SalaPrivada) getSala(nombreSala);
        if(privada != null) {
            privada.addMensaje(new Mensaje(mensaje));
            privada.notificar(nombreSala, 3);
        }
    }
}
