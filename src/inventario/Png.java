/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import logica.Personaggio;
import logica.Griglia;
import javax.swing.ImageIcon;
import logica.CellaVuota;
import logica.FinestraPrincipale;

/**
 *
 * @author Riccardo Gonzini
 */
public class Png extends Personaggio {

    private boolean inBattaglia = false;
    
    private static ArrayList<ArrayList<Integer>> turnologia = new ArrayList<ArrayList<Integer>>();

    public Png(int posizioneMappa, ImageIcon icona, ImageIcon carta, Griglia griglia) {
        super(posizioneMappa, icona, new ImageIcon("img/iconaNemico.gif"), carta, griglia);
        puntiVita = 70;
        puntiAttacco = 6;
        puntiDifesa = 5;
        puntiMagia = 5;
        puntiVelocita = 3;

    }

    public void setInBattaglia(boolean nuovaSituazione) {
        this.inBattaglia = nuovaSituazione;
    }

    public boolean getInBattaglia() {
        return this.inBattaglia;
    }

    private void movimentoPngMappa(int tipoDiMovimento) {
        // qui utilizzo il "posprima" in quanto ho bisogno di registrare il png negli intransitabili
        int posprima = posizioneMappa;
        grigliaMappa.remove(posizioneMappa);
        grigliaMappa.add(new CellaVuota(), posizioneMappa);

        switch (tipoDiMovimento) {
            case 0://sx
                grigliaMappa.remove(--this.posizioneMappa);
                coordinateMappa[0]--;
                break;

            case 1://dx
                grigliaMappa.remove(++this.posizioneMappa);
                coordinateMappa[0]++;
                break;

            case 2: //su
                grigliaMappa.remove(this.posizioneMappa -= grigliaMappa.getDimensione());
                coordinateMappa[1]++;
                break;

            case 3://giu
                grigliaMappa.remove(this.posizioneMappa += grigliaMappa.getDimensione());
                coordinateMappa[1]--;
                break;

        }
        Intransitabile.aggiornaPosizionePngMappa(posprima, posizioneMappa);
        grigliaMappa.add(this, this.posizioneMappa);
    }

    private void movimentoPngCampo(int tipoDiMovimento) {
        int posprima = posizioneCampo;
        grigliaCampo.remove(posizioneCampo);
        grigliaCampo.add(new CellaVuota(), posizioneCampo);
        switch (tipoDiMovimento) {
            case 0://sx
                grigliaCampo.remove(--posizioneCampo);
                coordinateCampo[0]--;
                break;

            case 1://dx
                grigliaCampo.remove(++posizioneCampo);
                coordinateCampo[0]++;
                break;

            case 2: //su
                grigliaCampo.remove(posizioneCampo -= grigliaCampo.getDimensione());
                coordinateCampo[1]++;
                break;

            case 3://giu
                grigliaCampo.remove(posizioneCampo += grigliaCampo.getDimensione());
                coordinateCampo[1]--;
                break;

        }
        Intransitabile.aggiornaPosizionePngCampo(posprima, posizioneMappa);
        grigliaCampo.add(this, posizioneCampo);

    }

    public boolean movimentoPngSx() {

        if (statusCombattimento == false) {
            if (!Intransitabile.checkPngMappa(posizioneMappa - 1)) {
                if (coordinateMappa[0] > 0) { //se non è contro un muro
                    movimentoPngMappa(0);
                    return false;
                }
            }
        } else {
            if (coordinateCampo[0] > 0) { //se non è contro un muro
                movimentoPngCampo(0);
                return false;
            }
        }
        return true;
    }

    public boolean movimentoPngDx() {

        if (statusCombattimento == false) {
            if (!Intransitabile.checkPngMappa(posizioneMappa + 1)) {
                if (coordinateMappa[1] < (grigliaCampo.getDimensione() - 1)) { //se non è contro un muro
                    movimentoPngMappa(1);
                    return false;
                }
            }
        } else {
            if (coordinateCampo[1] < (grigliaCampo.getDimensione() - 1)) { //se non è contro un muro
                movimentoPngCampo(1);
                return false;
            }
        }
        return true;
    }

    public boolean movimentoPngSu() {

        if (statusCombattimento == false) {
            if (!Intransitabile.checkPngOrBloccoMappa(posizioneMappa - grigliaMappa.getDimensione())) {
                if (coordinateMappa[1] < (grigliaMappa.getDimensione() - 1)) { //se non è contro un muro
                    movimentoPngMappa(2);
                    return false;
                }
            }
        } else {
            if (coordinateCampo[1] < (grigliaCampo.getDimensione() - 1)) { //se non è contro un muro
                movimentoPngMappa(2);
                return false;
            }
        }
        return true;

    }

    public boolean movimentoPngGiu() {

        if (statusCombattimento == false) {
            if (!Intransitabile.checkPngMappa(posizioneMappa + grigliaMappa.getDimensione())) {
                if (coordinateMappa[1] > 0) { //se non è contro un muro
                    movimentoPngMappa(3);
                    return false;
                }
            }
        } else {
            if (coordinateCampo[1] > 0) { //se non è contro un muro
                movimentoPngMappa(3);
                return false;
            }
        }
        return true;
    }

    public void azione( Pg pg ){
        ArrayList<Integer> azioniPng = new ArrayList<Integer>();
        ArrayList<Integer> percorsoPng = percorsoPngToPg();
        for (int movimento : percorsoPng)
            azioniPng.add(movimento);
        for (int numeroAttacchi = 0; numeroAttacchi < 101; numeroAttacchi++)
            azioniPng.add(5);
        turnologia.add(azioniPng);
        
    }
    
    private ArrayList<Integer> percorsoPngToPg(){
        
        return null;
    }
//    
//    public boolean checkScontro(Pg pg){
//            int davanti = pg.posizione + 1;
//            int dietro = pg.posizione - 1;
//            int sopra = pg.posizione - 10;
//          
//        }  int sotto = pg.posizione + 10;
//            
//
//                if (this.posizione == davanti){
//                    return true;
//                } else if (this.posizione == dietro){
//                    return true;
//                } else if (this.posizione == sopra){
//                    return true;
//                } else if (this.posizione == sotto){
//                    return true;
//                }
//            return false;
//            
//        }
}
