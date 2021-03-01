package fr.benoitpegaz.sources;

import javax.swing.*;
import java.awt.*;

public class InterfaceGraphique implements Runnable {
    Jeu j;
    JFrame frame;
    boolean maximized;

    InterfaceGraphique(Jeu jeu) {
        j = jeu;
    }

    static void demarrer(Jeu j) {
        SwingUtilities.invokeLater(new InterfaceGraphique(j));
    }

    public void toggleFullscreen() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        if (maximized) {
            device.setFullScreenWindow(null);
            maximized = false;
        } else {
            device.setFullScreenWindow(frame);
            maximized = true;
        }
    }

    public void run() {
        frame = new JFrame("Ma fenetre a moi");
        NiveauGraphique niv = new NiveauGraphique(j);
        niv.addMouseListener(new AdaptateurSouris(j, niv));
        frame.addKeyListener(new AdaptateurClavier(j, niv, this));
        frame.add(niv);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }
}
