package fr.benoitpegaz.sources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

import fr.benoitpegaz.sources.Global.Configuration;

public class NiveauGraphique extends JComponent {
    Jeu j;
    Image but, caisse, caisseSurBut, mur, pousseur, sol;

    public NiveauGraphique(Jeu jeu) {
        j = jeu;
        but = lisImage("But");
        caisse = lisImage("Caisse");
        caisseSurBut = lisImage("CaisseSurBut");
        mur = lisImage("Mur");
        pousseur = lisImage("Pousseur");
        sol = lisImage("Sol");

    }

    private Image lisImage(String nom) {
        String ressource = Configuration.instance().lis(nom);
        Configuration.instance().logger().info("Lecture de l'image " + ressource + " comme " + nom);
        InputStream in = Configuration.charge(ressource);
        try {
            // Chargement d'une image utilisable dans Swing
            return ImageIO.read(in);
        } catch (Exception e) {
            Configuration.instance().logger().severe("Impossible de charger l'image " + ressource);
            System.exit(1);
        }
        return null;
    }


    @Override
    public void paintComponent(Graphics g) {
        Graphics2D drawable = (Graphics2D) g;
        Niveau n = j.niveau();

        int largeur = getSize().width;
        int hauteur = getSize().height;
        int largeurCase = largeur / n.colonnes();
        int hauteurCase = hauteur / n.lignes();

        drawable.clearRect(0, 0, largeur, hauteur);
        for (int ligne = 0; ligne < n.lignes(); ligne++) {
            for (int colonne = 0; colonne < n.colonnes(); colonne++) {
                int x = colonne * largeurCase;
                int y = ligne * hauteurCase;

                if (n.aBut(ligne, colonne)) {
                    g.drawImage(but, x, y, largeurCase, hauteurCase, null);
                } else {
                    g.drawImage(sol, x, y, largeurCase, hauteurCase, null);
                }

                if (n.aMur(ligne, colonne)) {
                    g.drawImage(mur, x, y, largeurCase, hauteurCase, null);
                } else if (n.aCaisse(ligne, colonne)) {
                    g.drawImage(caisse, x, y, largeurCase, hauteurCase, null);
                } else if (n.aPousseur(ligne, colonne)) {
                    g.drawImage(pousseur, x, y, largeurCase, hauteurCase, null);
                }
            }
        }


    }
}
