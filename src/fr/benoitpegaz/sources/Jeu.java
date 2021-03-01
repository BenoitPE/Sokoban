package fr.benoitpegaz.sources;

public class Jeu {
    Niveau n;
    LecteurNiveaux l;

    Jeu(LecteurNiveaux lect) {
        l = lect;
    }

    Niveau niveau() {
        return n;
    }
    boolean prochainNiveau() {
        Niveau nouveau = l.lisProchainNiveau();
        if(nouveau != null) {
            n = nouveau;
            return true;
        } else {
            return false;
        }

    }
}
