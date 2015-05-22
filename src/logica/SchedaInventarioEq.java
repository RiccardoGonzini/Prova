/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Riccardo Gonzini
 */
public class SchedaInventarioEq extends JPanel {

    // SchedaInventarioEq è l'inventario che equipaggia sul momento il personaggio durante il gioco.
    private Image backgroundImage;
    private JLabel[] descrizioni = new JLabel[4];
    private JLabel[] equipaggiamenti = new JLabel[4];

    public SchedaInventarioEq(String arma, String protezione, String oggetto, String chiave) {

        for (int contatore = 0; contatore < 4; contatore++) {
            equipaggiamenti[contatore] = new JLabel();
        }

        equipaggiamenti[0].setText(arma);
        equipaggiamenti[1].setText(protezione);
        equipaggiamenti[2].setText(oggetto);
        equipaggiamenti[3].setText(chiave);

        setFocusable(false);
        setLayout(new GridLayout(4, 2));

        for (int contatore = 0; contatore < 4; contatore++) {
            descrizioni[contatore] = new JLabel();
        }

        descrizioni[0].setText("  Arma: ");
        descrizioni[1].setText("  Protezione: ");
        descrizioni[2].setText("  Oggetto: ");
        descrizioni[3].setText("  Chiave: ");

        for (int contatore = 0; contatore < 4; contatore++) {
            add(descrizioni[contatore]);

            switch (contatore) {
                case 0:
                    add(new JLabel("" + arma));
                    break;

                case 1:
                    add(new JLabel("" + protezione));
                    break;

                case 2:
                    add(new JLabel("" + oggetto));
                    break;

                case 3:
                    add(new JLabel("" + chiave));
                    break;
            }
        }
        setSfondo("img/pergamenaLato2.jpg");
        repaint();
        
        setMouse();
    }

       private void setMouse() {
        addMouseListener(
                new MouseListener() {

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
                        System.exit(0);
                    }

                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                    }
                });
    }
    
    
    private void setSfondo(String ImageLocation) {
        backgroundImage = new ImageIcon(ImageLocation).getImage();
    }

    public void paintComponent(Graphics g) {
        g.drawImage(backgroundImage, 0, -25, null);

    }

    public void setComponente(String equipaggiamento, int posizione) {
        this.equipaggiamenti[posizione].setText(equipaggiamento);
        repaint();
    }

    public boolean ifBomba() {
        if (this.equipaggiamenti[2].getText().equals("Bomba")) {
            return true;
        } else {
            return false;
        }
    }

}
