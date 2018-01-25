package automaton;

import java.util.HashMap;
import java.util.Map;

import static automaton.BinaryState.ALIVE;
import static automaton.BinaryState.DEAD;

public class TestGameOfLifeProgram {
    static int width = 3;
    static int height = 3;
    public static void main(String[] argv) {

        UniformStateFactory uniformStateFactory = new UniformStateFactory(DEAD);
        MoorNeighborhood moorNeighborhood = new MoorNeighborhood(width, height, false, 1);
        GameOfLife newGame = new GameOfLife(moorNeighborhood, uniformStateFactory, width, height);
        Map<CellCoordinates, CellState> cellsMap = newGame.getCells();
        System.out.println("Reading returned map (Size " + cellsMap.size() + ")");
        System.out.println("Iterating entrySet");
        drawMap(cellsMap);

        Map<CellCoordinates,CellState> testStructure = new HashMap<>();

        // blinker (oscylator)
        // https://upload.wikimedia.org/wikipedia/commons/c/c2/2-3_O1.gif
        CellCoordinates testItem = new Coords2D(1,1);
        CellState testItemState = ALIVE;
        testStructure.put(testItem,testItemState);

        CellCoordinates testItem2 = new Coords2D(1,0);
        CellState testItemState2 = ALIVE;
        testStructure.put(testItem2,testItemState2);

        CellCoordinates testItem3 = new Coords2D(1,2);
        CellState testItemState3 = ALIVE;
        testStructure.put(testItem3,testItemState3);

        newGame.insertStructure(testStructure);
        cellsMap = newGame.getCells();
        System.out.println("Printing after inserting structure");
        drawMap(cellsMap);

        for(int i = 0; i < 6; i++) {
            newGame = (GameOfLife) newGame.nextState();
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
                String p = cellsMap.get(new Coords2D(j,i)).toString().substring(0,1);
                System.out.print(" " + p + " ");
            }
            System.out.println("]");
        }
    }
}
