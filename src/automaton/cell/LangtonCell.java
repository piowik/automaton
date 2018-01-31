package automaton.cell;

import automaton.state.AntState;
import automaton.state.BinaryState;
import automaton.state.CellState;
/**
 * CellState class that implements langton ant
 */
public class LangtonCell implements CellState {

    /**
     * Constructor for Coords2D class.
     * @param antState direction that ant is facing
     * @param antId ant id (used for multiple ants)
     * @param cellState state of cell under the ant
     */
    public LangtonCell(AntState antState, int antId, BinaryState cellState) {
        this.antState = antState;
        this.antId = antId;
        this.cellState = cellState;
    }

    public final AntState antState;
    public final int antId;
    public final BinaryState cellState;
}
