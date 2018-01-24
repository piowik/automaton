package automaton;

import java.util.Set;
import java.util.TreeSet;

public class VonNeumanNeighborhood implements CellNeighborhood{


    VonNeumanNeighborhood(int width, int height) {
        this.width=width;
        this.height=height;
    }
    private int width;
    private int height;

public Set<CellCoordinates> cellNeighbors(CellCoordinates cell){
    Set<CellCoordinates> neighbors = new TreeSet<>();
 }

}
