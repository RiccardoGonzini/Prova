/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Riccardo Gonzini
 */
public class SchedaInventario extends JPanel {

    // SchedaInventario contiene tutte le informazioni riguardanti l'inventario del nostro personaggio
    // dalle armi agli oggetti e le chiavi. Ed ha una sottoscheda che è l'inventario che equipaggia sul momento.
    
    private JScrollPane pannelloScrollabile = new JScrollPane();
    private SchedaInventarioEq inventario;

    public SchedaInventario(String arma, String protezione, String oggetto, String chiave) {

        inventario = new SchedaInventarioEq( arma,  protezione,  oggetto,  chiave);
        setFocusable( false );
	pannelloScrollabile.setFocusable( false );
	setPreferredSize( new Dimension( 230, 0 ) );
	setLayout( new BorderLayout() );
	JPanel spazioSinistro =new JPanel();
	JPanel spazioDestro =new JPanel();
        spazioDestro.setBackground(Color.white);
		
	spazioDestro.setPreferredSize( new Dimension( 15, 0 ) );
	spazioSinistro.setPreferredSize( new Dimension( 25, 0 ) );
	add( spazioDestro, BorderLayout.WEST );
	add( spazioSinistro, BorderLayout.EAST );

        JButton titolo = new JButton(new ImageIcon("img/titoloEquipaggiamento.jpg"));
        titolo.setBackground(Color.white);
        titolo.setPreferredSize( new Dimension( 0, 50 ) );
        titolo.setFocusable(false);
        titolo.setOpaque(true);
        add( titolo, BorderLayout.NORTH );
        add (inventario, BorderLayout.CENTER);
    }

    public SchedaInventarioEq getEquipaggiamento() {
        return inventario;
    }
    
    
}
