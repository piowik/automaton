package automaton;

public class UniformStateFactory implements CellStateFactory {
    private CellState state;

    UniformStateFactory(CellState state) {
        this.state = state;
    }

    public CellState initialState(CellCoordinates coords){
        return state;
    }

}
