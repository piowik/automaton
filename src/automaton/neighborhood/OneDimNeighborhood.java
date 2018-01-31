package automaton.neighborhood;

import automaton.coordinates.CellCoordinates;
import automaton.coordinates.Coords1D;

import java.util.HashSet;
import java.util.Set;

/**
 * Class used for getting cell's neighbors in one dimensional automaton
 */
public class OneDimNeighborhood implements CellNeighborhood {

    /**
     * Constructor for OneDimNeighborhood class
     */
    public OneDimNeighborhood() {

    }

    /**
     * Returns neighbors in one dimensional world, previous and next cell.
     *
     * @param cellCoordinates cell's coordinates
     * @return set of {@link automaton.coordinates.Coords1D}
     */
    public Set<CellCoordinates> cellNeighborhood(CellCoordinates cellCoordinates) {
        Coords1D coords = (Coords1D) cellCoordinates;
        Set<CellCoordinates> neighbors = new HashSet<>();
        for (int i = -1; i <= 1; i++) {
            neighbors.add(new Coords1D(coords.x - i));
        }
        return neighbors;
    }
}