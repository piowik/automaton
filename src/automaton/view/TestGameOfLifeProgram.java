package automaton.view;

import automaton.automaton.GameOfLife;
import automaton.coordinates.CellCoordinates;
import automaton.coordinates.Coords2D;
import automaton.factory.GeneralStateFactory;
import automaton.factory.UniformStateFactory;
import automaton.neighborhood.MoorNeighborhood;
import automaton.state.CellState;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static automaton.state.BinaryState.ALIVE;
import static automaton.state.BinaryState.DEAD;

public class TestGameOfLifeProgram {
    static int width = 3;
    static int height = 3;

    public static Map<CellCoordinates,Integer> ReadMapFromFile(String filename){
        BufferedReader br = null;
        FileReader fr = null;
        Map<CellCoordinates,Integer> parsedCells=new HashMap<CellCoordinates, Integer>() {
        };
        try {

            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader(filename);
            br = new BufferedReader(fr);

            String sCurrentLine;
            int row=0;
            int column=0;
            Integer value;
            while ((sCurrentLine = br.readLine()) != null) {
                column=0;
                for(int i=0;i<sCurrentLine.length();i++){
                    value=Character.getNumericValue(sCurrentLine.charAt(i));
                    Coords2D coords2D = new Coords2D(column,row);
                    parsedCells.put(coords2D,value);
                    column++;
                }
                row++;
            }

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }
        return parsedCells;
    }

    public static void main(String[] argv) {
        Map<CellCoordinates,Integer> mapFromFile = ReadMapFromFile("D:\\IntelliJ Projects\\src\\com\\company\\automaton\\src\\automaton\\view\\test.txt"); //TODO: dunno how path should work
        UniformStateFactory uniformStateFactory = new UniformStateFactory(DEAD);
        GeneralStateFactory generalStateFactory = new GeneralStateFactory(GameOfLife.convert(mapFromFile));
        MoorNeighborhood moorNeighborhood = new MoorNeighborhood(width, height, false, 1);
        int[] neighborsToNotDie = {2};
        int[] neighborsToStartLiving = {2,3};
        GameOfLife newGame = new GameOfLife(moorNeighborhood, generalStateFactory, width, height,neighborsToNotDie,neighborsToStartLiving);
        Map<CellCoordinates, CellState> cellsMap = newGame.getCells();
        System.out.println("Reading returned map (Size " + cellsMap.size() + ")");
        System.out.println("Iterating entrySet");
        drawMap(cellsMap);

      /*  Map<CellCoordinates, CellState> testStructure = new HashMap<>();

        // blinker (oscylator)
        // https://upload.wikimedia.org/wikipedia/commons/c/c2/2-3_O1.gif
        CellCoordinates testItem = new Coords2D(1, 1);
        CellState testItemState = ALIVE;
        testStructure.put(testItem, testItemState);

        CellCoordinates testItem2 = new Coords2D(1, 0);
        CellState testItemState2 = ALIVE;
        testStructure.put(testItem2, testItemState2);

        CellCoordinates testItem3 = new Coords2D(1, 2);
        CellState testItemState3 = ALIVE;
        testStructure.put(testItem3, testItemState3);

        newGame.insertStructure(testStructure);
        cellsMap = newGame.getCells();
        System.out.println("Printing after inserting structure");
        drawMap(cellsMap);*/

        for (int i = 0; i < 6; i++) {
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
                String p = cellsMap.get(new Coords2D(j, i)).toString().substring(0, 1);
                System.out.print(" " + p + " ");
            }
            System.out.println("]");
        }
    }
}
