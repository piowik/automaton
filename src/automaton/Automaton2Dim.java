package automaton;

public abstract class Automaton2Dim extends Automaton {
    private int width;
    private int height;

    protected boolean hasNextCoordinates(CellCoordinates cellCoordinates) {
        Coords2D coords = (Coords2D)cellCoordinates;
        return (!(coords.x == width-1 && coords.y == height-1));
    }

    protected CellCoordinates initialCoordinates(CellCoordinates cellCoordinates) {
        // TODO: Automaton2Dim: initialCoordinates
    }

    protected CellCoordinates nextCoordinates(CellCoordinates cellCoordinates) {
        // TODO: Automaton2Dim: nextCoordinates
    }
}
