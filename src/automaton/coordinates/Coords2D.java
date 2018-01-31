package automaton.coordinates;

/**
 * Two dimensional coordinates
 */
public class Coords2D implements CellCoordinates {
    public int x;
    public int y;


    /**
     * Constructor for Coords2D class.
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public Coords2D(int x, int y) {
        this.x = x;
        this.y = y;
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
        Coords2D other = (Coords2D) obj;
        return other.x == x && other.y == y;
    }

    /**
     * Method giving hash code of a coordinate, needed to compare in HashSet
     *
     * @return boolean
     */

    @Override
    public int hashCode() {
        return (1000 * x) + y; // TODO: is 1000 enough? another way?
    }
}
