package chenille.anneaux;
import static geometrie.Direction.EST;

import geometrie.Direction;

public class Tete extends Anneau {
	private Direction cap;
	
	public Tete(int x, int y, Direction cap) {
		super(x, y, null);
		this.cap = cap;
	}
	
	public Tete(int x, int y) {
		this(x,y,EST);
	}
	public Direction direction() {	return cap;	}

	public void deplacer(int xMax, int yMax) {
		cap = cap.deriver(1);
		super.placerA((x()+cap.getDx() + xMax)%xMax, (y()+cap.getDy()+yMax)%yMax);
	}
}
