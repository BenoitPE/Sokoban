package fr.benoitpegaz.sources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

import fr.benoitpegaz.sources.Global.Configuration;


public class NiveauGraphique extends JComponent {
    Image pousseur, mur, sol, caisse, but, caisseSurBut;
    Jeu j;
    int largeurCase;
    int hauteurCase;

    NiveauGraphique(Jeu jeu) {
        j = jeu;
        pousseur = lisImage("Pousseur");
        mur = lisImage("Mur");
        sol = lisImage("Sol");
        caisse = lisImage("Caisse");
        but = lisImage("But");
        caisseSurBut = lisImage("CaisseSurBut");
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

    private void tracer(Graphics2D g, Image i, int x, int y, int l, int h) {
        g.drawImage(i, x, y, l, h, null);
    }

    public void paintComponent(Graphics g) {
        Graphics2D drawable = (Graphics2D) g;
        Niveau n = j.niveau();

        int largeur = getSize().width;
        int hauteur = getSize().height;
        largeurCase = largeur / n.colonnes();
        hauteurCase = hauteur / n.lignes();
        // On prend des cases carrées
        largeurCase = Math.min(largeurCase, hauteurCase);
        hauteurCase = largeurCase;

        // On efface tout
        drawable.clearRect(0, 0, largeur, hauteur);
        for (int ligne = 0; ligne < n.lignes(); ligne++)
            for (int colonne = 0; colonne < n.colonnes(); colonne++) {
                int x = colonne * largeurCase;
                int y = ligne * hauteurCase;
                // Tracé du sol
                if (n.aBut(ligne, colonne))
                    tracer(drawable, but, x, y, largeurCase, hauteurCase);
                else
                    tracer(drawable, sol, x, y, largeurCase, hauteurCase);
                // Tracé des objets
                if (n.aMur(ligne, colonne))
                    tracer(drawable, mur, x, y, largeurCase, hauteurCase);
                else if (n.aPousseur(ligne, colonne))
                    tracer(drawable, pousseur, x, y, largeurCase, hauteurCase);
                else if (n.aCaisse(ligne, colonne))
                    if (n.aBut(ligne, colonne))
                        tracer(drawable, caisseSurBut, x, y, largeurCase, hauteurCase);
                    else
                        tracer(drawable, caisse, x, y, largeurCase, hauteurCase);
            }
    }

    int hauteurCase() {
        return hauteurCase;
    }

    int largeurCase() {
        return largeurCase;
    }
}
