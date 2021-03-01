package fr.benoitpegaz.sources;

import javax.swing.*;
import java.awt.*;

public class InterfaceGraphique implements Runnable {
    Jeu j;
    JFrame frame;

    InterfaceGraphique(Jeu jeu) {
        j = jeu;
    }

    static void demarrer(Jeu j) {
        SwingUtilities.invokeLater(new InterfaceGraphique(j));
    }

    public void run() {
        frame = new JFrame("Sokoban");
        frame.add(new NiveauGraphique(j));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

}
