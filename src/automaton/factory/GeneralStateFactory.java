package automaton.factory;

import automaton.coordinates.CellCoordinates;
import automaton.state.CellState;

import java.util.Map;

/**
 * Class used for initiating automaton cell's with custom map
 */
public class GeneralStateFactory implements CellStateFactory {
    private final Map<CellCoordinates, CellState> states;

    /**
     * Constructor for GeneralStateFactory class.
     * @param states map of cell states with cooridnates
     */
    public GeneralStateFactory(Map<CellCoordinates, CellState> states) {
        this.states = states;
    }

    /**
     * Method returning cell's state based on cell coordinates
     * @param coords cell's coordinates
     * @return {@link automaton.cell.Cell}
     */
    public CellState initialState(CellCoordinates coords) {
        return states.get(coords);
    }
}
