package automaton;

import java.util.HashMap;
import java.util.Map;

import static automaton.BinaryState.ALIVE;
import static automaton.BinaryState.DEAD;

public class TestWireWorldProgram {
    static int width = 3;
    static int height = 3;
    public static void main(String[] argv) {

        UniformStateFactory uniformStateFactory = new UniformStateFactory(WireElectronState.VOID);
        MoorNeighborhood moorNeighborhood = new MoorNeighborhood(width, height, false, 1);
        WireWorld newGame = new WireWorld(moorNeighborhood, uniformStateFactory, width, height);
        Map<CellCoordinates, CellState> cellsMap = newGame.getCells();
        System.out.println("Reading returned map (Size " + cellsMap.size() + ")");
        System.out.println("Iterating entrySet");
        drawMap(cellsMap);

        Map<CellCoordinates,CellState> testStructure = new HashMap<>();

        // blinker (oscylator)
        // https://upload.wikimedia.org/wikipedia/commons/c/c2/2-3_O1.gif
        CellCoordinates testItem = new Coords2D(1,1);
        CellState testItemState = WireElectronState.WIRE;
        testStructure.put(testItem,testItemState);

        CellCoordinates testItem2 = new Coords2D(1,0);
        CellState testItemState2 = WireElectronState.WIRE;
        testStructure.put(testItem2,testItemState2);

        CellCoordinates testItem3 = new Coords2D(1,2);
        CellState testItemState3 = WireElectronState.ELECTRON_HEAD;
        testStructure.put(testItem3,testItemState3);

        newGame.insertStructure(testStructure);
        cellsMap = newGame.getCells();
        System.out.println("Printing after inserting structure");
        drawMap(cellsMap);

        for(int i = 0; i < 4; i++) {
            newGame = (WireWorld) newGame.nextState();
            cellsMap = newGame.getCells();
            System.out.println("Printing after iteration " + i);
            drawMap(cellsMap);
        }

    }

    private static void printMap(Map<CellCoordinates, CellState> cellsMap) {
        for (Map.Entry<CellCoordinates, CellState> entry : cellsMap.entrySet()) {
            Coords2D coordinates = (Coords2D) entry.getKey();
            CellState state = entry.getValue();
            System.out.println("[" + coordinates.x + ";" + coordinates.y + "]" + " " + state);
        }
    }

    private static void drawMap(Map<CellCoordinates, CellState> cellsMap) {
        for (int i = 0; i < width; i++) {
            System.out.print("[");
            for (int j = 0; j < width; j++) {
                String p = cellsMap.get(new Coords2D(i,j)).toString().substring(0,1);
                System.out.print(" " + p + " ");
            }
            System.out.println("]");
        }
    }
}
