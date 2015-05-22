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
public class Chiave extends Fisso{

    private static ArrayList <Chiave> listaChiavi = new ArrayList<Chiave>();

    public Chiave(int posizione, ImageIcon icona, String nome, String tipologia) {
        super(posizione, icona, "Chiave", '4');
    }

    public static ArrayList<Chiave> getListaChiavi() {
        return listaChiavi;
    }
    
    
    
}
