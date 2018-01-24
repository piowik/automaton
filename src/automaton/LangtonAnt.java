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

    protected CellState nextCellState(Cell targetCell, Set<Cell> neighborsStates) {
        CellState currentState = targetCell.state;
        if (currentState instanceof LangtonCell) {
            LangtonCell antCell = (LangtonCell) currentState;
            AntState antState = antCell.antState;
            AntState newState = AntState.NONE;
            if (antCell.cellState == DEAD) {// white
                if (antState == AntState.NORTH)
                    newState = AntState.EAST;
                else if (antState == AntState.EAST)
                    newState = AntState.SOUTH;
                else if (antState == AntState.SOUTH)
                    newState = AntState.WEST;
                else if (antState == AntState.WEST)
                    newState = AntState.NORTH;
                antCell.antState = newState;
                return ALIVE;
            }
            else{// black
                if (antState == AntState.NORTH)
                    newState = AntState.WEST;
                else if (antState == AntState.EAST)
                    newState = AntState.NORTH;
                else if (antState == AntState.SOUTH)
                    newState = AntState.EAST;
                else if (antState == AntState.WEST)
                    newState = AntState.SOUTH;
                antCell.antState = newState;
                return DEAD;
            }
        }
        else
            return currentState;
    }
}
