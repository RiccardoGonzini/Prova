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
public class Protezione extends Fisso{

    private static ArrayList <Protezione> listaProtezioni = new ArrayList<Protezione>();

    public Protezione(int posizione, ImageIcon icona, String nome) {
        super(posizione, icona, nome, '3');
    }

    public static ArrayList<Protezione> getListaProtezioni() {
        return listaProtezioni;
    }
    
    
    
}
