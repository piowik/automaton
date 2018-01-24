package automaton;

public class Coords1D implements CellCoordinates  {
    public int x;

    Coords1D(int x) {
        this.x = x;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coords1D other = (Coords1D) obj;
        if (other.x == x)
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return x;
    }
}
