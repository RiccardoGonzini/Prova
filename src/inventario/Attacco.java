/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inventario;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Riccardo Gonzini
 */
public class Attacco extends Temporaneo{

    private static ArrayList <Attacco> listaAttacchi = new ArrayList<Attacco>();

    public Attacco(int posizione, ImageIcon icona, String nome) {
        super(posizione, icona, nome, '1');
    }

    public static ArrayList<Attacco> getListaAttacchi() {
        return listaAttacchi;
    }
    
    
    
}
