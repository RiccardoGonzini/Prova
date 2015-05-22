/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import inventario.Castello;
import inventario.Pg;
import inventario.Png;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Riccardo Gonzini
 */
public class Griglia extends JPanel {

    // Griglia è praticamente il luogo virtuale dove si sposta il pg e i png, e a seconda se è una GrigliaCampo
    // o una GrigliaMappa cambiano i modi di interfacciarsi con essa. Questa classe è lo scheletro su quali
    // vengono costruite le altre due classi.
    protected FinestraPrincipale finestra;
//    private int[] limiteBordi = new int[2]; sbaglio o sono inutili?
    private int dimensione;
    
    private Image backgroundImage;
    private int larghezza;
    private int altezza;
    
    public Pg protagonista;
    protected static ArrayList<Png> elencoPngMappa = new ArrayList<Png>();
    protected static ArrayList<Png> elencoPngCampo = new ArrayList<Png>();
    
    public Griglia(int dimensione, String imageLocation, int larghezza, int altezza, FinestraPrincipale finestra) {
        this.finestra = finestra;
//        this.limiteBordi[0] = 0; sbaglio o sono inutili?
//        this.limiteBordi[1] = 0; sbaglio o sono inutili?
        this.larghezza = larghezza;
        this.altezza = altezza;
        this.dimensione = dimensione;
        creazioneCelleVuote();
        setSfondo(imageLocation);
       // repaint();

    }

    public Pg getProtagonista() {
        return protagonista;
    }
    
    public String getTipo(){
        return this.getClass().getSimpleName();
    }
    
    public FinestraPrincipale getFinestra() {
        return finestra;
    }
    
    private void creazioneCelleVuote() {

        setFocusable(false);
        setLayout(new GridLayout(dimensione, dimensione));

        for (int count = 0; count < (dimensione * dimensione); count++) {
            add(new CellaVuota());
        }
    }
    
    public int getDimensione() {
        return dimensione;
    }

    private void setSfondo(String ImageLocation) {
        backgroundImage = new ImageIcon(ImageLocation).getImage();
    }

    protected void paintComponent(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, larghezza, altezza, null);
    }
    
    public Png getPngInPosition( int posizione){
        for ( Png nemico : elencoPngMappa){
            if (nemico.posizioneMappa == posizione)
                return nemico;
        }
        return null;
    }
    
    public void setPnginBattaglia( int posizione){
        for ( Png nemico : elencoPngMappa)
            if (nemico.posizioneMappa == posizione)
                nemico.setInBattaglia(true);
    }
    
    public Personaggio getPngDaPosizione( int griglia, int posizionePng){
        for ( Png nemico : elencoPngMappa)
            if (nemico.posizioneMappa == posizionePng)
                return nemico;
        return null;
    }
    
//    protected void paintComponent(Graphics g) {
//        g.drawImage(backgroundImage, dimensione - 10, dimensione - 10,
//                finestra.getWidth() - 460, finestra.getHeight() - 245, null);
//    }
}
