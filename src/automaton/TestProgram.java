package automaton;

import java.util.Map;

import static automaton.BinaryState.DEAD;

public class TestProgram {
    public static void main(String[] argv) {
        int width = 3;
        int height = 3;
        UniformStateFactory uniformStateFactory = new UniformStateFactory(DEAD);
        MoorNeighborhood moorNeighborhood = new MoorNeighborhood(width, height, false, 1);
        GameOfLife newGame = new GameOfLife(moorNeighborhood, uniformStateFactory, width, height);
        Map<CellCoordinates, CellState> cellsMap = newGame.getCells();
        System.out.println("Reading returned map (Size " + cellsMap.size() + ")");
        System.out.println("Iterating entrySet");
        for (Map.Entry<CellCoordinates, CellState> entry : cellsMap.entrySet()) {
            Coords2D coordinates = (Coords2D) entry.getKey();
            CellState state = entry.getValue();
            System.out.println("[" + coordinates.x + ";" + coordinates.y + "]" + " " + state);
        }
        /*System.out.println("Iterating keySet");
        for (CellCoordinates p : cellsMap.keySet()) {
            Coords2D coordinates = (Coords2D) p;
            CellState state = cellsMap.get(p);
            System.out.println("[" + coordinates.x + ";" + coordinates.y + "] " + state);
        }*/
    }
}
