package automaton.automaton;

import automaton.coordinates.CellCoordinates;
import automaton.coordinates.Coords2D;
import automaton.factory.CellStateFactory;
import automaton.neighborhood.CellNeighborhood;

/**
 * Automaton1Dim class that implements automaton. Used to implement two dimensional games.
 */

public abstract class Automaton2Dim extends Automaton {
    private final int width;
    private final int height;

    /**
     * Constructor for Automaton2Dim class.
     * @param neighborsStrategy strategy used to find neighbors
     * @param stateFactory State Factory used in generating map
     * @param width automaton width
     * @param height automaton heighth
     */
    Automaton2Dim(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory, int width, int height) {
        super(neighborsStrategy, stateFactory);
        this.width = width;
        this.height = height;
        super.initializeMap(); // cannot initialize before setting map dimensions
    }


    /**
     * Method checking if next coordinates exist based on current coordinates
     * @param cellCoordinates current cooridnates
     * @return boolean
     */
    protected boolean hasNextCoordinates(CellCoordinates cellCoordinates) {
        Coords2D coords = (Coords2D) cellCoordinates;
        return (!(coords.x == width - 1 && coords.y == height - 1));
    }


    /**
     * Method returning first coordinates.
     * @return {@link automaton.coordinates.Coords2D}
     */
    protected CellCoordinates initialCoordinates() {
        return new Coords2D(-1, 0);
    }

    /**
     * Method returning next coordinates based on current coordinates.
     * @param cellCoordinates current cooridnates
     * @return {@link automaton.coordinates.Coords2D}
     */
    protected CellCoordinates nextCoordinates(CellCoordinates cellCoordinates) {
        Coords2D coords = (Coords2D) cellCoordinates;
        int newX = coords.x;
        int newY = coords.y;
        if (coords.x == (width - 1)) {
            newX = 0;
            newY += 1;
        } else
            newX += 1;
        return new Coords2D(newX, newY);
    }
}
