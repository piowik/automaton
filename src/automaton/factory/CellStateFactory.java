package automaton.factory;

import automaton.coordinates.CellCoordinates;
import automaton.state.CellState;

public interface CellStateFactory {

    CellState initialState(CellCoordinates a);
}
