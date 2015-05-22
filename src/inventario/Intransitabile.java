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
public class Intransitabile extends Scenario {

    private static ArrayList<Intransitabile> intransitabiliBlocchiMappa = new ArrayList<Intransitabile>();
    private static ArrayList<Integer>        posizioniPngMappa = new ArrayList<Integer>();
    private static ArrayList<Integer>        posizioniPngCampo = new ArrayList<Integer>();

    public Intransitabile(int posizione, ImageIcon icona) {
        super(posizione, icona);
    }
    
    // METODI PER REGISTRARE

    public static void registraBloccoMappa(Intransitabile daRegistrare) {
        intransitabiliBlocchiMappa.add(daRegistrare);
    }
    
    public static void registraPngMappa(int posizionePng) {
        posizioniPngMappa.add(posizionePng);
    }

    public static void registraPngCampo(int posizionePng) {
        posizioniPngCampo.add(posizionePng);
    }

    // METODI PER CONTROLLARE
    
    public static boolean checkPngOrBloccoMappa(int posizionePg) {
        for (int posizionePng : posizioniPngMappa) {
            if (posizionePng == posizionePg) {
                return true;
            }
        }
        for (Intransitabile blocco : intransitabiliBlocchiMappa) {
            if (blocco.posizioneMappa == posizionePg) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkPngCampo(int posizionePg) {
        for (int posizionePng : posizioniPngCampo) {
            if (posizionePng == posizionePg) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkPngMappa(int posizionePng) {
        for (Intransitabile blocco : intransitabiliBlocchiMappa) {
            if (blocco.posizioneMappa == posizionePng) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean aggiornaPosizionePngMappa(int posizionePngVecchia, int posizionePngNuova) {
        posizioniPngMappa.set(posizioniPngMappa.indexOf(posizionePngVecchia), posizionePngNuova);
        return false;
    }
    public static boolean aggiornaPosizionePngCampo(int posizionePngVecchia, int posizionePngNuova) {
        posizioniPngCampo.set(posizioniPngCampo.indexOf(posizionePngVecchia), posizionePngNuova);
        return false;
    }
    // METODI PER ELIMINARE
    
    public static void removeIntransitabile(Intransitabile toRemove) {
        intransitabiliBlocchiMappa.remove(toRemove);
    }

    public static void pngEliminato(Png nemico) {
        posizioniPngCampo.set(posizioniPngCampo.indexOf(nemico.posizioneCampo), -123);
        posizioniPngMappa.set(posizioniPngMappa.indexOf(nemico.posizioneMappa), -123);
    }
    
//    voglio capire quando viene usato il seguente metodo
//    public static void removePng(Png toRemove) {
//        posizioniPng.remove(toRemove);
//    }
    



    
    
}
