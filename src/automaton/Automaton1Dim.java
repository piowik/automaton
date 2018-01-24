package automaton;

public abstract class Automaton1Dim extends Automaton {
    private int size;

    public Automaton1Dim(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory) {
        super(neighborsStrategy, stateFactory);
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

