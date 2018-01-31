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
     *
     * @param neighborsStrategy strategy used to find neighbors
     * @param stateFactory      State Factory used in generating map
     */
    Automaton(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory) {
        this.neighborsStrategy = neighborsStrategy;
        this.stateFactory = stateFactory;
    }

    /**
     * Method initializing map based on state factory.
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
     *
     * @return map with key of {@link automaton.coordinates.CellCoordinates} and value {@link automaton.state.CellState}
     */
    public Map<CellCoordinates, CellState> getCells() {
        return cells;
    }

    /**
     * Essential method that whole program is based on. It creates new automaton class and fills it with new cell states,
     * based on current states.
     *
     * @return {@link Automaton}
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
     *
     * @param structure map with key of {@link automaton.coordinates.CellCoordinates} and value {@link automaton.state.CellState}
     */
    public void insertStructure(Map<? extends CellCoordinates, ? extends CellState> structure) {
        for (Map.Entry<? extends CellCoordinates, ? extends CellState> entry : structure.entrySet()) {
            cells.put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Method creates and returns an instance of CellIterator
     *
     * @return an instance of {@link CellIterator}
     */
    private CellIterator cellIterator() {
        return new CellIterator();
    }

    /**
     * Method creates a new instance of Automaton, used in {@link #nextState()}
     *
     * @param cellStateFactory  current {@link CellStateFactory}
     * @param neighborsStrategy current neighborhood strategy, instance of {@link CellNeighborhood}
     * @return a new instance of {@link Automaton}
     */
    protected abstract Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood neighborsStrategy);

    /**
     * Used by {@link CellIterator}, overwritten in 1dim/2dim automatons so it works with both 1d and 2d environments
     *
     * @param coords coordinates to check
     * @return true if there is next coordinate, false otherwise
     * @see Automaton1Dim#hasNextCoordinates(CellCoordinates)
     * @see Automaton2Dim#hasNextCoordinates(CellCoordinates)
     */
    protected abstract boolean hasNextCoordinates(CellCoordinates coords);

    /**
     * Method used for setting iterator's initial coordinates, overwritten in 1dim/2dim automatons
     * so it works with both 1d and 2d environments
     *
     * @return {@link automaton.coordinates.CellCoordinates} initial iterator's coordinates
     * @see Automaton1Dim#initialCoordinates()
     * @see Automaton2Dim#initialCoordinates()
     */
    protected abstract CellCoordinates initialCoordinates();

    /**
     * Method returns next cell coordinates, overwritten in 1dim/2dim automatons
     * so it works with both 1d and 2d environments
     *
     * @param coords current coordinates
     * @return {@link automaton.coordinates.CellCoordinates}
     * @see Automaton1Dim#nextCoordinates(CellCoordinates)
     * @see Automaton2Dim#nextCoordinates(CellCoordinates)
     */
    protected abstract CellCoordinates nextCoordinates(CellCoordinates coords);

    /**
     * Method which returns next cell state based on current cell state and current cell neighbors,
     * overwritten in each automaton with corresponding rules
     *
     * @param targetCell     current {@link Cell} that we want next state of
     * @param neighborsCells all {@link Cell} neighbors with corresponding states and coordinates
     * @return {@link CellState}
     * @see GameOfLife#nextCellState(Cell, Set)
     * @see LangtonAnt#nextCellState(Cell, Set)
     * @see WireWorld#nextCellState(Cell, Set)
     * @see OneDimAutomaton#nextCellState(Cell, Set)
     */
    protected abstract CellState nextCellState(Cell targetCell, Set<Cell> neighborsCells);

    /**
     * Method return cells from coordinates passed in argument
     *
     * @param coords set of {@link automaton.coordinates.CellCoordinates}
     * @return set of {@link automaton.cell.Cell}
     */
    private Set<Cell> mapCoordinates(Set<CellCoordinates> coords) {
        Set<Cell> cellsHashSet = new HashSet<>();
        for (CellCoordinates coordinates : coords)
            cellsHashSet.add(new Cell(cells.get(coordinates), coordinates));
        return cellsHashSet;
    }

    /**
     * CellIterator class used for iterating through cells map
     */
    class CellIterator {
        private CellCoordinates currentCoords = initialCoordinates();

        /**
         * Method returns if there is next coordinate
         *
         * @return true if there is next coordinate, false otherwise
         */
        boolean hasNext() {
            return hasNextCoordinates(currentCoords);
        }

        /**
         * Method returns next coordinate
         *
         * @return {@link automaton.coordinates.CellCoordinates}
         */
        CellCoordinates next() {
            currentCoords = nextCoordinates(currentCoords);
            return currentCoords;
        }

        void setState(CellState newState) {
            cells.put(currentCoords, newState);
        }
    }
}
