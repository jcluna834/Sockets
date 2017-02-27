/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servidor;

import Comun.IServidor;
import RMI.ServidorRMI;
import java.rmi.Naming;
/**
 *
 * @author CristianCamilo
 */
public class Servidor {
    
    public Servidor() {
        try{
            IServidor i = new ServidorRMI();
            Naming.rebind("rmi://localhost//Chat", i);
            System.out.println("Escuchando...");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Servidor();
    }
}
