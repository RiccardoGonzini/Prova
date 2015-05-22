/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inventario;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import logica.SchedaInventario;

/**
 *
 * @author Riccardo Gonzini \
 */
public class Leva extends Interagibile{
    private boolean stato       = false;
    public  Muro    muroRemove;

    public Leva(int posizione, Muro muroRemove) {
        super(posizione, new ImageIcon("img/iconaLevaDisattivata.jpg"));
        this.muroRemove = muroRemove;
    }
    
    public void cambioStato(){
        if (stato){
            super.setIcona(new ImageIcon("img/iconaLevaDisattivata.jpg"));
            stato = false;
        }
        else {
            super.setIcona(new ImageIcon("img/iconaLevaAttivata.jpg"));
            stato = true;
        }
        
    }
    
    public boolean getStato(){
        return stato;
    }

    public Muro getMuroRemove() {
        return muroRemove;
    }
    
    

    
}
