/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import java.util.Timer;

/**
 *
 * @author serale5ms
 */
public class CambioImmagine extends java.util.TimerTask {
    
    int secondi;
    GrigliaCampo campo;
    boolean stop;

    public CambioImmagine(GrigliaCampo campo) {
        this.campo = campo;
        stop = false;
    }

    public boolean getStop() {
        return stop;
    }
    
    public void run() {
        stop = true;
        campo.protagonista.setIcona(campo.protagonista.arrayImg[2]);
        if (secondi > 1) {
            campo.protagonista.setIcona(campo.protagonista.arrayImg[1]);
            this.cancel();
            stop = false;
        }
        secondi++;

    }
   
}
