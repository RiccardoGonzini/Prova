/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import inventario.Intransitabile;
import inventario.Pg;
import inventario.Png;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Riccardo Gonzini
 */
public class GrigliaCampo extends Griglia {

    // GrigliaCampo è una griglia che viene "attivata" quando viene apeerta la FinestraCampo;
    // essa contiene il pg e i png che si spostano e combattono, e segue le coordinateMappa dagli altri 2 pannelli.
    private int[] limiteBordi = new int[2];

//         906 379
    public GrigliaCampo(FinestraPrincipale finestra, Pg protagonista, ArrayList<Png> elencoPngCampo ) {
        super(5, "img/comba.jpg", 906, 379, finestra);
        this.protagonista = protagonista;
        this.elencoPngCampo = elencoPngCampo;
        protagonista.setIcona(protagonista.arrayImg[1]);
        protagonista.setPosizioneCampo(10);
        protagonista.battaglia(this);
//        Ho creato questo nuoveCoordinate perchè devo passare un array di int al metodo dopo
//        e non trovavo altro modo oltre a questo, e comunque ho impostato (2,0) in quanto
//        il pg parte da metà bordo grigliaCampo e non come nella grigliaMappa nell'angolo.
        protagonista.setCoordinateCampo();
        remove( protagonista.getPosizioneCampo() );
        add(protagonista,  protagonista.getPosizioneCampo() );
        creazionePng();        
        setVisible(true);
    }

    private void creazionePng() {
        ArrayList<Integer> posizioniNemici = new ArrayList<>();
        if (elencoPngCampo.size() == 1){
            posizioniNemici.add(14);
        } else if ( elencoPngCampo.size() == 2){
            posizioniNemici.add(9);
            posizioniNemici.add(19);
        } else {
            posizioniNemici.add(4);
            posizioniNemici.add(14);
            posizioniNemici.add(24);
            
        }
        int contatore = 0;
        for (Png nemico : elencoPngCampo) {
            nemico.setPosizioneCampo(posizioniNemici.get(contatore));
            nemico.setCoordinateCampo();
            nemico.setIcona(nemico.arrayImg[1]);
            remove(nemico.posizioneCampo);
            add(nemico, nemico.posizioneCampo);
            Intransitabile.registraPngCampo(nemico.posizioneCampo);
            contatore++;
        }
    }
    
    public Png getPng(int indice){
        return elencoPngCampo.get(indice);
    }
    
    public Png getPngDaPosizione( int posizioneDaCheck){
        for ( Png nemico: elencoPngCampo)
            if (nemico.posizioneCampo == posizioneDaCheck)
                return nemico;
        return null;
    }
    
    public void uccisione(Png nemico) {
        int temp = nemico.posizioneCampo;
        remove(nemico.posizioneCampo);
        add(new CellaVuota(), temp);
        Intransitabile.pngEliminato(nemico);
        elencoPngMappa.remove(nemico);
        elencoPngCampo.remove(nemico);
        protagonista.aggiornaStatistiche();
        finestra.setClip("mortePng.wav");
    }
    
    public void combattimento2() {
        
//        double danniA = protagonista.getPuntiAttacco() * (new Random().nextInt(10));
//        double danniB = (nemico.getPuntiAttacco() * (new Random().nextInt(10)));
//        // ATTACCO DA PARTE DEL PG
//        nemico.setPuntiVita(nemico.getPuntiVita() - danniA);
//        ((GrigliaMappa) centroGioco).protagonista.setPuntiVita(((GrigliaMappa) centroGioco).protagonista.getPuntiVita() - danniB);
//        JOptionPane.showMessageDialog(centroGioco, "Attacchi il nemico causandogli " + danniA
//                + " \n danni, ma riesce a contraccare prontamente causandoti " + danniB + " danni!");
//        if (nemico.getPuntiVita() <= 0) {
//            ((GrigliaMappa) centroGioco).uccisione(nemico);
//        }
//        if (((GrigliaMappa) centroGioco).protagonista.getPuntiVita() <= 0) {
//            mortePg();
//        }
    }

    public static ArrayList<Png> getElencoPngCampo() {
        return elencoPngCampo;
    }
    
    
    
}
