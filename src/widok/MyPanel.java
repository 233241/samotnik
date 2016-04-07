/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package widok;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.JPanel;
import model.Model;

/**
 * Klasa <code>MyPanel</code> jest klasa odpowiedzialna za rysowanie planszy
 * @author M
 */
public class MyPanel extends JPanel {

    private Model model;
    /**
     * Konstruktor klasy <code>MyPanel</code>.
     * @param model obiekt przechowujacy logike gry, z ktorgo beda pobierane dane w celu ich narysowania
     */
    public MyPanel(Model model) {
        this.model = model;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int jW = getWidth()/9;
        int jH = getHeight()/9;
        boolean wersjaE = model.getWersjaE();
        for (int i = 3; i < 6; i++)
            for (int j = 1; j < 8; j++) {
                g2d.draw(new Rectangle.Double(i*jW, j*jH ,jW, jH));
                g2d.draw(new Rectangle.Double(j*jW, i*jH ,jW, jH));
            }
        if (wersjaE)
            g2d.draw(new Rectangle.Double(2*jW, 2*jH ,5*jW, 5*jH));
        if (model.getCzyPodswitlone()) {
            int h = model.getPodswietloneH();
            int w = model.getPodswietloneW();
            Rectangle2D rectangle = new Rectangle2D.Double((w+1)*jW, (h+1)*jH ,jW, jH);
            g2d.draw(rectangle);
            g2d.setPaint(Color.YELLOW);
            g2d.fill(rectangle);
            g2d.setPaint(Color.BLACK);
        }
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 7; j++) {
                Color kolor = model.getPionek(j, i);
                if (kolor != null) {
                    Ellipse2D ellipse = new Ellipse2D.Double((i+1)*jW, (j+1)*jH ,jW, jH);
                    g2d.draw(ellipse);
                    g2d.setPaint(kolor);
                    g2d.fill(ellipse);
                }
                g2d.setPaint(Color.BLACK);
            }
    }
    /**
     * Zmienia tlo
     * @param kolor nowy kolor tla
     */
    public void zmienKolorTla(Color kolor) {
        setBackground(kolor);
    }
}
