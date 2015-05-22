package logica;

import inventario.Pg;
import inventario.Png;
import inventario.Muro;
import inventario.Leva;
import inventario.Oggetto;
import inventario.Castello;
import inventario.Pozione;
import inventario.Intransitabile;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class GrigliaMappa extends Griglia {

    // GrigliaMappa è una griglia che viene "attivata" fin da subito ma rimossa durante i combattimenti;
    // essa contiene il pg e i png che si spostano ma non combattono, e segue le coordinate dalla tastiera e dal mouse.
    private static final long serialVersionUID = 6884438951918936986L;

//        Oggetti singoli fini
    protected FinestraPrincipale finestra;
    protected Castello castello;

//    per il momento non ci sono ancora trappole
//    private static ArrayList<Integer> elencoTrappole = new ArrayList<Integer>();
//        Array e relative grandezze espresse in int per l'accumulare
    protected Leva[] leve = new Leva[5];
    private static int numeroLeve = 0;
    protected Pozione[] pozioni = new Pozione[4];
    private static int numeroPozioni = 0;
    protected Muro[] muri = new Muro[100];
    private static int numeroMuri = 0;

    public GrigliaMappa(FinestraPrincipale finestra) {
        super(10, "img/Pergamena.jpg", 922, 550, finestra);
        this.finestra = finestra;
//        creazioneCelleVuote();

        creazioneMuri();

        creazioneCastello();

        creazioneLeve();

        creazionePg();

        creazionePng();
        
        creazionePozioni();
        repaint();
    }

    // METODI EFFETTIVI PER LO SVOLGIMENTO DEL GIOCO

    public void cambioStato(Leva leva) {
        leva.cambioStato();

        if (leva.posizioneMappa == 89) {
            castello.cambioStato();
        }

        if (leva.getStato()) {
            remove(leva.getMuroRemove().posizioneMappa);
            add(new CellaVuota(), leva.getMuroRemove().posizioneMappa);
            Intransitabile.removeIntransitabile(leva.getMuroRemove());
        } else {
            remove(leva.getMuroRemove().posizioneMappa);
            add(leva.getMuroRemove(), leva.getMuroRemove().posizioneMappa);
            Intransitabile.registraBloccoMappa(leva.getMuroRemove());
        }
        repaint();
    }

    public Leva checkLevaVicina() {
        int davanti = protagonista.posizioneMappa + 1;
        int dietro = protagonista.posizioneMappa - 1;
        int sopra = protagonista.posizioneMappa - 10;
        int sotto = protagonista.posizioneMappa + 10;

        for (Leva leva : this.leve) {
            if (leva.posizioneMappa == davanti && protagonista.getCoordinateMappa()[1] < 9) {
                return leva;
            } else if (leva.posizioneMappa == dietro && protagonista.getCoordinateMappa()[1] > 0) {
                return leva;
            } else if (leva.posizioneMappa == sopra && protagonista.getCoordinateMappa()[0] > 0) {
                return leva;
            } else if (leva.posizioneMappa == sotto && protagonista.getCoordinateMappa()[0] < 9) {
                return leva;
            }
        }
        return null;

    }

    public ArrayList<Png> checkScontro() {

        ArrayList<Png> elencoTemporaneoPngNemici = new ArrayList<Png>();
        ArrayList<Png> elencoTemporaneoPngAiutanti = new ArrayList<Png>();

        int davanti = protagonista.posizioneMappa + 1;
        int dietro = protagonista.posizioneMappa - 1;
        int sopra = protagonista.posizioneMappa - 10;
        int sotto = protagonista.posizioneMappa + 10;

        // aggiornamento 5/04/2015 ho aggiunto i getCoordinataMappa per evitare che un pg che si trova
        // all'inizio del campo mi trovi un nemico sul confine del campo, es: (se ho il pg nella posizione 80)
        // che è praticamente ad inizio campo nella penultima riga, mi può fare il controllo e trovare il png
        // 79 in fondo alla terz'ultima riga, aggiungo il getCoordinataMappa questo problema scompare.
        for (Png nemico : elencoPngMappa) {
            if (nemico.posizioneMappa == davanti && protagonista.getCoordinateMappa()[0] < 9) {
                nemico.setInBattaglia(true);
                elencoTemporaneoPngNemici.add(nemico);

                elencoTemporaneoPngAiutanti = checkPngCatena(1, nemico.posizioneMappa);
                if (elencoTemporaneoPngAiutanti.size() > 0) {
                    for (Png aiutanteNemico : elencoTemporaneoPngAiutanti) {
                        elencoTemporaneoPngNemici.add(aiutanteNemico);
                    }
                }
            }
            if (nemico.posizioneMappa == dietro && protagonista.getCoordinateMappa()[0] > 0) {
                nemico.setInBattaglia(true);
                elencoTemporaneoPngNemici.add(nemico);

                elencoTemporaneoPngAiutanti = checkPngCatena(1, nemico.posizioneMappa);
                if (elencoTemporaneoPngAiutanti.size() > 0) {
                    for (Png aiutanteNemico : elencoTemporaneoPngAiutanti) {
                        elencoTemporaneoPngNemici.add(aiutanteNemico);
                    }
                }
            }
            if (nemico.posizioneMappa == sopra && protagonista.getCoordinateMappa()[1] < 9) {
                nemico.setInBattaglia(true);
                elencoTemporaneoPngNemici.add(nemico);

                elencoTemporaneoPngAiutanti = checkPngCatena(2, nemico.posizioneMappa);
                if (elencoTemporaneoPngAiutanti.size() > 0) {
                    for (Png aiutanteNemico : elencoTemporaneoPngAiutanti) {
                        elencoTemporaneoPngNemici.add(aiutanteNemico);
                    }
                }
            }
            if (nemico.posizioneMappa == sotto && protagonista.getCoordinateMappa()[1] > 0) {
                nemico.setInBattaglia(true);
                elencoTemporaneoPngNemici.add(nemico);

                elencoTemporaneoPngAiutanti = checkPngCatena(2, nemico.posizioneMappa);
                if (elencoTemporaneoPngAiutanti.size() > 0) {
                    for (Png aiutanteNemico : elencoTemporaneoPngAiutanti) {
                        elencoTemporaneoPngNemici.add(aiutanteNemico);
                    }
                }
            }

        }
        if (elencoTemporaneoPngNemici.size() > 0) {
            return elencoTemporaneoPngNemici;
        } else {
            return null;
        }
    }

    // CheckPngCatena è un controllo avanzato per verificare se ci sono png accanto ai png che incontriamo, insomma
    // è incentrato sul multiscontro, d'ora in poi useremo gli arraylist per i png che combatteremo, infatti c'è un controllo
    // per i due lati, a sinistr e a destra c'è il +-10 dei check delle posizione, e in alto e in basso il +-1
    // il tutto ancora da implementare
    
    public ArrayList<Png> checkPngCatena(int direzione, int pngPosizione) {

        Png temporaneo;
        ArrayList<Png> nuovoElencoPng = new ArrayList<Png>();
        switch (direzione) {
            case 1:
                temporaneo = getPngInPosition(pngPosizione - 10);
                if (temporaneo != null && !temporaneo.getInBattaglia()) {
                    setPnginBattaglia(pngPosizione - 10);
                    nuovoElencoPng.add(getPngInPosition(pngPosizione - 10));
                }
                temporaneo = getPngInPosition(pngPosizione + 10);
                if (temporaneo != null && !temporaneo.getInBattaglia()) {
                    setPnginBattaglia(pngPosizione + 10);
                    nuovoElencoPng.add(getPngInPosition(pngPosizione + 10));
                }

                break;

            case 2:
                temporaneo = getPngInPosition(pngPosizione - 1);
                if (temporaneo != null && !temporaneo.getInBattaglia()) {
                    setPnginBattaglia(pngPosizione - 1);
                    nuovoElencoPng.add(getPngInPosition(pngPosizione - 1));
                }

                temporaneo = getPngInPosition(pngPosizione + 1);
                if (temporaneo != null && !temporaneo.getInBattaglia()) {
                    setPnginBattaglia(pngPosizione + 1);
                    nuovoElencoPng.add(getPngInPosition(pngPosizione + 1));
                }

                break;
        }
        return nuovoElencoPng;

    }

    // METODI PER LA CREAZIONE E IL POSIZIONAMENTO DEGLI ELEMENTI / SCENARI
    private void creazionePng() {
        creaPng(7, new ImageIcon("img/iconaPNGBoss.jpg"), new ImageIcon("img/cartaPng2.jpg"));
        creaPng(17, new ImageIcon("img/iconaPNGA.jpg"), new ImageIcon("img/cartaPng1.jpg"));
        creaPng(27, new ImageIcon("img/iconaPNGA.jpg"), new ImageIcon("img/cartaPng1.jpg"));
        creaPng(84, new ImageIcon("img/iconaPNGB.jpg"), new ImageIcon("img/cartaPng3.jpg"));
    }

    private void creazionePg() {
        this.protagonista = new Pg(0, new ImageIcon("img/frontale.gif"),
                new ImageIcon("img/animazione7.gif"),
                new ImageIcon("img/cartaPg1.jpg"),
                this);
        remove(protagonista.posizioneMappa);
        add(protagonista, protagonista.posizioneMappa);
    }

    private void creaPng(int posizionePng, ImageIcon icona, ImageIcon carta) {
        Png nemico = new Png(posizionePng, icona, carta, this);
        elencoPngMappa.add(nemico);
        remove(nemico.posizioneMappa);
        add(nemico, nemico.posizioneMappa);
        Intransitabile.registraPngMappa(nemico.posizioneMappa);
    }

    private void creazionePozioni() {
        creaPozione(19, 300);
        creaPozione(39, 100);
    }

    private void creaPozione(int posizionePozione, int guarigione) {
        pozioni[numeroPozioni] = new Pozione(posizionePozione, guarigione);
        Oggetto.registraOggetto(pozioni[numeroPozioni]);
        remove(posizionePozione);
        add(pozioni[numeroPozioni], posizionePozione);
        numeroPozioni++;
    }

    private void creazioneMuri() {
        creaMuro(4);
        creaMuro(8);
        creaMuro(11);
        creaMuro(12);
        creaMuro(14);
        creaMuro(22);
        creaMuro(24);
        creaMuro(28);
        creaMuro(30);
        creaMuro(31);
        creaMuro(32);
        creaMuro(33);
        creaMuro(34);
        creaMuro(35);
        creaMuro(36);
        creaMuro(37);
        creaMuro(38);
        creaMuro(43);
        creaMuro(52);
        creaMuro(53);
        creaMuro(55);
        creaMuro(56);
        creaMuro(57);
        creaMuro(58);
        creaMuro(59);
        creaMuro(62);
        creaMuro(65);
        creaMuro(72);
        creaMuro(75);
        creaMuro(82);
        creaMuro(88);
        creaMuro(87);
        creaMuro(92);
        creaMuro(95);
        creaMuro(97);
    }

    private void creaMuro(int posizioneMuro) {
        muri[numeroMuri] = new Muro(posizioneMuro);
        Intransitabile.registraBloccoMappa(muri[numeroMuri]);
        remove(posizioneMuro);
        add(new Muro(posizioneMuro), posizioneMuro);
        numeroMuri++;
    }

    private void creazioneCastello() {
        castello = new Castello((super.getDimensione() * super.getDimensione()) - 1);
        Intransitabile.registraBloccoMappa(castello);
        remove((super.getDimensione() * super.getDimensione()) - 1);
        add(castello, ((super.getDimensione() * super.getDimensione()) - 1));
    }

    private void creazioneLeve() {
        creaLeva(21, this.muri[4]);
        creaLeva(54, this.muri[17]);
        creaLeva(90, this.muri[32]);
        creaLeva(85, this.muri[28]);
        creaLeva(89, this.muri[34]);
    }

    private void creaLeva(int posizioneLeva, Muro muroRemove) {
        leve[numeroLeve] = new Leva(posizioneLeva, muroRemove);
        Intransitabile.registraBloccoMappa(leve[numeroLeve]);
        remove(posizioneLeva);
        add(leve[numeroLeve], posizioneLeva);
        numeroLeve++;
    }

    public FinestraPrincipale getFinestraMain() {
        return finestra;
    }

}
