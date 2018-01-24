package automaton;

import java.util.Set;

public class MoorNeighborhood implements CellNeighborhood {


    MoorNeighborhood(int width, int height) {
        this.width=width;
        this.height=height;
    }
    private int width;
    private int height;

    public Set<CellCoordinates> cellNeighbors(CellCoordinates cell) {

    }
}