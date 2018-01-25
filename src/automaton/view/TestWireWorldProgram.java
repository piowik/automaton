package automaton.view;

import automaton.automaton.WireWorld;
import automaton.coordinates.CellCoordinates;
import automaton.coordinates.Coords2D;
import automaton.factory.UniformStateFactory;
import automaton.neighborhood.MoorNeighborhood;
import automaton.state.CellState;
import automaton.state.WireElectronState;

import java.util.HashMap;
import java.util.Map;

public class TestWireWorldProgram {
    static int width = 4;
    static int height = 4;

    public static void main(String[] argv) {

        UniformStateFactory uniformStateFactory = new UniformStateFactory(WireElectronState.VOID);
        MoorNeighborhood moorNeighborhood = new MoorNeighborhood(width, height, false, 1);
        WireWorld newGame = new WireWorld(moorNeighborhood, uniformStateFactory, width, height);
        Map<CellCoordinates, CellState> cellsMap = newGame.getCells();
        System.out.println("Reading returned map (Size " + cellsMap.size() + ")");
        System.out.println("Iterating entrySet");
        drawMap(cellsMap);

        Map<CellCoordinates, CellState> testStructure = new HashMap<>();


        CellCoordinates testItem = new Coords2D(1, 1);
        CellState testItemState = WireElectronState.WIRE;
        testStructure.put(testItem, testItemState);

        CellCoordinates testItem2 = new Coords2D(1, 0);
        CellState testItemState2 = WireElectronState.ELECTRON_HEAD;
        testStructure.put(testItem2, testItemState2);

        CellCoordinates testItem3 = new Coords2D(1, 2);
        CellState testItemState3 = WireElectronState.WIRE;
        testStructure.put(testItem3, testItemState3);

        CellCoordinates testItem4 = new Coords2D(2, 2);
        CellState testItemState4 = WireElectronState.WIRE;
        testStructure.put(testItem4, testItemState4);


        CellCoordinates testItem5 = new Coords2D(3, 3);
        CellState testItemState5 = WireElectronState.WIRE;
        testStructure.put(testItem5, testItemState5);


        CellCoordinates testItem6 = new Coords2D(0, 3);
        CellState testItemState6 = WireElectronState.WIRE;
        testStructure.put(testItem6, testItemState6);


        newGame.insertStructure(testStructure);
        cellsMap = newGame.getCells();
        System.out.println("Printing after inserting structure");
        drawMap(cellsMap);

        for (int i = 0; i < 5; i++) {
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
        for (int i = 0; i < height; i++) {
            System.out.print("[");
            for (int j = 0; j < width; j++) {
                String st = cellsMap.get(new Coords2D(j, i)).toString();
                String p;
                if (st.contains("_")) {
                    int ind = st.indexOf("_") + 1;
                    p = st.substring(ind, ind + 1);
                } else
                    p = cellsMap.get(new Coords2D(j, i)).toString().substring(0, 1);
                System.out.print(" " + p + " ");
            }
            System.out.println("]");
        }
    }
}
