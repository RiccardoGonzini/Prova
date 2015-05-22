/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import logica.Personaggio;

/**
 *
 * @author Riccardo Gonzini
 */
public class Oggetto extends Elemento {

    private String nome;
    private char tipologia;

    public Oggetto(int posizione, ImageIcon icona, String nome, char tipologia) {
        super(posizione, icona);
        this.nome = nome;
        this.tipologia = tipologia;

    }

    public static void registraOggetto(Oggetto nuovo) {
        switch (nuovo.tipologia) {

            // 0 = POZIONE
            case '0':
                Pozione.getListaPozioni().add((Pozione) nuovo);
                break;

            // 1 = ATTACCO TEMPORANEO
            case '1':
                Attacco.getListaAttacchi().add((Attacco) nuovo);
                break;

            // 2 = ARMA
            case '2':
                Arma.getListaArmi().add((Arma) nuovo);
                break;

            // 3 = PROTEZIONE
            case '3':
                Protezione.getListaProtezioni().add((Protezione) nuovo);

                break;

            // 4 = CHIAVE
            case '4':
                Chiave.getListaChiavi().add((Chiave) nuovo);

                break;
        }
    }

    public static void rimuoviOggetto(Oggetto daRimuovere) {
        switch (daRimuovere.tipologia) {

            // 0 = POZIONE
            case '0':
                Pozione.getListaPozioni().remove(daRimuovere);

                break;

            // 1 = ATTACCO TEMPORANEO
            case '1':
                Attacco.getListaAttacchi().remove(daRimuovere);

                break;

            // 2 = ARMA
            case '2':
                Arma.getListaArmi().remove(daRimuovere);

                break;

            // 3 = PROTEZIONE
            case '3':
                Protezione.getListaProtezioni().remove(daRimuovere);

                break;

            // 4 = CHIAVE
            case '4':
                Chiave.getListaChiavi().remove(daRimuovere);

                break;
        }

    }

    public static Oggetto getOggetto(int posizionePg) {

        for (Pozione pozione : Pozione.getListaPozioni()){
            if (pozione.posizioneMappa == posizionePg) 
                return pozione;
        }
        
        for (Attacco attacco : Attacco.getListaAttacchi()) 
            if (attacco.posizioneMappa == posizionePg) 
                return attacco;
        
        for (Arma arma : Arma.getListaArmi()) 
            if (arma.posizioneMappa == posizionePg) 
                return arma;
        
        for (Protezione protezione : Protezione.getListaProtezioni()) 
            if (protezione.posizioneMappa == posizionePg) 
                return protezione;
        
        for (Chiave chiave : Chiave.getListaChiavi()) 
            if (chiave.posizioneMappa == posizionePg) 
                return chiave;
        
        return null;
}

    public static void effettoOggetto(Oggetto oggetto, Personaggio personaggio){
            switch (oggetto.tipologia){
            
            // 0 = POZIONE
            case '0':
                Pozione pozione = (Pozione) oggetto;
                personaggio.setPuntiVita(personaggio.getPuntiVita() + pozione.getGuarigione());
                
                break;
                
            // 1 = ATTACCO TEMPORANEO
            case '1':
                
                break;
                
            // 2 = ARMA
            case '2':
                
                break;
            
            // 3 = PROTEZIONE
            case '3':
                
                break;
                
            // 4 = NEUTRO
            case '4':
                
                break;
        }
    }

//    
//    public static Oggetto getCheckOggetto(int posizionePg){
//        for( Oggetto oggetto : listaOggetti )
//            if (oggetto.posizione == posizionePg){
//                return oggetto;
//            }
//        return null;
//    }
//    
//    public static String nomeCheckOggetto(int posizionePg){
//        for (Oggetto oggetto : listaOggetti )
//            if (oggetto.posizione == posizionePg)
//                return oggetto.nome;
//        return " nulla ";
//    }

    public int getPosizione() {
        return posizioneMappa;
    }

    public String getNome() {
        return nome;
    }
    
    
    
    
    
}
