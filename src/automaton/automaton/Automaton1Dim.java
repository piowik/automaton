package automaton.automaton;

import automaton.coordinates.CellCoordinates;
import automaton.coordinates.Coords1D;
import automaton.factory.CellStateFactory;
import automaton.neighborhood.CellNeighborhood;

/**
 * Automaton1Dim class that implements automaton. Used to implement one dimensional games.
 */

public abstract class Automaton1Dim extends Automaton {
    private final int size;

    /**
     * Constructor for Automaton1Dim class.
     * @param neighborsStrategy strategy used to find neighbors
     * @param stateFactory State Factory used in generating map
     * @param size automaton size
     */
    Automaton1Dim(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory, int size) {
        super(neighborsStrategy, stateFactory);
        this.size = size;
        super.initializeMap();
    }

    /**
     * Method checking if next coordinates exist based on current coordinates
     * @param cellCoordinates current coordinates
     * @return boolean
     */

    protected boolean hasNextCoordinates(CellCoordinates cellCoordinates) {
        Coords1D coords = (Coords1D) cellCoordinates;
        return (!(coords.x == size - 1));
    }
    /**
     * Method returning first coordinates.
     * @return {@link automaton.coordinates.Coords1D}
     */

    protected CellCoordinates initialCoordinates() {
        return new Coords1D(-1);
    }
    /**
     * Method returning next coordinates based on current coordinates.
     * @param cellCoordinates current cooridnates
     * @return {@link automaton.coordinates.Coords1D}
     */
    protected CellCoordinates nextCoordinates(CellCoordinates cellCoordinates) {
        Coords1D coords = (Coords1D) cellCoordinates;
        int newX = coords.x;
        newX += 1;
        return new Coords1D(newX);
    }
}

