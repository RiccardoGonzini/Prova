/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import inventario.Pg;
import inventario.Png;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Riccardo Gonzini
 */
public class FinestraCampo extends JPanel {

    protected FinestraPrincipale finestra;
    protected GrigliaCampo grigliaCampo;
    private PannelloAzioni panAzioni;
    private PannelloDirezioni panDirezioni;

    public FinestraCampo(FinestraPrincipale finestra, Pg protagonista, ArrayList<Png> elencoPngCampo) {
        // FinestraCampo è una finestra che comprende 3 sezioni: grigliaCampo, panAzioni e panDirezioni;
        // viene utilizzata quando si entra in contatto con un nemico e deve essere effettuato un combattimento.
        setLayout(new BorderLayout());
        grigliaCampo = new GrigliaCampo(finestra, protagonista, elencoPngCampo);

        add(grigliaCampo, BorderLayout.NORTH);
        grigliaCampo.setPreferredSize(new Dimension(0, 366));
        grigliaCampo.setBackground(Color.green);

        panAzioni = new PannelloAzioni(grigliaCampo);
        add(panAzioni, BorderLayout.CENTER);
        panAzioni.setPreferredSize(new Dimension(756, 120));
        panAzioni.setBackground(Color.red);

        panDirezioni = new PannelloDirezioni(grigliaCampo);
        add(panDirezioni, BorderLayout.WEST);
        panDirezioni.setPreferredSize(new Dimension(150, 120));
        panDirezioni.setBackground(Color.yellow);

        setVisible(true);
    }

}
