package automaton.coordinates;


/**
 * One dimensional coordinates
 */
public class Coords1D implements CellCoordinates {
    public final int x;

    /**
     * Constructor for Coords1D class.
     *
     * @param x x coordinate
     */
    public Coords1D(int x) {
        this.x = x;
    }


    /**
     * Method used to compare Coordinates in HashSet
     *
     * @param obj Object class
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coords1D other = (Coords1D) obj;
        return other.x == x;
    }


    /**
     * Method giving hash code of a coordinate, needed to compare in HashSet
     *
     * @return boolean
     */

    @Override
    public int hashCode() {
        return x;
    }
}
