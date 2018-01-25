package automaton.neighborhood;

import automaton.coordinates.CellCoordinates;

import java.util.Set;

public interface CellNeighborhood {
    Set<CellCoordinates> cellNeighborhood(CellCoordinates cell);
}
