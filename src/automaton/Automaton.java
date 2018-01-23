package automaton;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public abstract class Automaton {
    private Map<CellCoordinates, CellState> cells;
    private CellNeighborhood neighborsStrategy;
    private CellStateFactory stateFactory;

    public Automaton(Map<CellCoordinates, CellState> cells, CellNeighborhood neighborsStrategy, CellStateFactory stateFactory) {
        this.cells = cells;
        this.neighborsStrategy = neighborsStrategy;
        this.stateFactory = stateFactory;
    }

    public Automaton nextState() {
        //TODO
    }

    public void insertStructure(Map<? extends CellCoordinates, ? extends CellState> structure) {
        //TODO
    }


    public CellIterator cellIterator() {
        return new CellIterator();
    }


    protected abstract Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood);

    protected abstract boolean hasNextCoordinates(CellCoordinates coords);

    protected abstract CellCoordinates initialCoordinates(CellCoordinates coords);

    protected abstract CellCoordinates nextCoordinates(CellCoordinates coords);

    protected abstract CellState nextCellState(CellState currentState, Set<Cell> neighborsStates);

    private Set<Cell> mapCoordinates(Set<CellCoordinates> coords) {
        // TODO
    }

    class CellIterator {
        private CellCoordinates currentCoords;

        public boolean hasNext() {
            return hasNextCoordinates(currentCoords);
        }

        public void next() {
            if (hasNext())
                currentCoords = nextCoordinates(currentCoords);
        }

        public void setState(CellState newState) {
            cells.put(currentCoords, newState);
        }

    }
}
