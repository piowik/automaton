package automaton.automaton;

import automaton.cell.Cell;
import automaton.coordinates.CellCoordinates;
import automaton.factory.CellStateFactory;
import automaton.neighborhood.CellNeighborhood;
import automaton.state.CellState;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static automaton.state.BinaryState.ALIVE;
import static automaton.state.BinaryState.DEAD;

public class GameOfLife extends Automaton2Dim {
    private final int width;
    private final int height;
    private final int[] neighborsToNotDie;
    private final int[] neighborsToStartLiving;

    private static boolean contains(int[] arr, int item) {
        for (int n : arr) {
            if (item == n) {
                return true;
            }
        }
        return false;
    }

    public static Map<CellCoordinates, CellState> convert(Map<CellCoordinates, Integer> mapFromFile)
    {
        Map<CellCoordinates,CellState> convertedMap = new HashMap<>();
        for (Map.Entry<CellCoordinates, Integer> entry : mapFromFile.entrySet()) {
            if(entry.getValue()==0)
                convertedMap.put(entry.getKey(), DEAD);
            else
                convertedMap.put(entry.getKey(), ALIVE);
        }

        return convertedMap;
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
