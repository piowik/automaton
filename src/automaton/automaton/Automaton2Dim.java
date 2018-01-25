package automaton.automaton;

import automaton.coordinates.CellCoordinates;
import automaton.coordinates.Coords2D;
import automaton.factory.CellStateFactory;
import automaton.neighborhood.CellNeighborhood;

public abstract class Automaton2Dim extends Automaton {
    private int width;
    private int height;

    public Automaton2Dim(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory, int width, int height) {
        super(neighborsStrategy, stateFactory);
        this.width = width;
        this.height = height;
        super.initializeMap(); // cannot initialize before setting map dimensions
    }

    protected boolean hasNextCoordinates(CellCoordinates cellCoordinates) {
        Coords2D coords = (Coords2D) cellCoordinates;
        return (!(coords.x == width - 1 && coords.y == height - 1));
    }

    protected CellCoordinates initialCoordinates() {
        return new Coords2D(-1, 0);
    }

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
