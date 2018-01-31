package automaton.factory;

import automaton.coordinates.CellCoordinates;
import automaton.state.CellState;

import java.util.Map;

public class GeneralStateFactory implements CellStateFactory {
    private final Map<CellCoordinates, CellState> states;

    public GeneralStateFactory(Map<CellCoordinates, CellState> states) {
        this.states = states;
    }

    public CellState initialState(CellCoordinates coords) {
        return states.get(coords);
    }
}
