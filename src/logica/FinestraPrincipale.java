package logica;

import inventario.Png;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FinestraPrincipale extends javax.swing.JFrame {

// FinestraPrincipale è la finestra principale che si apre a inizio gioco,
// che comprende 5 schede: SchedaInfo, SchedaInventario, SchedaPersonaggio, SchedaTitolo e la Griglia di gioco;
// Qua viene visualizzata subito la GrigliaMappa.
    private static final long serialVersionUID = -1245062492564384446L;
    private SchedaTitolo titolo;
    private SchedaPersonaggio schedaPersonaggio;
    private SchedaInventario inventario;
    private SchedaInfo info;
    private JPanel centroGioco;
    private JPanel centroGiocoTMP;

    public FinestraPrincipale(String nome) {

        //IMPOSTAZIONI CLASSICHE PER UNA FINESTRA QUALSIASI
        super(nome);
        //CREO I VARI OGGETTI DA INSERIRE DELLA FINESTRA
        centroGioco = new GrigliaMappa(this);
        titolo = new SchedaTitolo();
        titolo.setPreferredSize(new Dimension(0, 100));
        inventario = new SchedaInventario("Spada Lunga", "Scudo Medio", "", "");
        inventario.setPreferredSize(new Dimension(230, 0));
        inventario.setFocusable(false);
        allSet();
        info = new SchedaInfo();
        info.setPreferredSize(new Dimension(0, 120));
//        plotPanel.inserisciMissione("Qua viene descritta la missione");

        //aggiungo gli oggetti alla finestra
        add(titolo, BorderLayout.PAGE_START);
        add(centroGioco, BorderLayout.CENTER);
        add(info, BorderLayout.PAGE_END);
        add(inventario, BorderLayout.EAST);

        setSchedaPersonaggio(((GrigliaMappa) centroGioco).protagonista.schedaPersonaggio);

        ((GrigliaMappa) centroGioco).protagonista.setPlot(info);

        //impostazione tastiera
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyReleased(KeyEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void keyPressed(KeyEvent arg0) {

                if (centroGioco.getClass().getSimpleName().equals("GrigliaMappa")) {

                    ArrayList<Png> elencoTemporaneoPng = new ArrayList<Png>();

                    switch (arg0.getKeyCode()) {
                        case 10:
                            if (((GrigliaMappa) centroGioco).protagonista.posizioneMappa == 75) {
                                break;
                            }
                            if (((GrigliaMappa) centroGioco).checkLevaVicina() != null) {
                                ((GrigliaMappa) centroGioco).cambioStato(((GrigliaMappa) centroGioco).checkLevaVicina());
                                setClip("leva.wav");
                            }
                            SchedaInventario oggetti2 = new SchedaInventario("a", "b", "c", "d");
                            inventario = oggetti2;
                            break;

                        case 27:
                            System.exit(0);
                            break;

                        case 32:
                            if (((GrigliaMappa) centroGioco).protagonista.posizioneMappa == 98) {
                                setClip("tada.wav");
                                JOptionPane.showMessageDialog(centroGioco, "HAI CONCLUSO LA TUA MISSIONE!");
                                System.exit(0);
                            }
                            break;

                        case 38: // se premiamo su
                            try {
                                ((GrigliaMappa) centroGioco).protagonista.movimentoPgSu();
                            } catch (MuoviPngException ex) {
                            }

                            elencoTemporaneoPng = ((GrigliaMappa) centroGioco).checkScontro();
                            if (elencoTemporaneoPng != null) {
                                setClip("toltostocazzdisuono.wav");
                                combattimento(elencoTemporaneoPng);
                            }

                            setVisible(true);
                            break;

                        case 40: // se premiamo giu'
                            try {
                                ((GrigliaMappa) centroGioco).protagonista.movimentoPgGiu();
                            } catch (MuoviPngException ex) {
                            }

                            elencoTemporaneoPng = ((GrigliaMappa) centroGioco).checkScontro();
                            if (elencoTemporaneoPng != null) {
                                setClip("toltostocazzdisuono.wav");
                                combattimento(elencoTemporaneoPng);
                            }

                            setVisible(true);
                            break;

                        case 37: //se premo sinistra
                            try {
                                ((GrigliaMappa) centroGioco).protagonista.movimentoPgSx();
                            } catch (MuoviPngException ex) {
                            }

                            elencoTemporaneoPng = ((GrigliaMappa) centroGioco).checkScontro();
                            if (elencoTemporaneoPng != null) {
                                setClip("toltostocazzdisuono.wav");
                                combattimento(elencoTemporaneoPng);
                            }

                            setVisible(true);
                            break;

                        case 39: //se premi destra
                            try {
                                ((GrigliaMappa) centroGioco).protagonista.movimentoPgDx();
                            } catch (MuoviPngException ex) {
                            }

                            elencoTemporaneoPng = ((GrigliaMappa) centroGioco).checkScontro();
                            if (elencoTemporaneoPng != null) {
                                setClip("toltostocazzdisuono.wav");
                                combattimento(elencoTemporaneoPng);
                            }

                            setVisible(true);
                            break;

                    }
                    setVisible(true);

                }
            }
        });

    }

    public void allSet() {

        //setto l'audio di sottofondo
        setClip("stocazzo.wav");

        //tolgo la barra e i bottoni in cima (-,[],X)
        setUndecorated(true);

        //non lascio modificare la grandezza della visualizzazione con il cursore
        setResizable(false);

        //setto il focus sul frame
        setFocusable(true);

        //ho coommentato questo set in quanto non so a che minchia serve
        //setBackground(Color.getHSBColor(245, 211, 139));    
        pack();
        setVisible(true);

        //setto la visualizzazione per tutti gli schermi
        setFrameBounds();

        //setto il layout ( disposizione che gli oggetti avranno nella finestra )
        setLayout(new BorderLayout());
    }

    public void setFrameBounds() {
        int y = 0;
        int x = 0;
        if (java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() > 768 && java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() > 1366) {
            y = ((int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 768) / 2;
            x = ((int) java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 1366) / 2;
        }
        setBounds(x, y, 1366, 768);

    }

    public void setClip(String percorso) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(percorso));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {

        }
    }

//    a che cosa servono questi due metodi campogioco??
    public Griglia getCentroGioco() {
        return (Griglia) centroGioco;
    }

    public void setCampoGioco(GrigliaMappa campoGioco) {
        this.centroGioco = campoGioco;
    }

    public double attacco(double puntiAttaccoA, double puntiDifesaC) {
        double danni = (puntiAttaccoA * new Random().nextInt(10)) - (puntiDifesaC / 2);
        System.out.println("DANNI INFLITTI " + danni);
        return danni;

    }

    public static void main(String[] args) {
        FinestraPrincipale chimerzia = new FinestraPrincipale("Chimerzia");
//        JOptionPane.showMessageDialog(chimerzia, storia()); chimerzia.getContentPane().add("Center", chimerzia);
//        chimerzia.setDefaultCloseOperation(FinestraMain.DO_NOTHING_ON_CLOSE);

    }

    public void setSchedaPersonaggio(SchedaPersonaggio scheda) {

        if (schedaPersonaggio != null) {
            remove(schedaPersonaggio);
        }
        schedaPersonaggio = scheda;
        add(schedaPersonaggio, BorderLayout.WEST);
        setVisible(true);

    }

    public static String storia() {
        return "Quando un rampollo di nobile casata era ritenuto maturo "
                + "per iniziare la sua educazione di cavaliere (ciò avveniva"
                + " intorno ai sette anni), "
                + "veniva inviato come paggio nella dimora di un gentiluomo \n"
                + "(spesso un parente, come uno zio, oppure un grande signore).\n"
                + "Qui imparava sia a stare in società, sia a cavalcare. Intorno ai"
                + " quattordici anni passava al seguito di un cavaliere in qualità di scudiero. \n"
                + " Apprendeva così a maneggiare le armi, ad accudire il cavallo del suo signore,"
                + " a tenere in ordine il suo equipaggiamento.\n";
    }

    public void combattimento(ArrayList<Png> elencoPngCampo) {
        centroGiocoTMP = centroGioco;
        remove(centroGioco);

        centroGioco = new FinestraCampo(this, ((Griglia) centroGiocoTMP).protagonista, elencoPngCampo);
        for (Png nemico : elencoPngCampo) {
            nemico.setGrigliaCampo(((FinestraCampo) centroGioco).grigliaCampo);
        }
        add(centroGioco, BorderLayout.CENTER);
        setVisible(true);
        centroGioco.setFocusable(true);
    }

    public void mortePg() {
        setClip("risata.wav");
        JOptionPane.showMessageDialog(this, "LA FAVOLA E' FINITA");
        System.exit(0);
    }

}
