package automaton;

public class UnifromStateFactory implements CellStateFactory {
    private CellState state;

    public CellState initialState(CellCoordinates coords){
        return state;
    }

}
