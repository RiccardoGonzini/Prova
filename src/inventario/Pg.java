/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventario;

import logica.Personaggio;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import logica.CellaVuota;
import logica.FinestraPrincipale;
import logica.Griglia;
import logica.GrigliaMappa;
import logica.MuoviPngException;

/**
 *
 * @author Riccardo Gonzini
 */
public class Pg extends Personaggio {
    
    private boolean passo = false;

    public Pg(int posizioneMappa, ImageIcon icona, ImageIcon gif, ImageIcon carta, Griglia griglia) {
        super(posizioneMappa, icona, gif, carta, griglia );
    }

        // tipoDiMovimento puo' assumere 4 valori :
        // 0- SX
        // 1- DX
        // 2- SU
        // 3- GIU'
        // tutti i movimenti del protagonista (Pg)

    private void movimentoPgMappa(int tipoDiMovimento) {
        grigliaMappa.remove(posizioneMappa);
        grigliaMappa.add(new CellaVuota(), posizioneMappa);

        switch (tipoDiMovimento) {

            case 0://sx
                grigliaMappa.remove(--posizioneMappa);
                coordinateMappa[0]--;
                if (!passo){
                    this.setIcona(new ImageIcon("img/sx1.png"));
                    setVisible(true);
                    repaint();
                    passo = true;
                } else {
                    this.setIcona(new ImageIcon("img/sx2.png"));
                    setVisible(true);
                    repaint();
                    passo = false;
                }
                break;

            case 1://dx
                grigliaMappa.remove(++posizioneMappa);
                coordinateMappa[0]++;
                if (!passo){
                    this.setIcona(new ImageIcon("img/dx1.png"));
//                    setVisible(true);
//                    repaint();
                    passo = true;
                } else {
                    this.setIcona(new ImageIcon("img/dx2.png"));
//                    setVisible(true);
//                    repaint();
                    passo = false;
                }
                break;

            case 2: //su
                grigliaMappa.remove(posizioneMappa -= grigliaMappa.getDimensione());
                coordinateMappa[1]++;
                break;

            case 3://giu
                grigliaMappa.remove(posizioneMappa += grigliaMappa.getDimensione());
                coordinateMappa[1]--;
                break;
        }
        grigliaMappa.add(this, posizioneMappa);
  
    }

    private void movimentoPgCampo(int tipoDiMovimento) throws MuoviPngException {
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
        grigliaCampo.add(this, posizioneCampo);
              
        throw new MuoviPngException();
    }

    public boolean movimentoPgSx() throws MuoviPngException {
        if (statusCombattimento == false) {
            if (!Intransitabile.checkPngOrBloccoMappa(posizioneMappa - 1)) {
                if (coordinateMappa[0] > 0) { //se non sono contro un muro
                    checkUseOggetto(posizioneMappa - 1);
                    movimentoPgMappa(0);
                    return false;
                }
            }
        } else if (!Intransitabile.checkPngCampo(posizioneCampo - 1)) {
            if (coordinateCampo[0] > 0) { //se non sono contro un muro
                movimentoPgCampo(0);
                return false;
            }
        }
        return true;
    }

    public boolean movimentoPgDx() throws MuoviPngException {
        if (statusCombattimento == false) {
            if (!Intransitabile.checkPngOrBloccoMappa(posizioneMappa + 1)) {
                if (coordinateMappa[0] < (grigliaMappa.getDimensione() - 1)) { //se non sono contro un muro
                    checkUseOggetto(posizioneMappa + 1);
                    movimentoPgMappa(1);
                    return false;
                }
            }
        } else if (!Intransitabile.checkPngCampo(posizioneCampo + 1)) {
            if (coordinateCampo[0] < (grigliaCampo.getDimensione() - 1)) { //se non sono contro un muro
                movimentoPgCampo(1);
                return false;
            }
        }
        return true;
    }

    public boolean movimentoPgSu() throws MuoviPngException {
        if (statusCombattimento == false) {
            if (!Intransitabile.checkPngMappa(posizioneMappa - grigliaMappa.getDimensione())) {
                if (coordinateMappa[1] < (grigliaMappa.getDimensione() - 1)) { //se non sono contro un muro
                    checkUseOggetto(posizioneMappa - grigliaMappa.getDimensione());
                    movimentoPgMappa(2);
                    return false;
                }
            }
        } else if (!Intransitabile.checkPngCampo(posizioneCampo - grigliaCampo.getDimensione())) {
            if (coordinateCampo[1] < (grigliaCampo.getDimensione() - 1)) { //se non sono contro un muro
                movimentoPgCampo(2);
                return false;
            }
        }
        return true;
    }

    public boolean movimentoPgGiu() throws MuoviPngException {
        if (statusCombattimento == false) {
            if (!Intransitabile.checkPngMappa(posizioneMappa + grigliaMappa.getDimensione())) {
                if (coordinateMappa[1] > 0) { //se non sono contro un muro
                    checkUseOggetto(posizioneMappa + grigliaMappa.getDimensione());
                    movimentoPgMappa(3);
                    return false;
                }
            }
        } else if (!Intransitabile.checkPngCampo(posizioneCampo + grigliaCampo.getDimensione())) {
            if (coordinateCampo[1] > 0) { //se non sono contro un muro
                movimentoPgCampo(3);
                return false;
            }

        }
        return true;
    }

    public void checkUseOggetto(int posizionePg) {
        if (statusCombattimento == false) {
            if (Oggetto.getOggetto(posizionePg) != null) {
                ((GrigliaMappa) grigliaMappa).getFinestraMain().setClip("nonlofacciosuonare.wav");
                Oggetto nuovo = Oggetto.getOggetto(posizionePg);
                plot.inserisciMissione("Hai appena usato una " + nuovo.getNome() + "!");
                Oggetto.effettoOggetto(nuovo, this);
                Oggetto.rimuoviOggetto(nuovo);
            }
        }
    }

}
