package automaton.neighborhood;

import automaton.coordinates.CellCoordinates;
import automaton.coordinates.Coords2D;

import java.util.HashSet;
import java.util.Set;


public class VonNeumanNeighborhood implements CellNeighborhood {


    public VonNeumanNeighborhood(int width, int height, boolean wrap, int r) {
        this.width = width;
        this.height = height;
        this.wrap = wrap;
        this.r = r;
    }

    private int width;
    private int height;
    private boolean wrap;
    private int r;

    public Set<CellCoordinates> cellNeighborhood(CellCoordinates cellCoordinates) {
        Set<CellCoordinates> neighbors = new HashSet<>();
        Coords2D coords = (Coords2D) cellCoordinates;
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                if ((Math.abs(i - coords.x) + Math.abs(j - coords.y)) <= r && (Math.abs(i - coords.x) + Math.abs(j - coords.y)) != 0)
                    neighbors.add(new Coords2D(i, j));

        return neighbors;
    }

}
