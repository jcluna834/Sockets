/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servidor;

import java.util.LinkedList;

/**
 *
 * @author CristianCamilo
 */
public class SalaComun extends Chat{

    public SalaComun() {
        nombreSala = null;
        mensajes = new LinkedList<>();
        usuarios = new LinkedList<>();
    }
}
