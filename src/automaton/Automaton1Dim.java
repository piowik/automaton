package automaton;

public abstract class Automaton1Dim extends Automaton {
    private int size;

    public Automaton1Dim(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory) {
        super(neighborsStrategy, stateFactory);
    }

    protected boolean hasNextCoordinates(CellCoordinates cellCoordinates) {
        Coords1D coords = (Coords1D) cellCoordinates;
        return (!(coords.x == size - 1));
    }

    protected CellCoordinates initialCoordinates(CellCoordinates cellCoordinates) {
        Coords1D coords = (Coords1D) cellCoordinates;
        return coords;
    }

    protected CellCoordinates nextCoordinates(CellCoordinates cellCoordinates) {
        Coords1D coords = (Coords1D) cellCoordinates;
        coords.x += 1;
        return coords;
    }
}

