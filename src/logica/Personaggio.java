/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import inventario.Elemento;
import inventario.Intransitabile;
import inventario.Oggetto;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Riccardo Gonzini
 */
public class Personaggio extends Elemento {

    // Personaggio è la classe che simula il personaggio del gioco, all'interno troveremo valori
    // esterni al Personaggio tipo il coordinateMappa e la Griglia sul quale gioco (che vengono spiegati di seguito)
    
    // AGGIORNAMENTO DEL 26 APRILE 2015, da ora in poi tutti i limitiBordi si chiamano Coordinate + relativa grigliaMappa di appartenenza
    // in quanto scombussolava meno le idee, in quanto quei valori erano effettivamente delle coordinate e davano molti meno problemi.
    // oltretutto l'asse y (il primo valore) è stato invertito ora, o meglio dire, corretto! In quanto prima era messo al contrario,
    // se notate il pg partiva dall'angolino in alto a sinistra, e nello scendere la prima coordinata assumeva valori positivi
    // mentre invece dovrebbe essere il contrario (COME ORA FA) e quindi è stato corretto.
    // NB: ora il valore in posizione '0' è X e quello in posizione '1' è Y, non come prima.
    
    // VARIABILI UTILI ALLA GRIGLIAMAPPA
    protected GrigliaMappa grigliaMappa;
    protected int[] coordinateMappa = new int[2];

    // VARIABILI UTILI ALLA GRIGLIACAMPO
    protected GrigliaCampo grigliaCampo;
    protected int[] coordinateCampo = new int[2];
    public int posizioneCampo;
    
    // VARIABILI CHE VENGONO AGGIORNATE DAL PERSONAGGIO
    protected SchedaInfo plot;
    protected SchedaPersonaggio schedaPersonaggio;

    // VARIABILE CHE IDENTIFICA IN CHE CAMPO SI TROVA IL PROTAGONISTA
    protected static boolean statusCombattimento;

    // IMMAGINI, POSIZIONI: 0 ICONA, 1 CARTA, 2 GIF
    // QUESTO E' STATO FATTO IN QUANTO IL CAMBIO DA MAPPAGRIGLIA E GRIGLIACAMPO NECESSITAVA DI UNA NUOVA IMAGEICON
    // PER LA TESSERA, E DOVENDO COSì SALVARE LE IMMAGINI HO DECISO DI CREARE UN ARRAY IN MODO DA PRENDERE
    // SEMPRE LE IMMAGINI QUANDO MI SERVONO, UNA TEMP SAREBBE STATA PIU' EFFICENTE MA POTEVA CREARE CONFUSIONE
    public ImageIcon[] arrayImg = new ImageIcon[3];

    // VARIABILI UTILI ALLE STATISTICHE
    protected double puntiVita = 100;
    protected double puntiAttacco = 3;
    protected double puntiDifesa = 3;
    protected double puntiVelocita = 1;
    protected double puntiIntelligenza = 1;
    protected double puntiMagia = 3;

    public Personaggio(int posizioneMappa, ImageIcon icona, ImageIcon gif, ImageIcon carta, Griglia griglia) {

        super(posizioneMappa, icona);
        arrayImg[0] = icona;
        arrayImg[1] = carta;
        arrayImg[2] = gif;
        grigliaMappa = (GrigliaMappa) griglia;
    
        schedaPersonaggio = new SchedaPersonaggio(arrayImg[2], puntiVita, puntiAttacco, puntiDifesa, puntiVelocita, puntiVelocita);
        statusCombattimento = false;
        
        setCoordinateMappa();
        setMouse();
        repaint();
    }
    
// METODI RIGUARDANTI LE COORDINATE
//    I metodi set sono "automatici", ovvero si settano da soli in base alla posizione del personaggio
//    in quanto parto dal presupposto che un personaggio per esistere deve avere una posizione nel grigliaCampo
//    ed ergo possiamo trarre le coordinate in qualsiasi momento (cambiando da una griglia all'altra)
    
    public int[] getCoordinateMappa() {
        return coordinateMappa;
    }
    
    public int[] getCoordinateCampo() {
        return coordinateCampo;
    }
    
    public void setCoordinateMappa(){
        coordinateMappa[0] = posizioneMappa%grigliaMappa.getDimensione();
        coordinateMappa[1] = (grigliaMappa.getDimensione() - 1) - ( posizioneMappa / grigliaMappa.getDimensione() );
    }
    
    public void setCoordinateCampo(){
        coordinateCampo[0] = posizioneCampo%5;
        coordinateCampo[1] = (5 - 1) - ( posizioneCampo / 5 );
    }
    
// METODI GENERICI
    private void setMouse() {
        addMouseListener(
                new MouseListener() {

                    @Override
                    public void mouseReleased(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void mousePressed(MouseEvent arg0) {
                    }

                    @Override
                    public void mouseExited(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void mouseEntered(MouseEvent arg0) {
                        // TODO Auto-generated method stub
                       
                    }

                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        schedaPersonaggio.setStatistiche(puntiVita, puntiAttacco, puntiDifesa, puntiVelocita, puntiMagia);
                        schedaPersonaggio.setGif(arrayImg[2]);
                        ((Griglia) grigliaMappa).getFinestra().setSchedaPersonaggio(schedaPersonaggio);
                    }
                });
    }

    public void setPosizioneMappa(int posizione) {
        this.posizioneMappa = posizione;
    }

    public void setPlot(JPanel pan) {
        plot = (SchedaInfo) pan;
    }

    public void setGrigliaCampo(GrigliaCampo grigliaCampo) {
        this.grigliaCampo = grigliaCampo;
    }

// METODI RIGUARDANTI IL CAMPOGRIGLIA E LA SUA BATTAGLIA
    public void setPosizioneCampo(int posizione) {
        posizioneCampo = posizione;
    }

    public int getPosizioneCampo() {
        return posizioneCampo;
    }

    public void battaglia(Griglia campoBattaglia) {
        this.grigliaCampo = (GrigliaCampo) campoBattaglia;
        statusCombattimento = true;
    }

    public boolean isAdiacente(int[] coordinateCampoPng) {
        int dx = Math.abs((coordinateCampoPng[0] - this.coordinateCampo[0]));
        int dy = Math.abs((coordinateCampoPng[1] - this.coordinateCampo[1]));
        
//        Non posso fare lo XOR perchè ho bisogno di verificare che le distanze siano 0 oppure 1
        if ((dx == 1 && dy == 0) || (dx == 0 && dy == 1)) {
            return true;
        } else {
            return false;
        }
    }

// TUTTI I METODI NECESSARI PER LE VARIE STATISTICHE O CALCOLI RIGUARDANTI I VALORI DEI PNG/PG
    public double getPuntiVita() {
        return puntiVita;
    }

    public double getPuntiAttacco() {
        return puntiAttacco;
    }

    public double getPuntiDifesa() {
        return puntiDifesa;
    }

    public double getPuntiVelocita() {
        return puntiVelocita;
    }

    public double getPuntiMagia() {
        return puntiMagia;
    }

    public void setPuntiVita(double puntiVita) {
        this.puntiVita = puntiVita;
        aggiornaStatistiche();
    }

    protected void aggiornaStatistiche() {
        schedaPersonaggio.setStatistiche(puntiVita, puntiAttacco, puntiDifesa, puntiVelocita, puntiMagia);
        schedaPersonaggio.setGif(arrayImg[2]);
        grigliaMappa.getFinestra().setSchedaPersonaggio(schedaPersonaggio);
    }

}
