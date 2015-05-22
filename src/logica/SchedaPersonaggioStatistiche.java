/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Riccardo Gonzini
 */
public class SchedaPersonaggioStatistiche extends JPanel {

    // SchedaPersonaggioStatistiche contiene tutte le statistiche riguardanti il nostro Pg.
    
	private Image       backgroundImage;
        private JLabel[]    statistiche          = new JLabel[5];
        private JLabel[]    valori               = new JLabel[5];
	
        public SchedaPersonaggioStatistiche(double puntiVita, double puntiAttacco, double puntiDifesa,
                double puntiVelocita, double puntiMagia){
            
            for ( int contatore = 0 ; contatore < 5 ; contatore++ )
                valori[contatore] =new JLabel();
            
            valori[0].setText("" + puntiVita);
            valori[1].setText("" + puntiAttacco);
            valori[2].setText("" + puntiDifesa);
            valori[3].setText("" + puntiVelocita);
            valori[4].setText("" + puntiMagia);
            
            setFocusable( false );
	        setLayout( new GridLayout( 5, 2) );
    
            for ( int contatore = 0 ; contatore < 5 ; contatore++ )
                statistiche[contatore] =new JLabel();

            statistiche[0].setText("  Punti " + "\n" + " Vita: ");
            Font myFont = new Font("Celtic Garamond the 2nd", Font.BOLD ,20 );
            statistiche[0].setFont(myFont);
            statistiche[1].setText("  Punti Attacco: ");
            
            statistiche[2].setText("  Punti Difesa: ");
            statistiche[3].setText("  Punti Velocita: ");
            statistiche[4].setText("  Punti Magia:");
            
            for (int contatore = 0; contatore < 5 ; contatore++){
                add (statistiche[contatore]);
                
                switch (contatore){
                    case 0:
                        add (new JLabel("" + puntiVita));
                        break;
                        
                    case 1:
                        add (new JLabel("" + puntiAttacco));
                        break;
                        
                    case 2:
                        add (new JLabel("" + puntiDifesa));
                        break;
                        
                    case 3:
                        add (new JLabel("" + puntiVelocita));
                        break;
                        
                    case 4:
                        add (new JLabel("" + puntiMagia));
                        break;
                }
              
            }
		setSfondo("img/pergamenaLato2.jpg");	
                repaint();

        }
        
        private void setSfondo( String ImageLocation ){
            backgroundImage = new ImageIcon(ImageLocation).getImage();
	}
        
        public void paintComponent(Graphics g) {
            g.drawImage(backgroundImage, 0, -25, null);
            
        }
        
        public void cambiaStatistica( int statistica, int campo){
            
            switch (campo){
                    
                    case 0:
                        remove(1);
                        add(new JLabel("" + statistica), 1);
                        
                        break;
                        
                    case 1:
                        remove(3);
                        add(new JLabel("" + statistica), 3);
                        
                        break;
                        
                    case 2:
                        remove(5);
                        add(new JLabel("" + statistica), 5);
                        
                        break;
                        
                    case 3:
                        remove(7);
                        add(new JLabel("" + statistica), 7);
                        
                        break;
                        
                    case 4:
                        remove(9);
                        add(new JLabel("" + statistica), 9);
                        
                        break;
            
            }
        }
        
        public void cambiaStatistiche( double puntiVita, double puntiAttacco, double puntiDifesa,
                double puntiVelocita, double puntiMagia){
            valori[0].setText("" + puntiVita);
            valori[1].setText("" + puntiAttacco);
            valori[2].setText("" + puntiDifesa);
            valori[3].setText("" + puntiVelocita);
            valori[4].setText("" + puntiMagia);
        }    
}
