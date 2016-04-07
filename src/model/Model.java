/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.*;
import java.util.*;

/**
 * Klasa <code>Model</code> jest klasa odpowiedzialna za logike gry
 * @author M
 */
public class Model {

    private boolean wersjaE, czyPodswietlone, czyLosowykolor;
    private int faza, pozostalo, podswietlenie_h, podswietlenie_w;
    private Color kolorPionkow, kolorTla;
    private static ArrayList<Color> kolory;
    private Color[][] plansza;
    private boolean[][] dostepne;
    /**
     * Konstruktor klasy <code>Model</code>.
     */
    public Model() {
        wersjaE = false;
        czyPodswietlone = false;
        czyLosowykolor = false;
        kolorPionkow = Color.BLUE;
        kolorTla = Color.GRAY;
        nowaGra();
    }
    /**
     * Uruchomienie nowej gry
     */
    public void nowaGra() {
        faza = 0;
        czyPodswietlone = false;
        plansza = new Color[7][7];
        dostepne = new boolean[7][7];
        for (int i = 2; i < 5; i++)
            for (int j = 0; j < 7; j++) {
                plansza[i][j] = kolorPionkow;
                plansza[j][i] = kolorPionkow;
                dostepne[i][j] = true;
                dostepne[j][i] = true;
            }
        pozostalo = 32;
        if (wersjaE) {
            for (int i = 1; i < 6; i++)
                for (int j = 1; j < 6; j++) {
                    plansza[i][j] = kolorPionkow;
                    dostepne[i][j] = true;
                }
            pozostalo+=4;
        }
        plansza[3][3] = null;
        if (czyLosowykolor)
            losujKolory();
    }
    /**
     * Losowanie kolorow
     */
    private void losujKolory() {
        ArrayList<Color> koloryDoWyboru = (ArrayList<Color>) kolory.clone();
        Random r = new Random();
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 7; j++) {
                if (plansza[i][j] != null)
                    plansza[i][j] = koloryDoWyboru.remove(r.nextInt(koloryDoWyboru.size()));
            }
    }
    /**
     * Metoda symulujaca dzialanie aplikacji po zaznaczeniu pionka
     */
    public void zaznacz() {
        switch (faza) {
            case 0:
                czyPodswietlone = true;
                faza = 1;
                podswietlenie_h = 3;
                podswietlenie_w = 3;
                break;
            case 1:
                if (zajete(podswietlenie_h, podswietlenie_w)) {
                    faza = 2;
                }
                else {
                    faza = 0;
                    czyPodswietlone = false;
                }
                break;
        }
    }
    /**
     * Metoda symulujaca dzialanie aplikacji podczas ruchu
     * @param y2 pozycja docelowa
     * @param x2 pozycja docelowa
     * @param klawiatura <code>true</code> jesli ruch wykonywany jest za pomoca klawiatury, <code>false</code> jesli ruch wykonywany jest za pomoca myszy
     */
    public void ruch(int y2, int x2, boolean klawiatura) {
        switch (faza) {
            case 1:
                if (!dostepne(y2,x2)) {
                    faza = 0;
                    czyPodswietlone = false;
                }
                else {
                    podswietlenie_h=y2;
                    podswietlenie_w=x2;
                }
                break;
            case 2:
                if (klawiatura) {
                    x2 = x2+(x2-podswietlenie_w);
                    y2 = y2+(y2-podswietlenie_h);
                }
                if (wolne(y2, x2) && zajete((y2+podswietlenie_h)/2, (x2+podswietlenie_w)/2)&& zajete(podswietlenie_h, podswietlenie_w) && (Math.abs(x2-podswietlenie_w)==2 && y2==podswietlenie_h || Math.abs(y2-podswietlenie_h)==2 && x2==podswietlenie_w)) {
                    plansza[(podswietlenie_h+y2)/2][(podswietlenie_w+x2)/2] = null;
                    plansza[y2][x2] = plansza[podswietlenie_h][podswietlenie_w];
                    plansza[podswietlenie_h][podswietlenie_w] = null;
                    --pozostalo;
                }
                czyPodswietlone = false;
                if (czyMozliwyRuch())
                    faza = 0;
                else
                    faza = 3;
                break;
        }
    }
    /**
     * Metoda symulujaca dzialanie aplikacji podczas ruchu myszy
     * @param x pozycja
     * @param y pozycja
     */
    public void ruchMyszy(int y, int x) {
        switch (faza) {
            case 0:
                zaznacz();
                ruch(x,y,false);
                if (czyPodswietlone)
                    zaznacz();
                break;
            case 1:
                ruch(x,y,false);
                if (czyPodswietlone)
                    zaznacz();
                break;
            case 2:
                ruch(x,y,false);
                break;
        }
    }
    /**
     * Sprawdza czy pole lezy na planszy
     * @param h pozycja pola
     * @param w pozycja pola
     * @return <code>true</code> jesli pole lezy na planszy, <code>false</code> w przeciwnym przypadku
     */
    private boolean dostepne(int h, int w) {
        return 0<=h&&h<=6&&0<=w&&w<=6 && dostepne[h][w];
    }
    /**
     * Sprawdza czy na danym polu znajduje sie pionek
     * @param h pozycja pola
     * @param w pozycja pola
     * @return <code>true</code> jesli na danycm polu znajduje sie pionek, <code>false</code> w przeciwnym przypadku
     */
    private boolean zajete(int h, int w) {
        return dostepne(h, w) && plansza[h][w] !=  null;
    }
    /**
     * Sprawdza na danym polu nie znajduje sie pionek
     * @param h pozycja pola
     * @param w pozycja pola
     * @return <code>true</code> jesli na danycm polu nie znajduje sie pionek, <code>false</code> w przeciwnym przypadku
     */
    private boolean wolne(int h, int w) {
        return dostepne(h, w) && plansza[h][w] ==  null;
    }
    /**
     * Sprawdza jest jeszcze mozliwy jakis ruch
     * @return <code>true</code> jesli jest mozliwy, <code>false</code> w przeciwnym przypadku
     */
    private boolean czyMozliwyRuch() {
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 7; j++) {
                if (zajete(i,j)&&zajete(i+1,j)&&wolne(i+2,j) || wolne(i,j)&&zajete(i+1,j)&&zajete(i+2,j) || zajete(i,j)&&zajete(i,j+1)&&wolne(i,j+2) || wolne(i,j)&&zajete(i,j+1)&&zajete(i,j+2))
                    return true;
            }
        return false;
    }
    /**
     * Zwraca kolor pionka
     * @param j pozycja pionka
     * @param i pozycja pionka
     * @return kolor pionka
     */
    public Color getPionek(int j, int i) {
        return plansza[j][i];
    }
    /**
     * Zwraca kolor tla
     * @return kolor tla
     */
    public Color getKolorTla() {
        return kolorTla;
    }
    /**
     * Zmienia kolor tla
     * @param nowyKolorTla nowy kolor tla
     */
    public void setKolorTla(Color nowyKolorTla) {
        kolorTla = nowyKolorTla;
    }
    /**
     * Zwraca kolor pionkow
     * @return kolor pionkow
     */
    public Color getKolorPionkow() {
        return kolorPionkow;
    }
    /**
     * Zwraca informacje czy jakies pole jest podswietlone
     * @return <code>true</code> jesli jakies pole jest podswietlone, <code>false</code> w przeciwnym przypadku
     */
    public boolean getCzyPodswitlone() {
        return czyPodswietlone;
    }
    /**
     * Zwraca pozycje podswietlonego pola
     * @return pozycja podswieltonego pola
     */
    public int getPodswietloneH() {
        return podswietlenie_h;
    }
    /**
     * Zwraca pozycje podswietlonego pola
     * @return pozycja podswieltonego pola
     */
    public int getPodswietloneW() {
        return podswietlenie_w;
    }
    /**
     * Zwraca liczbe pozostalych na planszy pionkow
     * @return liczba pozostalych na planszy pionkow
     */
    public int getPozostalo() {
        return pozostalo;
    }
    /**
     * Zwraca informacje czy gra jest skonczona
     * @return <code>true</code> gra jest skonczona, <code>false</code> w przeciwnym przypadku
     */
    public boolean getCzyGraSkonczona() {
        return !czyMozliwyRuch();
    }
    /**
     * Ustala kolor pionka
     * @param x pozycja pionka
     * @param y pozycja pionka
     * @param nowyKolorPionka nowy kolor pionka
     */
    public void setKolorPionka(int x, int y, Color nowyKolorPionka) {
        if (zajete(x, y))
            plansza[x][y] = nowyKolorPionka;
    }
    /**
     * Zwraca wersje gry
     * @return <code>true</code> jesli gra jest w wersji Europejskiej, <code>false</code> jesli jest w wersji Brytyjskiej
     */
    public boolean getWersjaE() {
        return wersjaE;
    }
    /**
     * Ustala wersje gry
     * @param b <code>true</code> jesli gra ma byc w wersji Europejskiej, <code>false</code> jesli ma byc w wersji Brytyjskiej
     */
    public void setWersjaE(boolean b) {
        this.wersjaE = b;
    }
    /**
     * Ustala kolor pionkow na planszy
     * @param wybranyKolor nowy kolor pionkow
     */
    public void setKolorPionkow(Color wybranyKolor) {
        czyLosowykolor = false;
        this.kolorPionkow = wybranyKolor;
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 7; j++)
                setKolorPionka(i, j, kolorPionkow);
    }
    /**
     * Zwraca informacje czy mozna w danym momenci zmienic wersje gry
     * @return <code>true</code> jesli zmiana wersji gry jest mozliwa, <code>false</code> w przeciwnym przypadku
     */
    public boolean wyborWersjiMozliwy() {
        return faza == 3;
    }
    /**
     * Ustala losowy kolor kazdego z pionkow
     */
    public void setKolorowe() {
        czyLosowykolor = true;
        losujKolory();
    }
    /**
     * pole statyczne inicjalizujace liste kolorow do losowania
     */
    static {
        kolory = new ArrayList<>();
        for (int i = 40; i < 230; i+=40)
            for (int j = 40; j < 230; j+=40)
                for (int k = 40; k < 230; k+=40)
                    kolory.add(new Color(i,j,k));
    }
}

