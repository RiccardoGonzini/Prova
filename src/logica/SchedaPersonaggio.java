/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;

/**
 *
 * @author Riccardo Gonzini
 */
public class SchedaPersonaggio extends JPanel {

    // SchedaPersonaggio contiene tutte le informazioni riguardanti il nostro Pg, e una scheda contenente le sue statistiche.
    private JScrollPane pannelloScrollabile = new JScrollPane();
    private SchedaPersonaggioStatistiche statistiche;
    protected ImageIcon gifPersonaggio;
    private JButton panSud;
    private JLabel labelGif;

    public SchedaPersonaggio(ImageIcon gif, double puntiVita,
            double puntiAttacco, double puntiDifesa, double puntiVelocita, double puntiMagia) {

        this.gifPersonaggio = gif;
        statistiche = new SchedaPersonaggioStatistiche(puntiVita, puntiAttacco, puntiDifesa, puntiVelocita, puntiMagia);
        setFocusable(false);
        pannelloScrollabile.setFocusable(false);
        setPreferredSize(new Dimension(230, 0));
        setLayout(new BorderLayout());
        JPanel spazioSinistro = new JPanel();
        JPanel spazioDestro = new JPanel();
        spazioDestro.setBackground(Color.white);

        spazioDestro.setPreferredSize(new Dimension(15, 0));
        spazioSinistro.setPreferredSize(new Dimension(25, 0));
        add(spazioDestro, BorderLayout.WEST);
        add(spazioSinistro, BorderLayout.EAST);

        JButton titolo = new JButton(new ImageIcon("img/titoloScheda.jpg"));
        titolo.setBackground(Color.white);
        titolo.setPreferredSize(new Dimension(0, 50));
        titolo.setFocusable(false);
        titolo.setOpaque(true);
        add(titolo, BorderLayout.NORTH);
        add(statistiche, BorderLayout.CENTER);

        panSud = new JButton(new ImageIcon("img/sfondoGif.jpg"));
        panSud.setFocusable(false);
        panSud.setBackground(Color.GRAY);
        panSud.add(labelGif = new JLabel(gifPersonaggio));
        labelGif.setAlignmentX(CENTER_ALIGNMENT);
        labelGif.setPreferredSize(new Dimension(100, 50));

        panSud.setPreferredSize(new Dimension(0, 120));
        add(panSud, BorderLayout.SOUTH);
           
        
        
        
    }
   
        
//        protected void paintComponent(Graphics g) {
//        g.drawImage(labelGif, 30, 2,
//                finestra.getWidth() - 460, finestra.getHeight() - 245, null);
//    }

    public void setStatistiche(double puntiVita, double puntiAttacco,
            double puntiDifesa, double puntiVelocita, double puntiMagia) {
        try {
            this.remove(statistiche);
        } catch (NullPointerException ex) {
        };

        statistiche = new SchedaPersonaggioStatistiche(puntiVita, puntiAttacco, puntiDifesa, puntiVelocita, puntiMagia);
        add(statistiche, BorderLayout.CENTER);
    }

    public void setGif(ImageIcon gif) {
        panSud.remove(labelGif);
        panSud.add(labelGif = new JLabel(gif));
        labelGif.setAlignmentX(CENTER_ALIGNMENT);

    }

}
