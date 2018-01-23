package automaton;

import java.util.Map;

public class GeneralStateFactory implements CellStateFactory{
    private Map<CellCoordinates,CellState> states;

    public CellState initialState(CellCoordinates coords){
        return states.get(coords);
    }
}
