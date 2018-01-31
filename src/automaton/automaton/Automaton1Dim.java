package automaton.automaton;

import automaton.coordinates.CellCoordinates;
import automaton.coordinates.Coords1D;
import automaton.factory.CellStateFactory;
import automaton.neighborhood.CellNeighborhood;

public abstract class Automaton1Dim extends Automaton {
    private final int size;

    Automaton1Dim(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory, int size) {
        super(neighborsStrategy, stateFactory);
        this.size = size;
        super.initializeMap();
    }

    protected boolean hasNextCoordinates(CellCoordinates cellCoordinates) {
        Coords1D coords = (Coords1D) cellCoordinates;
        return (!(coords.x == size - 1));
    }

    protected CellCoordinates initialCoordinates() {
        return new Coords1D(-1);
    }

    protected CellCoordinates nextCoordinates(CellCoordinates cellCoordinates) {
        Coords1D coords = (Coords1D) cellCoordinates;
        int newX = coords.x;
        newX += 1;
        return new Coords1D(newX);
    }
}

