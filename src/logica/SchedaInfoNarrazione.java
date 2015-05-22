/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author Riccardo Gonzini
 */
public class SchedaInfoNarrazione extends JPanel{
    

    // SchedaInfoNarrazione mostra la narrazione della storia lungo il gioco.
    
    private JTextArea       testo = new JTextArea();
    private Image           backgroundImage;
//    private Image backgroundImage;

    public SchedaInfoNarrazione() {
        setFocusable(false);
        setOpaque(true);
        testo.setFocusable(false);
        
        add(testo);
        testo.setBackground(new Color(0, 0,0, 0));
        setSfondo("img/sfondoPlot.jpg");	
        repaint();   

    }

    public String getTesto() {
        return testo.getText();
    }
    
    
    public void inserisciMissione(String missione){
        testo.setText(missione);
    }

    public void setSfondo(String ImageLocation) {
        backgroundImage = new ImageIcon(ImageLocation).getImage();
    }

    public void paintComponent(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, null);
    }
}
