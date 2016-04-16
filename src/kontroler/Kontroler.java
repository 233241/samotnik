/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import java.awt.*;;
import java.awt.event.*;
import javax.swing.*;
import model.Model;
import widok.*;

/**
 * Klasa <code>Kontroler</code> jest klasa odpowiedzialna za obsluge zadan uzytkownika
 * @author M
 */
public class Kontroler {

    private Model model;
    private Widok widok;
    private MyPopupMenu menu;
    private String OGrze = "Tutaj beda informacje o grze";
    private String OAplikacji = "Autor:   M\nWersja:   1.0\nData powstania:   25 11 2013";
    private Point lastRightClick, pozWTablicy;
    private Color staryKolor, nowyKolor;
    /**
     * Konstruktor klasy <code>Kontroler</code>.
     * @param model obiekt przechowujacy logike gry
     * @param widok obiekt odpowiedzialy za wyglad gry
     */
    public Kontroler(Model model, Widok widok) {
        this.model = model;
        this.widok = widok;
        widok.addListeners(new UniversalListener(), new MyMouseListener());
        menu = new MyPopupMenu(new UniversalListener());
    }


    /**
     * Klasa wewnetrzna<code>UniversalListener</code> jest sluchaczem zdarzen pochodzacych od przyciskow i pol
     */
    private class UniversalListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String komenda = e.getActionCommand();
            switch (komenda) {
                case "NOWA GRA":
                    model.nowaGra();
                    widok.aktualizuj();
                    break;
                case "KONIEC GRY":
                    widok.zakoncz();
                    break;
                case "ZAZNACZ":
                    model.zaznacz();
                    widok.aktualizuj();
                    break;
                case "LEWO":
                    model.ruch(model.getPodswietloneH(),model.getPodswietloneW()-1,true);
                    widok.aktualizuj();
                    break;
                case "PRAWO":
                    model.ruch(model.getPodswietloneH(),model.getPodswietloneW()+1,true);
                    widok.aktualizuj();
                    break;
                case "GORA":
                    model.ruch(model.getPodswietloneH()-1,model.getPodswietloneW(),true);
                    widok.aktualizuj();
                    break;
                case "DOL":
                    model.ruch(model.getPodswietloneH()+1,model.getPodswietloneW(),true);
                    widok.aktualizuj();
                    break;
                case "BRYTYJSKA":
                    model.setWersjaE(false);
                    break;
                case "EUROPEJSKA":
                    model.setWersjaE(true);
                    break;
                case "ZMIEN TLO":
                    staryKolor = model.getKolorTla();
                    nowyKolor = JColorChooser.showDialog(null, "Wybierz kolor tla planszy", staryKolor);
                    if (nowyKolor != null)
                        model.setKolorTla(nowyKolor);
                    widok.aktualizuj();
                    break;
                case "JEDNOLITE":
                    staryKolor = model.getKolorPionkow();
                    nowyKolor = JColorChooser.showDialog(null, "Wybierz kolor pionkow na planszy", staryKolor);
                    if (nowyKolor != null)
                        model.setKolorPionkow(nowyKolor);
                    widok.aktualizuj();
                    break;
                case "KOLOROWE":
                    model.setKolorowe();
                    widok.aktualizuj();
                    break;
                case "O GRZE":
                    JOptionPane.showMessageDialog(null, OGrze, "O Grze", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "O APLIKACJI":
                    JOptionPane.showMessageDialog(null, OAplikacji, "O Aplikacji", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "ZMIEN KOLOR PIONKA":
                    pozWTablicy = toPozWTablicy(lastRightClick);
                    staryKolor = model.getKolorPionkow();
                    nowyKolor = JColorChooser.showDialog(null, "Wybierz kolor zaznaczonego pionka", staryKolor);
                    if (nowyKolor != null)
                        model.setKolorPionka(pozWTablicy.x, pozWTablicy.y, nowyKolor);
                    widok.aktualizuj();
                    break;
                case "KLIKNIJ":
                    Point pozycja = toPozWTablicy(lastRightClick);
                    model.ruchMyszy(pozycja.y, pozycja.x);
                    widok.aktualizuj();
                    break;
            }
        }
    }
    /**
     * Klsa wewnetrzna<code>MyMouseListener</code> jest sluchaczem zdarzen pochodzacych od myszy
     */
    private class MyMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                Point pozycja = toPozWTablicy(e.getPoint());
                model.ruchMyszy(pozycja.y, pozycja.x);
                widok.aktualizuj();
            }
            if (e.getButton() == MouseEvent.BUTTON3) {
                lastRightClick = e.getPoint();
                menu.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }
    /**
     * Konwertuje pozycje na planszy na pozycje w tablicy gry
     * @param last pozycja na planszy
     * @return pozycja w tablicy gry
     */
    private Point toPozWTablicy(Point last) {
        Dimension size = widok.getPanelSize();
        int jX = size.width/9;
        int jY = size.height/9;
        return new Point(last.y / jY-1, last.x / jX-1);
    }

    public void uruchom() {
        widok.setVisible(true);
    }
}
