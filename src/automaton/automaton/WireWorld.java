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

/**
 * Wireworld class that implements Automaton2Dim. Contains wireworld game rules.
 */
public class WireWorld extends Automaton2Dim {
    private final int width;
    private final int height;


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
                convertedMap.put(entry.getKey(), VOID);
            else if (entry.getValue() == 1)
                convertedMap.put(entry.getKey(), WIRE);
            else if (entry.getValue() == 2)
                convertedMap.put(entry.getKey(), ELECTRON_TAIL);
            else if (entry.getValue() == 3)
                convertedMap.put(entry.getKey(), ELECTRON_HEAD);
        }

        return convertedMap;
    }

    /**
     * Constructor for Wireworld class.
     *
     * @param neighborsStrategy strategy used to find neighbors
     * @param stateFactory      State Factory used in generating map
     * @param width             automaton width
     * @param height            automaton heighth
     */
    public WireWorld(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory, int width, int height) {
        super(neighborsStrategy, stateFactory, width, height);
        this.width = width;
        this.height = height;
    }


    /**
     * Method returning new instance of automaton class based on the current class.
     *
     * @param cellStateFactory state factory used to generate map
     * @param cellNeighborhood neighborhood strategy used in the game
     * @return {@link automaton.state.WireElectronState}
     */
    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        return new WireWorld(cellNeighborhood, cellStateFactory, width, height);
    }


    /**
     * Method returning next cell's state based on it's neighbors' states
     *
     * @param targetCell     cell's coordinates
     * @param neighborsCells cell's neighbors
     * @return {@link automaton.state.WireElectronState}
     */

    protected CellState nextCellState(Cell targetCell, Set<Cell> neighborsCells) {
        CellState currentState = targetCell.state;
        if (currentState == ELECTRON_HEAD)
            return ELECTRON_TAIL;
        else if (currentState == ELECTRON_TAIL)
            return WIRE;
        else if (currentState == WIRE) {
            int headsCounter = 0;
            for (Cell c : neighborsCells) {
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
