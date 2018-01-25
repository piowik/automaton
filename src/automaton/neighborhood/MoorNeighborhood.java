package automaton.neighborhood;

import automaton.coordinates.CellCoordinates;
import automaton.coordinates.Coords2D;

import java.util.HashSet;
import java.util.Set;

public class MoorNeighborhood implements CellNeighborhood {
    private int width;
    private int height;
    private boolean wrap;
    private int r;

    public MoorNeighborhood(int width, int height, boolean wrap, int r) {
        this.width = width;
        this.height = height;
        this.wrap = wrap;
        this.r = r;
    }

    public Set<CellCoordinates> cellNeighborhood(CellCoordinates cellCoordinates) {
        Coords2D coords = (Coords2D) cellCoordinates;
        Set<CellCoordinates> neighbors = new HashSet<>();
        for (int i = -r; i <= r; i++) {
            for (int j = -r; j <= r; j++) {
                if (i == 0 && j == 0)
                    continue;
                int x = coords.x + i;
                int y = coords.y + j;
                neighbors.add(new Coords2D(x, y));
            }
        }
        //TODO: wrap
        return neighbors;
    }
}