package automaton;

import java.util.Set;

import static automaton.BinaryState.ALIVE;
import static automaton.BinaryState.DEAD;

public class GameOfLife extends Automaton2Dim {
    private int width;
    private int height;

    public GameOfLife(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory, int width, int height) {
        super(neighborsStrategy, stateFactory, width, height);
        this.width = width;
        this.height = height;
    }

    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        GameOfLife newInstance = new GameOfLife(cellNeighborhood, cellStateFactory, width, height);
        return newInstance;
    }

    protected CellState nextCellState(Cell targetCell, Set<Cell> neighborsStates) {
        CellState currentState = targetCell.state;
        int neighborsAlive = 0;
        for (Cell c : neighborsStates) {
            if (c.state == ALIVE)
                neighborsAlive++;
        }
        if (currentState == DEAD && neighborsAlive == 3)
            return ALIVE;
        else if (currentState == ALIVE && (neighborsAlive == 2 || neighborsAlive == 3))
            return ALIVE;
        else
            return DEAD;
    }
}
