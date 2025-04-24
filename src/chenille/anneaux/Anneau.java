package chenille.anneaux;

import chenille.IAnneau;

public class Anneau implements IAnneau {
	private int x, y;
	private IAnneau pred;

	public Anneau(int x, int y, IAnneau pred) {
		placerA(x,y);
		this.pred = pred;
	}

	public int x() { return x; }
	public int y() {
		return y;
	}
	
	public void placerA(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void deplacer(int XMax, int YMax) {
		placerA(pred.x(), pred.y());
	}

}
