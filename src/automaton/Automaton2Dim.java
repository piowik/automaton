package automaton;

public abstract class Automaton2Dim extends Automaton {
    private int width;
    private int height;

    protected boolean hasNextCoordinates(Coords2D cellCoordinates) {
        return (!(cellCoordinates.x == width-1 && cellCoordinates.y == height-1));
    }

    protected CellCoordinates initialCoordinates(Coords2D cellCoordinates) {
        // TODO: Automaton2Dim: initialCoordinates
    }

    protected CellCoordinates nextCoordinates(Coords2D cellCoordinates) {
        if (cellCoordinates.x == width-1) {
            cellCoordinates.x = 0;
            cellCoordinates.y += 1;
        }
        else
            cellCoordinates.x += 1;
        return cellCoordinates;
    }
}
