/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import kontroler.Kontroler;
import model.Model;
import widok.Widok;

/**
 * Klasa <code> Main </code> jest glowna klasa programu, sluzy do uruchamiania gry
 * @author M
 */
public class Main {
    /**
     * Konstruktor klasy <code>Main</code>.
     */
    public static void main(String[] args) {
        Model model = new Model();
        Widok widok = new Widok(model);

        Kontroler kontroler = new Kontroler(model, widok);
        kontroler.uruchom();
    }

}

