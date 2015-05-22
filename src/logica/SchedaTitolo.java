package logica;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SchedaTitolo extends JPanel {

    // SchedaTitolo contiene il titolo della schermata.
    
	private Image backgroundImage;

	/**
	 * 
	 */
	private static final long serialVersionUID = -1009352778265227876L;

	public SchedaTitolo(){
		setFocusable( false );
                settaSfondo("img/sfondoTitolo.jpg");	
                repaint();
	}
        
        private void settaSfondo( String ImageLocation ){
            backgroundImage = new ImageIcon(ImageLocation).getImage();
	}
        
        public void paintComponent(Graphics g) {
        	
            g.drawImage(backgroundImage, 0, 0, null);
            
        }
}
