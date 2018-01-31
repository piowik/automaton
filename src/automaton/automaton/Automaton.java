package automaton.automaton;

import automaton.cell.Cell;
import automaton.coordinates.CellCoordinates;
import automaton.factory.CellStateFactory;
import automaton.neighborhood.CellNeighborhood;
import automaton.state.CellState;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * Automaton class that is base to the whole program.
 */
public abstract class Automaton {
    private final Map<CellCoordinates, CellState> cells = new HashMap<>();
    private final CellNeighborhood neighborsStrategy;
    private final CellStateFactory stateFactory;

    /**
     * Constructor for Automaton2Dim class.
     * @param neighborsStrategy strategy used to find neighbors
     * @param stateFactory State Factory used in generating map
     */
    Automaton(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory) {
        this.neighborsStrategy = neighborsStrategy;
        this.stateFactory = stateFactory;
    }

    /**
     * Method initializing map based on state factory.
     * @return void
     */
    void initializeMap() {
        CellIterator iterator = cellIterator();
        while (iterator.hasNext()) {
            CellCoordinates cellCoords = iterator.next();
            CellState state = stateFactory.initialState(cellCoords);
            cells.put(cellCoords, state);
        }
    }

    /**
     * Getter method returning cells map.
     * @return map with key of {@link automaton.coordinates.CellCoordinates} and value {@link automaton.state.CellState}
     */
    public Map<CellCoordinates, CellState> getCells() {
        return cells;
    }

    /**
     * Essential method that whole program is based on. It creates new automaton class and fills it with new cell stages,
     * based on current stages.
     * @return {@link automaton.automaton.Automaton}
     */
    public Automaton nextState() {
        Automaton newAuto = newInstance(stateFactory, neighborsStrategy);
        CellIterator iterator = newAuto.cellIterator();
        while (iterator.hasNext()) {
            CellCoordinates cellCoords = iterator.next();
            Cell c = new Cell(cells.get(cellCoords), cellCoords); // cell - state stored in cells map
            Set<CellCoordinates> neighbors = neighborsStrategy.cellNeighborhood(cellCoords);
            Set<Cell> mappedNeighbors = mapCoordinates(neighbors);
            CellState newState = nextCellState(c, mappedNeighbors);
            iterator.setState(newState);
        }
        return newAuto;
    }

    /**
     * Method used to insert predefined structures into map.
     * @param structure map with key of {@link automaton.coordinates.CellCoordinates} and value {@link automaton.state.CellState}
     * @return void
     */
    public void insertStructure(Map<? extends CellCoordinates, ? extends CellState> structure) {
        for (Map.Entry<? extends CellCoordinates, ? extends CellState> entry : structure.entrySet()) {
            cells.put(entry.getKey(), entry.getValue());
        }
    }

    private CellIterator cellIterator() {
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

        boolean hasNext() {
            return hasNextCoordinates(currentCoords);
        }

        CellCoordinates next() {
            currentCoords = nextCoordinates(currentCoords);
            return currentCoords;
        }

        void setState(CellState newState) {
            cells.put(currentCoords, newState);
        }
    }
}
