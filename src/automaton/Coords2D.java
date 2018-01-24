package automaton;

public class Coords2D implements CellCoordinates {
    public int x;
    public int y;

    Coords2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coords2D other = (Coords2D) obj;
        if (other.x == x && other.y == y)
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return (1000 * x) + y; // TODO: is 1000 enough? another way?
    }
}
