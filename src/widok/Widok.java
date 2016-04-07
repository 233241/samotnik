/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package widok;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.Model;

/**
 * Klasa <code>Widok</code> jest klasa odpowiedzialna za wyglad aplikacji od strony graficznej
 * @author M
 */
public class Widok extends JFrame {

    private Model model;
    private MyMenuBar menuBar;
    private MyPanel panel;
    private JLabel label;
    /**
     * Konstruktor klasy <code>Widok</code>.
     * @param model obiekt przechowujacy logike gry
     */
    public Widok(Model model) {
        this.model = model;

        deklaruj();
        polacz();

        this.setTitle("Gra samotnik");
        setLocation(100,100);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        setSize(700,700);
        setResizable(false);
        aktualizuj();
    }
    /**
     * deklaracja pol klasy
     */
    private void deklaruj() {
        menuBar = new MyMenuBar(model);
        panel = new MyPanel(model);
        label = new JLabel("STAN GRY");
    }
    /**
     * Przylacza elementy do menu
     */
    private void polacz() {
        setLayout(new BorderLayout());
        setJMenuBar(menuBar);
        add(panel,BorderLayout.CENTER);
        add(label,BorderLayout.SOUTH);
    }
    /**
     * Aktualizuje stan swoich komponentow
     */
    public void aktualizuj() {
        menuBar.aktualizuj();
        panel.zmienKolorTla(model.getKolorTla());
        menuBar.aktualizuj();
        if (model.getCzyGraSkonczona())
            label.setText("Gra skonczona, pozostalo: " + model.getPozostalo() + " pionkow");
        else
            label.setText("Gra trwa, pozostalo: " + model.getPozostalo() + " pionkow");
        panel.repaint();
    }
    /**
     * Konczy dzialanie aplikacji
     */
    public void zakoncz() {
        dispose();
    }
    /**
     * Dodaje sluchaczy do pol
     * @param ual sluchacz ogolny
     * @param mal akcji myszy
     */
    public void addListeners(ActionListener ual, MouseListener mal) {
        menuBar.addListeners(ual);
        panel.addMouseListener(mal);
    }
    /**
     * Zwraca rozmiary palety, na ktorej rysowana jest plansza
     * @return wymiary
     */
    public Dimension getPanelSize() {
        return panel.getSize();
    }
}
