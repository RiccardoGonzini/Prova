/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inventario;

import inventario.Attacco;
import javax.swing.ImageIcon;

/**
 *
 * @author Riccardo Gonzini
 */
public class Bomba extends Attacco{

    public Bomba(int posizione, ImageIcon icona, String nome) {
        super(posizione, icona, "Bomba");
    }
    
}
