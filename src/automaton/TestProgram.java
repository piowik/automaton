package automaton;

import java.util.HashMap;
import java.util.Map;

import static automaton.BinaryState.ALIVE;
import static automaton.BinaryState.DEAD;
import static automaton.WireElectronState.ELECTRON_HEAD;
import static automaton.WireElectronState.WIRE;

public class TestProgram {
    public static void main(String[] argv) {
        int width = 3;
        int height = 3;
        UniformStateFactory uniformStateFactory = new UniformStateFactory(WIRE);
        MoorNeighborhood moorNeighborhood = new MoorNeighborhood(width, height, false, 1);
        WireWorld newGame = new WireWorld(moorNeighborhood, uniformStateFactory, width, height);
        Map<CellCoordinates, CellState> cellsMap = newGame.getCells();
        System.out.println("Reading returned map (Size " + cellsMap.size() + ")");
        System.out.println("Iterating entrySet");
        printMap(cellsMap);
        CellCoordinates testItem = new Coords2D(1,1);
        CellState testItemState = ELECTRON_HEAD;
        Map<CellCoordinates,CellState> testItemMap = new HashMap<>();
        testItemMap.put(testItem,testItemState);
        newGame.insertStructure(testItemMap);
        cellsMap = newGame.getCells();
        System.out.println("Printing after inserting structure");
        printMap(cellsMap);
        newGame = (WireWorld) newGame.nextState();
        cellsMap = newGame.getCells();
        System.out.println("Printing after nextState");
        printMap(cellsMap);

    }

    private static void printMap(Map<CellCoordinates, CellState> cellsMap) {
        for (Map.Entry<CellCoordinates, CellState> entry : cellsMap.entrySet()) {
            Coords2D coordinates = (Coords2D) entry.getKey();
            CellState state = entry.getValue();
            System.out.println("[" + coordinates.x + ";" + coordinates.y + "]" + " " + state);
        }
    }
}
