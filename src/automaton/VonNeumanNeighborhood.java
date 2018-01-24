package automaton;

import java.util.Set;
import java.util.TreeSet;

public class VonNeumanNeighborhood implements CellNeighborhood{


    VonNeumanNeighborhood(int width, int height, boolean wrap, int r) {
        this.width=width;
        this.height=height;
        this.weap=wrap;
        this.r=r;
    }
    private int width;
    private int height;
    private boolean weap;
    private int r;

public Set<CellCoordinates> cellNeighbors(CellCoordinates cellCoordinates){
    Set<CellCoordinates> neighbors = new TreeSet<>();
    Coords2D coords = (Coords2D) cellCoordinates;
    for(int i=0;i<width;i++)
        for(int j=0;j<height;j++)
            if((Math.abs(i-coords.x)+Math.abs(j-coords.y))<=r)
                neighbors.add(new Coords2D(i,j));

    return neighbors;
 }

}
