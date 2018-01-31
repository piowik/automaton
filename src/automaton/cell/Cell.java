package automaton.cell;

import automaton.coordinates.CellCoordinates;
import automaton.state.CellState;

/**
 * CellState class that implements langton ant
 */
public class Cell {
    public final CellState state;
    public final CellCoordinates coords;

    /**
     * Constructor for Coords2D class.
     * @param state state of the cell
     * @param coords coordinates of the cell
     */
    public Cell(CellState state, CellCoordinates coords) {
        this.state = state;
        this.coords = coords;
    }
}
