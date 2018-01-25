package automaton.factory;

import automaton.coordinates.CellCoordinates;
import automaton.state.CellState;

import java.util.Map;

public class GeneralStateFactory implements CellStateFactory {
    private Map<CellCoordinates, CellState> states;

    GeneralStateFactory(Map<CellCoordinates, CellState> states) {
        this.states = states;
    }

    public CellState initialState(CellCoordinates coords) {
        return states.get(coords);
    }
}
