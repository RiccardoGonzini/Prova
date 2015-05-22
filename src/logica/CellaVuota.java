package logica;

import java.awt.Color;

import javax.swing.JButton;

public class CellaVuota extends JButton {

// CellaVuote è letteralmente una cella vuota, e verrà utilizzata nelle griglie per riempire le 
// celle inutilizzate.
    
	private static final long serialVersionUID = -6326205885964393700L;
        
	public CellaVuota(){
		setBackground(new Color(0, 0,0, 0)); 
		setOpaque(false);
		setFocusable(false);
	}
}
