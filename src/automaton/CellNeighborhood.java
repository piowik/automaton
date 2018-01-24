package automaton;

import java.util.Set;

public interface CellNeighborhood {
    public Set<CellCoordinates> cellNeighborhood(CellCoordinates cell);
}
