package automaton.automaton;

import automaton.cell.Cell;
import automaton.coordinates.CellCoordinates;
import automaton.factory.CellStateFactory;
import automaton.neighborhood.CellNeighborhood;
import automaton.state.CellState;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static automaton.state.WireElectronState.*;

public class WireWorld extends Automaton2Dim {
    private int width;
    private int height;



    public static Map<CellCoordinates, CellState> convert(Map<CellCoordinates, Integer> mapFromFile)
    {
        Map<CellCoordinates,CellState> convertedMap = new HashMap<>();
        for (Map.Entry<CellCoordinates, Integer> entry : mapFromFile.entrySet()) {
            if(entry.getValue()==0)
                convertedMap.put(entry.getKey(), VOID);
            else
                if(entry.getValue()==1)
                convertedMap.put(entry.getKey(), WIRE);
            else
                if(entry.getValue()==2)
                    convertedMap.put(entry.getKey(), ELECTRON_TAIL);
                else
                if(entry.getValue()==3)
                    convertedMap.put(entry.getKey(), ELECTRON_HEAD);
        }

        return convertedMap;
    }

    public WireWorld(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory, int width, int height) {
        super(neighborsStrategy, stateFactory, width, height);
        this.width = width;
        this.height = height;
    }

    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        return new WireWorld(cellNeighborhood, cellStateFactory, width, height);
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
        } else
            return VOID;
    }
}
