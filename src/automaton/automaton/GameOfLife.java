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

/**
 * GameOfLife class that implements Automaton2Dim. Contains wireworld game rules.
 */
public class GameOfLife extends Automaton2Dim {
    private final int width;
    private final int height;
    private final int[] neighborsToNotDie;
    private final int[] neighborsToStartLiving;

    /**
     * Helping method to find int in array
     *
     * @param arr  int array
     * @param item int that is being searched for
     * @return boolean
     */
    private static boolean contains(int[] arr, int item) {
        for (int n : arr) {
            if (item == n) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helping method that converts integers in map from file to cell states
     *
     * @param mapFromFile int array
     * @return map with key of {@link automaton.coordinates.Coords2D} and value {@link automaton.state.BinaryState}
     */

    public static Map<CellCoordinates, CellState> convert(Map<CellCoordinates, Integer> mapFromFile) {
        Map<CellCoordinates, CellState> convertedMap = new HashMap<>();
        for (Map.Entry<CellCoordinates, Integer> entry : mapFromFile.entrySet()) {
            if (entry.getValue() == 0)
                convertedMap.put(entry.getKey(), DEAD);
            else
                convertedMap.put(entry.getKey(), ALIVE);
        }

        return convertedMap;
    }

    /**
     * Constructor for GameOfLife class.
     *
     * @param neighborsStrategy      strategy used to find neighbors
     * @param stateFactory           State Factory used in generating map
     * @param width                  automaton width
     * @param height                 automaton heighth
     * @param neighborsToNotDie      integer array with rule when cells shouldn't die
     * @param neighborsToStartLiving integer array with rule when cells should start living
     */
    public GameOfLife(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory, int width, int height, int[] neighborsToNotDie, int[] neighborsToStartLiving) {
        super(neighborsStrategy, stateFactory, width, height);
        this.width = width;
        this.height = height;
        this.neighborsToNotDie = neighborsToNotDie;
        this.neighborsToStartLiving = neighborsToStartLiving;
    }

    /**
     * Method returning new instance of automaton class based on the current class.
     *
     * @param cellStateFactory state factory used to generate map
     * @param cellNeighborhood neighborhood strategy used in the game
     * @return {@link automaton.automaton.Automaton}
     */
    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        return new GameOfLife(cellNeighborhood, cellStateFactory, width, height, neighborsToNotDie, neighborsToStartLiving);
    }

    /**
     * Method returning next cell's state based on it's neighbors' states
     *
     * @param targetCell     cell's coordinates
     * @param neighborsCells cell's neighbors
     * @return {@link automaton.state.BinaryState}
     */
    protected CellState nextCellState(Cell targetCell, Set<Cell> neighborsCells) {
        CellState currentState = targetCell.state;
        int neighborsAlive = 0;
        for (Cell c : neighborsCells) {
            if (c.state == ALIVE)
                neighborsAlive++;
        }
        if (currentState == DEAD && contains(neighborsToStartLiving, neighborsAlive))
            return ALIVE;
        else if (currentState == ALIVE && contains(neighborsToNotDie, neighborsAlive))
            return ALIVE;
        else
            return DEAD;
    }
}
