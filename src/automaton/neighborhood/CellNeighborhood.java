package automaton.neighborhood;

import automaton.coordinates.CellCoordinates;

import java.util.Set;

/**
 * Interface for neighborhoods
 */
public interface CellNeighborhood {
    Set<CellCoordinates> cellNeighborhood(CellCoordinates cell);
}
