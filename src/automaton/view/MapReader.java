package automaton.view;

import automaton.coordinates.CellCoordinates;
import automaton.coordinates.Coords2D;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapReader {
    public static Map<CellCoordinates, Integer> readMapFromFile(String name, int xOffset, int yOffset) {
        String path = "F:\\IntelliJ\\workspace\\java\\Automaton\\src\\automaton\\view\\" + name;
        BufferedReader br = null;
        FileReader fr = null;
        Map<CellCoordinates, Integer> parsedCells = new HashMap<CellCoordinates, Integer>() {
        };
        try {
            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader(path);
            br = new BufferedReader(fr);
            String sCurrentLine;
            int row = 0;
            int column = 0;
            Integer value;
            while ((sCurrentLine = br.readLine()) != null) {
                column = 0;
                for (int i = 0; i < sCurrentLine.length(); i++) {
                    value = Character.getNumericValue(sCurrentLine.charAt(i));
                    Coords2D coords2D = new Coords2D(column+xOffset, row+yOffset);
                    parsedCells.put(coords2D, value);
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

}
