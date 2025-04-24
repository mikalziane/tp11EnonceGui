package geometrie;
import java.util.Random;

public enum Direction {
  EST(1, 0), NORD_EST(1, 1), NORD(0, 1), NORD_OUEST(-1, 1), OUEST (-1, 0), SUD_OUEST(-1, -1), SUD(0, -1), SUD_EST(1, -1);
  
  // d�placement relatif de la direction
  private final int dx, dy;

  private Direction(int dx, int dy) {
    this.dx = dx; this.dy = dy;
  }

  /** Retourne le d�placement sur l'axe des x correspondant � la
   * direction courante.
   * @return le d�placement en x
   */
  public int getDx() {
    return dx;
  }

  /** Retourne le d�placement sur l'axe des y correspondant � la
   * direction courante.
   * @return le d�placement en y
   */
  public int getDy() {
    return dy;
  }
  /** Retourne une direction inverse � la direction courante.
   * @return la direction oppos�e
   */
  public Direction inverser() {
    return Direction.values()[(this.ordinal() + 4) % 8];
  }
  
  /** Retourne une direction d�riv�e par rapport � la 
   * direction courante d'un angle al�atoirement choisi et
   * compris entre (-marge * 45�) et (+marge * 45�). La d�rivation
   * est choisie n�cessairement pour �tre un multiple de 45�.
   * @param marge la tol�rance
   * @return la nouvelle direction
   */
  public Direction deriver(int marge) {
    Random rd = new Random();
    int d = (8 + this.ordinal() + (rd.nextInt(marge * 2 + 1) - marge)) % 8;
    return Direction.values()[d];
  }
}
