package automaton.factory;

import automaton.coordinates.CellCoordinates;
import automaton.state.CellState;

public class UniformStateFactory implements CellStateFactory {
    private final CellState state;

    public UniformStateFactory(CellState state) {
        this.state = state;
    }

    public CellState initialState(CellCoordinates coords) {
        return state;
    }

}
