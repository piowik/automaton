package automaton.cell;

import automaton.coordinates.CellCoordinates;
import automaton.state.CellState;

public class Cell {
    public final CellState state;
    public final CellCoordinates coords;

    public Cell(CellState state, CellCoordinates coords) {
        this.state = state;
        this.coords = coords;
    }
}
