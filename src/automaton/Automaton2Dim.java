package automaton;

public abstract class Automaton2Dim extends Automaton {
    private int width;
    private int height;

    public Automaton2Dim(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory) {
        super(neighborsStrategy, stateFactory);
        //TODO: set width and height
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
        if (coords.x == (width - 1)) {
            coords.x = 0;
            coords.y += 1;
        } else
            coords.x += 1;
        return new Coords2D(coords.x,coords.y);
    }
}
