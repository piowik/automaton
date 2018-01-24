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
        // map initialization
        CellIterator iterator = cellIterator();
        while (iterator.hasNext()) {
            CellCoordinates cellCoords = iterator.next();
            cells.put(cellCoords,stateFactory.initialState(cellCoords));
        }
    }

    public Map<CellCoordinates, CellState> getCells() {
        return cells;
    }

    public Automaton nextState() {
        // TODO: check
        Automaton newAuto = newInstance(stateFactory, neighborsStrategy);
        CellIterator iterator = newAuto.cellIterator();
        while (iterator.hasNext()) {
            CellCoordinates cellCoords = iterator.next();
            Cell c = new Cell(cells.get(cellCoords), cellCoords); // cell - state stored in cells map
            Set<CellCoordinates> neighbors = neighborsStrategy.cellNeighborhood(cellCoords);
            Set<Cell> mappedNeighbors = mapCoordinates(neighbors);
            CellState newState = nextCellState(c, mappedNeighbors);
            iterator.setState(newState);
//            iterator.next();
        }
        return newAuto;
    }

    public void insertStructure(Map<? extends CellCoordinates, ? extends CellState> structure) {
        for (Map.Entry<? extends CellCoordinates, ? extends CellState> entry : structure.entrySet())
        {
            cells.put(entry.getKey(), entry.getValue());
        }
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

        public CellCoordinates next() {
            currentCoords = nextCoordinates(currentCoords);
            return currentCoords;
        }

        public void setState(CellState newState) {
            cells.put(currentCoords, newState);
        }

    }
}
