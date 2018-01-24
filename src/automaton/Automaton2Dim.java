package automaton;

import java.util.Map;

public abstract class Automaton2Dim extends Automaton {
    private int width;
    private int height;

    public Automaton2Dim(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory) {
        super(neighborsStrategy, stateFactory);
    }

    protected boolean hasNextCoordinates(CellCoordinates cellCoordinates) {
        Coords2D coords = (Coords2D)cellCoordinates;
        return (!(coords.x == width-1 && coords.y == height-1));
    }

    protected CellCoordinates initialCoordinates(CellCoordinates cellCoordinates) {
        Coords2D coords = (Coords2D)cellCoordinates;
        return coords;
    }

    protected CellCoordinates nextCoordinates(CellCoordinates cellCoordinates) {
        Coords2D coords = (Coords2D)cellCoordinates;
        if (coords.x == width-1) {
            coords.x = 0;
            coords.y += 1;
        }
        else
            coords.x += 1;
        return coords;
    }
}
