/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inventario;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Riccardo Gonzini
 */
public class Elemento extends JButton{
    
	private static final long serialVersionUID = -6326205885964393700L;
        public int       posizioneMappa;
        public ImageIcon icona;
        
        public Elemento( int posizioneMappa, ImageIcon icona ){
		super( icona );
		setFocusable(false);
                setBackground(new Color(0, 0,0, 0)); 
                setOpaque(false);
		this.posizioneMappa  = posizioneMappa;
                this.icona   = icona;
        }
        
        public void setIcona(ImageIcon icona) {
            this.icona = icona;
             setIcon(icona);
             repaint();
       }
    
}
