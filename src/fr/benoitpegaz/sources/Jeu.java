package fr.benoitpegaz.sources;

public class Jeu {
    Niveau n;
    LecteurNiveaux l;

    Jeu(LecteurNiveaux lect) {
        l = lect;
        prochainNiveau();
    }

    public Niveau niveau() {
        return n;
    }

    public boolean deplace(int x, int y) {
        boolean resultat = n.deplace(x, y);
        if (n.estTermine())
            prochainNiveau();
        return resultat;
    }

    public void prochainNiveau() {
        Niveau nouveau = l.lisProchainNiveau();
        if (nouveau != null) {
            n = nouveau;
        } else {
            System.exit(0);
        }
    }


    public int lignePousseur() {
        return n.lignePousseur();
    }

    public int colonnePousseur() {
        return n.colonnePousseur();
    }

}
