package automaton;

import java.util.Set;
import java.util.TreeSet;

public class MoorNeighborhood implements CellNeighborhood {
    private int width;
    private int height;
    private boolean wrap;
    private int r;

    MoorNeighborhood(int width, int height, boolean wrap, int r) {
        this.width=width;
        this.height=height;
    }

    public Set<CellCoordinates> cellNeighbors(CellCoordinates cellCoordinates) {
        Coords2D coords = (Coords2D) cellCoordinates;
        Set<CellCoordinates> neighbors = new TreeSet<>();
        for (int i = -r; i <= r; i++) {
            for (int j = -r; j <= r; j++) {
                int x = coords.x + i;
                int y = coords.y + j;
                neighbors.add(new Coords2D(x,y));
            }
        }
        return neighbors;
    }

    public static void main(String[] argv) {
        CellCoordinates test2D = new Coords2D(3,3);
        MoorNeighborhood moorNeighborhood = new MoorNeighborhood(100,100,false,1);
        Set<CellCoordinates> neighborsTest = moorNeighborhood.cellNeighbors(test2D);
        for (CellCoordinates coord : neighborsTest) {
            Coords2D coords = (Coords2D) coord;
            System.out.println("[" + coords.x + ";" + coords.y + "]");
        }
    }
}