/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package widok;

import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Klasa <code>MyPopupMenu</code> jest klasa przechowujaca graficzny interfejs menu kontekstowego
 * @author M
 */
public class MyPopupMenu extends JPopupMenu {

    private JMenuItem mZmienKolor, mKliknij;
    /**
     * Konstruktor klasy <code>MyPopupMenu</code>.
     * @param ual sluchacz przechwytujacy zdarzenia wyboru opcji zmiany koloru lub klikniecia
     */
    public MyPopupMenu(ActionListener ual) {
        super("Co chcesz zrobic ?");
        deklaruj();
        polacz();
        ustawKomendy();
        addListeners(ual);
    }
    /**
     * Deklaracja pol klasy
     */
    private void deklaruj() {
        mZmienKolor = new JMenuItem("Zmien kolor pionka");
        mKliknij = new JMenuItem("Kliknij");
    }
    /**
     * Przylacza elementy do menu
     */
    private void polacz() {
        add(mZmienKolor);
        add(mKliknij);
    }
    /**
     * Ustawia odpowiednie komendy dla sluchaczy pol
     */
    private void ustawKomendy() {
        mZmienKolor.setActionCommand("ZMIEN KOLOR PIONKA");
        mKliknij.setActionCommand("KLIKNIJ");
    }
    /**
     * Dodaje sluchacza do pol
     * @param ual
     */
    private void addListeners(ActionListener ual) {
        mZmienKolor.addActionListener(ual);
        mKliknij.addActionListener(ual);
    }
}
