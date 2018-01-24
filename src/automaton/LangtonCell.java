package automaton;

public class LangtonCell implements CellState {
    LangtonCell(AntState antState, int antId, BinaryState cellState)
    {
        this.antState=antState;
        this.antId=antId;
        this.cellState=cellState;
    }

    public AntState antState;
    public int antId;
    public BinaryState cellState;
}
