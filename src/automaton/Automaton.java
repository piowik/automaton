package automaton;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Automaton {
    private Map<CellCoordinates, CellState> cells;
    private CellNeighborhood neighborsStrategy;
    private CellStateFactory stateFactory;

    public Automaton(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory) {
        this.neighborsStrategy = neighborsStrategy;
        this.stateFactory = stateFactory;
    }

//    Automaton(Map<CellCoordinates, CellState> cells) {
//        this.cells = cells;
//    }


    public Map<CellCoordinates, CellState> getCells() {
        return cells;
    }

    public Automaton nextState() {
        // TODO: check
        Automaton newAuto = newInstance(stateFactory, neighborsStrategy);
        CellIterator iterator = newAuto.cellIterator();
        while (iterator.hasNext()) {
            Cell c = iterator.next();
            Set<CellCoordinates> neighbors = neighborsStrategy.cellNeighborhood(c.coords);
            Set<Cell> mappedNeighbors = mapCoordinates(neighbors);
            CellState newState = nextCellState(c, mappedNeighbors);
            iterator.setState(newState);
//            iterator.next();
        }
        return newAuto;
    }

    public void insertStructure(Map<? extends CellCoordinates, ? extends CellState> structure) {
        //TODO
    }


    public CellIterator cellIterator() {
        return new CellIterator();
    }


    protected abstract Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood neighborsStrategy);

    protected abstract boolean hasNextCoordinates(CellCoordinates coords);

    protected abstract CellCoordinates initialCoordinates();

    protected abstract CellCoordinates nextCoordinates(CellCoordinates coords);

    protected abstract CellState nextCellState(Cell targetCell, Set<Cell> neighborsStates);

    private Set<Cell> mapCoordinates(Set<CellCoordinates> coords) {
        Set<Cell> cellsHashSet = new HashSet<>();
        for (CellCoordinates coordinates : coords)
            cellsHashSet.add(new Cell(cells.get(coordinates), coordinates));
        return cellsHashSet;
    }

    class CellIterator {
        private CellCoordinates currentCoords = initialCoordinates();

        public boolean hasNext() {
            return hasNextCoordinates(currentCoords);
        }

        public Cell next() {
            currentCoords = nextCoordinates(currentCoords);
            return new Cell(cells.get(currentCoords), currentCoords);
        }

        public void setState(CellState newState) {
            cells.put(currentCoords, newState);
        }

    }
}
