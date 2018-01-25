package automaton.automaton;

import automaton.cell.Cell;
import automaton.factory.CellStateFactory;
import automaton.neighborhood.CellNeighborhood;
import automaton.state.CellState;

import java.util.Set;
import java.util.stream.IntStream;

import static automaton.state.BinaryState.ALIVE;
import static automaton.state.BinaryState.DEAD;

public class GameOfLife extends Automaton2Dim {
    private int width;
    private int height;
    private int[] neighborsToNotDie;
    private int[] neighborsToStartLiving;

    public static boolean contains(int[] arr, int item) {
        for (int n : arr) {
            if (item == n) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[] myArray = { 5, 2, 17, 13, 12, 19, 7, 3, 9, 15 };
        System.out.println(contains(myArray, 13));
        System.out.println(contains(myArray, 25));
    }

    public GameOfLife(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory, int width, int height, int[] neighborsToNotDie, int[] neighborsToStartLiving) {
        super(neighborsStrategy, stateFactory, width, height);
        this.width = width;
        this.height = height;
        this.neighborsToNotDie=neighborsToNotDie;
        this.neighborsToStartLiving=neighborsToStartLiving;
    }

    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        return new GameOfLife(cellNeighborhood, cellStateFactory, width, height, neighborsToNotDie, neighborsToStartLiving);
    }

    protected CellState nextCellState(Cell targetCell, Set<Cell> neighborsStates) {
        CellState currentState = targetCell.state;
        int neighborsAlive = 0;
        for (Cell c : neighborsStates) {
            if (c.state == ALIVE)
                neighborsAlive++;
        }
        if (currentState == DEAD &&  contains(neighborsToStartLiving,neighborsAlive) )
            return ALIVE;
        else if (currentState == ALIVE && contains(neighborsToNotDie,neighborsAlive))
            return ALIVE;
        else
            return DEAD;
    }
}
