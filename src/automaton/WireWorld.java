package automaton;

import java.util.Set;

import static automaton.WireElectronState.*;

public class WireWorld extends Automaton2Dim {
    public WireWorld(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory) {
        super(neighborsStrategy, stateFactory);
    }

    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        WireWorld newInstance = new WireWorld(cellNeighborhood,cellStateFactory);
        return newInstance;
    }

    protected CellState nextCellState(Cell targetCell, Set<Cell> neighborsStates) {
        CellState currentState = targetCell.state;
        if (currentState == ELECTRON_HEAD)
            return ELECTRON_TAIL;
        else if (currentState == ELECTRON_TAIL)
            return WIRE;
        else if (currentState == WIRE) {
            int headsCounter = 0;
            for (Cell c : neighborsStates) {
                if (c.state == ELECTRON_HEAD)
                    headsCounter++;
            }
            if (headsCounter == 1 || headsCounter == 2)
                return ELECTRON_HEAD;
            else
                return WIRE;
        }
        else
            return VOID;
    }
}
