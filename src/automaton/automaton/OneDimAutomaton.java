package automaton.automaton;

import automaton.cell.Cell;
import automaton.coordinates.Coords1D;
import automaton.factory.CellStateFactory;
import automaton.neighborhood.CellNeighborhood;
import automaton.state.CellState;

import java.util.Set;

import static automaton.state.BinaryState.ALIVE;
import static automaton.state.BinaryState.DEAD;

public class OneDimAutomaton extends Automaton1Dim {
    private int size;
    private int[] binaryRule = new int[8];
    private int rule;

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

    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        return new OneDimAutomaton(cellNeighborhood, cellStateFactory, size, rule);
    }

    protected CellState nextCellState(Cell targetCell, Set<Cell> neighborsStates) {
        int neighborsValue = 0;
        Coords1D myCoords = (Coords1D) targetCell.coords;
        for (Cell c : neighborsStates) {
            Coords1D neighborCoords = (Coords1D) c.coords;
            if (neighborCoords.x - 1 == myCoords.x && c.state == ALIVE)
                neighborsValue += 1;
            else if (neighborCoords.x == myCoords.x && c.state == ALIVE)
                neighborsValue += 2;
            else if (neighborCoords.x + 1 == myCoords.x && c.state == ALIVE)
                neighborsValue += 4;
        }
        int myValue = binaryRule[7-neighborsValue];
        if (myValue == 1)
            return ALIVE;
        return DEAD;
    }
}
