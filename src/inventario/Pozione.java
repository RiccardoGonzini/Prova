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
public class Pozione extends Guarigione{

    private static ArrayList <Pozione> listaPozioni = new ArrayList<Pozione>();
    private int guarigione;
    
    public Pozione(int posizione, int guarigione) {
        super(posizione, new ImageIcon("img/iconaPozione.jpg"), "Pozione");
        this.guarigione = guarigione;
    }

    public int getGuarigione() {
        return guarigione;
    }
    
    public static Oggetto getCheckOggetto(int posizionePg){
        for( Pozione pozione : listaPozioni )
            if (pozione.posizioneMappa == posizionePg){
                return pozione;
            }
        return null; 
    }
    
    public void removePozione(Pozione pozioneDaRimuovere ){
        listaPozioni.remove(pozioneDaRimuovere);
    }

    public static ArrayList<Pozione> getListaPozioni() {
        return listaPozioni;
    }
    
    
}
