package automaton;

import java.util.Set;

import static automaton.BinaryState.ALIVE;
import static automaton.BinaryState.DEAD;

public class LangtonAnt extends Automaton2Dim{
    public LangtonAnt(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory) {
        super(neighborsStrategy, stateFactory);
    }

    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        LangtonAnt newInstance = new LangtonAnt(cellNeighborhood, cellStateFactory);
        return newInstance;
    }

    protected CellState nextCellState(CellState currentState, Set<Cell> neighborsStates) {
        if (currentState == DEAD)
            return ALIVE;
        else
            return DEAD;
    }
}
