package automaton.cell;

import automaton.state.AntState;
import automaton.state.BinaryState;
import automaton.state.CellState;

public class LangtonCell implements CellState {
    public LangtonCell(AntState antState, int antId, BinaryState cellState) {
        this.antState = antState;
        this.antId = antId;
        this.cellState = cellState;
    }

    public AntState antState;
    public int antId;
    public BinaryState cellState;
}