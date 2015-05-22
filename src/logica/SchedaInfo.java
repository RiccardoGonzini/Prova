package logica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SchedaInfo extends JPanel {
    

    // SchedaInfo contiene due schede, una contente i comandi di gioco e l'altra la narrazione della storia
    // lungo il gioco.

    
    private JScrollPane             pannelloScrollabile = new JScrollPane();
    private SchedaInfoNarrazione    sfondoPlot          = new SchedaInfoNarrazione();
    private JLabel                  listaComandi        = new JLabel(new ImageIcon("img/sfondoComandi.jpg"));
    private Image                   backgroundImage;

    public SchedaInfo() {

        setFocusable(false);
        setOpaque(true);
        pannelloScrollabile.setFocusable(false);
        sfondoPlot.setFocusable(false);
        sfondoPlot.setOpaque(true);
        
        

        setLayout(new BorderLayout());

        JPanel spazioSinistro = new JPanel();
        spazioSinistro.setPreferredSize(new Dimension(10, 0));
        
        listaComandi.setFocusable(false);
        listaComandi.setPreferredSize(new Dimension(230, 0));
        
        add(listaComandi,           BorderLayout.WEST);
        add(sfondoPlot,             BorderLayout.CENTER);
        add(spazioSinistro,         BorderLayout.EAST);
        repaint();
        
	}
        
        public void paintComponent(Graphics g) {
            g.drawImage(backgroundImage, 0, 0, null);
            
        }
        
        public void inserisciMissione(String missione) {
            sfondoPlot.inserisciMissione(missione);
    }

}
