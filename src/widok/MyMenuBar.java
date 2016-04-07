/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package widok;

import java.awt.event.ActionListener;
import javax.swing.*;
import model.Model;

/**
 * Klasa <code>MyMenuBar</code> jest klasa przechowujaca graficzny interfejs menu
 * @author M
 */
public class MyMenuBar extends JMenuBar {

    private Model model;
    private JMenu menuGra, menuRuchy, menuUstawienia, zmienKolor, menuPomoc;
    private JMenuItem mNowaGra, mKoniecGry, mZaznacz, mLewo, mPrawo, mGora, mDol, zmienTlo, mJednolite, mKolorowe, mOGrze, mOAplikacji;
    private JRadioButtonMenuItem  rBrytyjska, rEuropejska;
    private ButtonGroup bgWersja;
    /**
     * Konstruktor klasy <code>MyMenuBar</code>.
     * @param model obiek przechowujacy logike gry
     */
    public MyMenuBar(Model model) {
        this.model = model;
        deklaruj();
        polacz();
        dodajSkroty();
        ustawKomendy();
    }
    /**
     * Deklaracja pol klasy
     */
    private void deklaruj() {
        menuGra = new JMenu("Gra");
        mNowaGra = new JMenuItem("Nowa Gra", 'N');
        mKoniecGry = new JMenuItem("Koniec Gry", 'K');

        menuRuchy = new JMenu("Ruchy");
        mZaznacz = new JMenuItem("Zaznacz");//
        mLewo = new JMenuItem("Ruch w lewo");//
        mPrawo = new JMenuItem("Ruch w prawo");//
        mGora = new JMenuItem("Ruch w gore");//
        mDol = new JMenuItem("Ruch w dol");//

        menuUstawienia = new JMenu("Ustawienia");

        rBrytyjska = new JRadioButtonMenuItem("Wersja Brytyjska");
        rEuropejska = new JRadioButtonMenuItem("Wersja Europejska");
        bgWersja = new ButtonGroup();

        zmienTlo = new JMenuItem("Zmien kolor tla");
        zmienKolor = new JMenu("Zmien kolor pionkow");
        mJednolite = new JMenuItem("Jednolite");
        mKolorowe = new JMenuItem("Kolorowe");

        menuPomoc = new JMenu("Pomoc");
        mOGrze = new JMenuItem("O Grze");
        mOAplikacji = new JMenuItem("O Aplikacji");
    }
    /**
     * Przylacza elementy do menu
     */
    private void polacz() {

        add(menuGra);
        menuGra.add(mNowaGra);
        menuGra.addSeparator();
        menuGra.add(mKoniecGry);
        this.add(menuGra);
        menuGra.add(mNowaGra);
        menuGra.addSeparator();
        menuGra.add(mKoniecGry);

        add(menuRuchy);
        menuRuchy.add(mZaznacz);
        menuRuchy.addSeparator();
        menuRuchy.add(mLewo);
        menuRuchy.add(mPrawo);
        menuRuchy.add(mGora);
        menuRuchy.add(mDol);

        add(menuUstawienia);
        bgWersja.add(rBrytyjska);
        bgWersja.add(rEuropejska);
        zmienKolor.add(mJednolite);
        zmienKolor.add(mKolorowe);
        menuUstawienia.add(rBrytyjska);
        menuUstawienia.add(rEuropejska);
        menuUstawienia.add(zmienTlo);
        menuUstawienia.add(zmienKolor);

        add(Box.createGlue());

        add(menuPomoc);
        menuPomoc.add(mOGrze);
        menuPomoc.add(mOAplikacji);

    }
    /**
     * Dodaje skroty klawiszowe do odpowiednich pol
     */
    private void dodajSkroty() {

        mZaznacz.setAccelerator(KeyStroke.getKeyStroke("Z"));
        mLewo.setAccelerator(KeyStroke.getKeyStroke("LEFT"));
        mPrawo.setAccelerator(KeyStroke.getKeyStroke("RIGHT"));
        mGora.setAccelerator(KeyStroke.getKeyStroke("UP"));
        mDol.setAccelerator(KeyStroke.getKeyStroke("DOWN"));

    }
    /**
     * Ustawia odpowiednie komendy dla sluchaczy pol
     */
    private void ustawKomendy() {

        mNowaGra.setActionCommand("NOWA GRA");
        mKoniecGry.setActionCommand("KONIEC GRY");
        mZaznacz.setActionCommand("ZAZNACZ");
        mLewo.setActionCommand("LEWO");
        mPrawo.setActionCommand("PRAWO");
        mGora.setActionCommand("GORA");
        mDol.setActionCommand("DOL");
        rBrytyjska.setActionCommand("BRYTYJSKA");
        rEuropejska.setActionCommand("EUROPEJSKA");
        zmienTlo.setActionCommand("ZMIEN TLO");
        mJednolite.setActionCommand("JEDNOLITE");
        mKolorowe.setActionCommand("KOLOROWE");
        mOGrze.setActionCommand("O GRZE");
        mOAplikacji.setActionCommand("O APLIKACJI");

    }
    /**
     * Dodaje sluchacza do pol
     * @param ual sluchacz
     */
    public void addListeners(ActionListener ual) {

        mNowaGra.addActionListener(ual);
        mKoniecGry.addActionListener(ual);
        mZaznacz.addActionListener(ual);
        mLewo.addActionListener(ual);
        mPrawo.addActionListener(ual);
        mGora.addActionListener(ual);
        mDol.addActionListener(ual);
        zmienTlo.addActionListener(ual);
        mJednolite.addActionListener(ual);
        mKolorowe.addActionListener(ual);
        mOGrze.addActionListener(ual);
        mOAplikacji.addActionListener(ual);
        rBrytyjska.addActionListener(ual);
        rEuropejska.addActionListener(ual);

    }
    /**
     * Pobiera od modelu dane i na ich podstawie aktualizuje swoj wyglad
     */
    public void aktualizuj() {
        boolean wyborMozliwy = model.wyborWersjiMozliwy();
        rEuropejska.setEnabled(wyborMozliwy);
        rBrytyjska.setEnabled(wyborMozliwy);
    }
}
