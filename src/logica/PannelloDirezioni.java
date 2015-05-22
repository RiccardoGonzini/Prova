/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import inventario.Png;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

/**
 *
 * @author Riccardo Gonzini
 */
public class PannelloDirezioni extends JPanel {

    // PannelloDirezioni è un pannello appartenente alla FinestraCampo, e consente all'utente di scegliere che direzione
    // far compiere all'avatar nel caso lo volesse spostare durante il combattimento.
    private Image backgroundImage;
    private JButton[] frecce = new JButton[4];
    private GrigliaCampo grigliaCampo;
    private CambioImmagine cambio;

    public PannelloDirezioni(GrigliaCampo grigliaCampo) {
        this.grigliaCampo = grigliaCampo;
        cambio = new CambioImmagine(grigliaCampo);
        creazioneFrecceCelle();
        setSfondo("img/pergamenadir.jpg");
        setVisible(true);
        repaint();
        CambioImmagine cambio = new CambioImmagine(grigliaCampo);

    }

    private void creazioneFrecceCelle() {

        frecce[0] = new JButton(new ImageIcon("img/su.jpg"));
        frecce[0].addMouseListener(new MouseListener() {

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
                
                try {
                    
                grigliaCampo.protagonista.movimentoPgSu();
                    
                } catch ( MuoviPngException ex) {
                    intelligenzaPNG();
                }
                grigliaCampo.finestra.setVisible(true);
                
                
            }

            private void NullPointerException() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        frecce[1] = new JButton(new ImageIcon("img/sx.jpg"));
        frecce[1].addMouseListener(new MouseListener() {

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
                
                try{
                    grigliaCampo.protagonista.movimentoPgSx();
                } catch (MuoviPngException ex){
                    intelligenzaPNG();
                }
                grigliaCampo.finestra.setVisible(true);
            }
        });
        frecce[2] = new JButton(new ImageIcon("img/dx.jpg"));
        frecce[2].addMouseListener(new MouseListener() {

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

                try{
                    grigliaCampo.protagonista.movimentoPgDx();
                
                }catch( MuoviPngException ex ){
                    intelligenzaPNG();
                }
//                grigliaCampo.elencoPngCampo.get(1).movimentoPngSx();
                grigliaCampo.finestra.setVisible(true);
            }
        });
        frecce[3] = new JButton(new ImageIcon("img/giu.jpg"));
        frecce[3].addMouseListener(new MouseListener() {

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
                
                try {
                     grigliaCampo.protagonista.movimentoPgGiu();
                } catch ( MuoviPngException ex){
                    intelligenzaPNG();
                }
                
               
                Timer orologio = new Timer();
                orologio.schedule(cambio, 0, 1000);
                grigliaCampo.finestra.setVisible(true);
                cambio = new CambioImmagine(grigliaCampo);
            }
        });

        setFocusable(false);
        setLayout(new GridLayout(3, 3));
        int contatoreFrecce = -1;
        for (int count = 0; count < 9; count++) {
            if (count % 2 == 1 && count != 0) {
                add(frecce[++contatoreFrecce]);
            } else {
                add(new CellaVuota());
            }
        }

    }

    private void setSfondo(String ImageLocation) {
        backgroundImage = new ImageIcon(ImageLocation).getImage();
    }

    protected void paintComponent(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, 150, 120, null);
    }
    
    private void intelligenzaPNG(){
        for( Png png : GrigliaCampo.getElencoPngCampo() ){
            png.azione( grigliaCampo.protagonista );
        }
    }
}
