package automaton;

import java.util.Set;

public interface CellNeighborhood {
    default public Set<CellCoordinates> cellNeighborhood(CellCoordinates cell) {
        return null;
    }
}
