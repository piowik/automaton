package automaton;

public interface CellStateFactory {

    default public CellState initialState(CellCoordinates a) {
        return null;
    }
}
