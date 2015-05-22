/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import inventario.Intransitabile;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Color.RED;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

/**
 *
 * @author Riccardo Gonzini
 */
public class PannelloAzioni extends JPanel {
    
    // PannelloAzioni è un pannello appartenente alla FinestraCampo, e consente all'utente di scegliere che azione
    // far compiere all'avatar nel caso lo volesse: attaccare, recuperare, usare 
    // o fuggire (tutto questo durante il combattimento).
    
    private     Image       backgroundImage;
    private     JButton[]   azioni              = new JButton[9];
    private GrigliaCampo    grigliaCampo;
    
    public PannelloAzioni(GrigliaCampo grigliaCampo) {
        this.grigliaCampo = grigliaCampo;
        creazioneCelleVuote();
        setSfondo("img/pergamenaazio.jpg");
        setVisible(true);
    }
    
    private void creazioneCelleVuote() {
        
        azioni[0] = new JButton(new ImageIcon("img/attacco.jpg"));
        azioni[0].addMouseListener( new MouseListener() {

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
                        if (grigliaCampo.getPngDaPosizione(grigliaCampo.protagonista.posizioneCampo + 5) != null)
                            grigliaCampo.uccisione(grigliaCampo.getPngDaPosizione(grigliaCampo.protagonista.posizioneCampo + 5));
                    }
                    
                });
        setFocusable(false);
        setLayout(new GridLayout(3, 3));
            for (int count = 0; count < 9; count++) {
                if (count == 0)
                    add (azioni[0]);
                else
                    add(new CellaVuota());
            }

    }
        
    private void setSfondo(String ImageLocation) {
        backgroundImage = new ImageIcon(ImageLocation).getImage();
    }

    protected void paintComponent(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, 756, 120, null);
    }
    
}
