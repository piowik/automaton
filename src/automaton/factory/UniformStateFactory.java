package automaton.factory;

import automaton.coordinates.CellCoordinates;
import automaton.state.CellState;

/**
 * Class used for initiating automaton cell's with one state
 */
public class UniformStateFactory implements CellStateFactory {
    private final CellState state;

    /**
     * Constructor for GeneralStateFactory class.
     *
     * @param state cell state
     */
    public UniformStateFactory(CellState state) {
        this.state = state;
    }

    /**
     * Method returning cell's state based on cell coordinates
     *
     * @param coords cell's coordinates
     * @return {@link automaton.cell.Cell}
     */
    public CellState initialState(CellCoordinates coords) {
        return state;
    }

}
