package chenille;

import chenille.anneaux.Anneau;
import chenille.anneaux.Tete;

public class Chenille {
	private IAnneau[] anneaux;

	public Chenille(int nbAnneaux, int xt, int yt) {
		anneaux = new IAnneau[nbAnneaux+1];
		anneaux[0] = new Tete(xt, yt);
	    for (int i = 1; i < nbAnneaux+1; ++i)
	    	anneaux[i] = new Anneau(xt - i, yt, anneaux[i-1]);
	}

	public void deplacer(int xMAX, int yMAX) {
		for (int i = anneaux.length -1; i >=0; --i)
			anneaux[i].deplacer(xMAX, yMAX);
	}

	public IAnneau anneau(int i) {
		assert i >= 0 && i < anneaux.length;
		return anneaux[i];
	}

	public int nbAnneaux() { // ne prend pas en compte la tête
		return anneaux.length -1;
	}
}
