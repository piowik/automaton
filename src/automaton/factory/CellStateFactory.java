package automaton.factory;

import automaton.coordinates.CellCoordinates;
import automaton.state.CellState;

/**
 * Interface for classes that generate initial Automaton map
 */

public interface CellStateFactory {

    CellState initialState(CellCoordinates a);
}
