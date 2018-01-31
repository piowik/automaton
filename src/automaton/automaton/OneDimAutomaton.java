package automaton.automaton;

import automaton.cell.Cell;
import automaton.coordinates.Coords1D;
import automaton.factory.CellStateFactory;
import automaton.neighborhood.CellNeighborhood;
import automaton.state.CellState;

import java.util.Set;

import static automaton.state.BinaryState.ALIVE;
import static automaton.state.BinaryState.DEAD;

/**
 * OneDimAutomaton class that implements Automaton1Dim. Contains one dimensional automaton game rules.
 */
public class OneDimAutomaton extends Automaton1Dim {
    private final int size;
    private final int[] binaryRule = new int[8];
    private final int rule;

    /**
     * Constructor for OneDimAutomaton class.
     *
     * @param neighborsStrategy strategy used to find neighbors
     * @param stateFactory      State Factory used in generating map
     * @param size              automaton size
     * @param rule              one dimensional automton rules
     */
    public OneDimAutomaton(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory, int size, int rule) {
        super(neighborsStrategy, stateFactory, size);
        this.size = size;
        this.rule = rule;
        int i = 7;
        while (rule != 0) {
            binaryRule[i] = rule % 2;
            rule = rule / 2;
            i--;
        }
    }

    /**
     * Method returning new instance of automaton class based on the current class.
     *
     * @param cellStateFactory state factory used to generate map
     * @param cellNeighborhood neighborhood strategy used in the game
     * @return {@link automaton.automaton.Automaton}
     */
    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        return new OneDimAutomaton(cellNeighborhood, cellStateFactory, size, rule);
    }

    /**
     * Method returning next cell's state based on it's neighbors' states
     *
     * @param targetCell     cell's coordinates
     * @param neighborsCells cell's neighbors
     * @return {@link automaton.state.BinaryState}
     */
    protected CellState nextCellState(Cell targetCell, Set<Cell> neighborsCells) {
        int neighborsValue = 0;
        Coords1D myCoords = (Coords1D) targetCell.coords;
        for (Cell c : neighborsCells) {
            Coords1D neighborCoords = (Coords1D) c.coords;
            if (neighborCoords.x - 1 == myCoords.x && c.state == ALIVE)
                neighborsValue += 1;
            else if (neighborCoords.x == myCoords.x && c.state == ALIVE)
                neighborsValue += 2;
            else if (neighborCoords.x + 1 == myCoords.x && c.state == ALIVE)
                neighborsValue += 4;
        }
        int myValue = binaryRule[7 - neighborsValue];
        if (myValue == 1)
            return ALIVE;
        return DEAD;
    }
}
