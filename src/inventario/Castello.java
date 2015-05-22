/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inventario;

import javax.swing.ImageIcon;

/**
 *
 * @author Riccardo Gonzini
 */
public class Castello extends Ambiente{
    boolean stato = false;

    public Castello(int posizione) {
        super(posizione, new ImageIcon("img/iconaAccessoCastelloOff.jpg"));
    }
    
    public void cambioStato(){
        if (stato){
            super.setIcona(new ImageIcon("img/iconaAccessoCastelloOff.jpg"));
            stato = false;
        }
        else {
            super.setIcona(new ImageIcon("img/iconaAccessoCastelloOn.jpg"));
            stato = true;
        }
    }
    
}
