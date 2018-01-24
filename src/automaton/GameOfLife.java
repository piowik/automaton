package automaton;

import java.util.Map;
import java.util.Set;

import static automaton.BinaryState.ALIVE;
import static automaton.BinaryState.DEAD;

public class GameOfLife extends Automaton2Dim {
    public GameOfLife(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory) {
        super(neighborsStrategy, stateFactory);
    }

    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        GameOfLife newInstance = new GameOfLife(cellNeighborhood,cellStateFactory);
        return newInstance;
    }
    protected CellState nextCellState(Cell targetCell, Set<Cell> neighborsStates) {
        CellState currentState = targetCell.state;
        int neighborsAlive =0;
        for(Cell c : neighborsStates)
        {
            if(c.state==ALIVE)
                neighborsAlive++;
        }
        if(currentState==DEAD&&neighborsAlive==3)
            return ALIVE;
        else if(currentState==ALIVE&&(neighborsAlive==2||neighborsAlive==3))
            return ALIVE;
        else
            return DEAD;
    }
}
