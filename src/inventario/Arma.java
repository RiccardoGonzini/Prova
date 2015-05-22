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
public class Arma extends Fisso{

    private static ArrayList <Arma> listaArmi = new ArrayList<Arma>();
    
    public Arma(int posizione, ImageIcon icona, String nome) {
        super(posizione, icona, nome, '2');
    }

    public static ArrayList<Arma> getListaArmi() {
        return listaArmi;
    }
    
    
    
}
