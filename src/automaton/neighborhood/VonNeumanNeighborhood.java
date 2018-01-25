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

        Set<CellCoordinates> fixedNeighbors = new HashSet<>();
        if(wrap)
        {
            for(CellCoordinates n:neighbors ){
                Coords2D nCoordinates = (Coords2D) n;
                if(nCoordinates.x<0)
                    nCoordinates.x=width+nCoordinates.x;
                if(nCoordinates.y<0)
                    nCoordinates.y=height+nCoordinates.y;
                if(nCoordinates.x>=width)
                    nCoordinates.x=nCoordinates.x-width;
                if(nCoordinates.y>=height)
                    nCoordinates.y=nCoordinates.y-height;
                fixedNeighbors.add(nCoordinates);
            }
        }
        else
        {
            boolean isOkay=true;
            for(CellCoordinates n:neighbors ){
                isOkay=true;
                Coords2D nCoordinates = (Coords2D) n;
                if(nCoordinates.x<0)
                    isOkay=false;
                if(nCoordinates.y<0)
                    isOkay=false;
                if(nCoordinates.x>=width)
                    isOkay=false;
                if(nCoordinates.y>=height)
                    isOkay=false;
                if(isOkay)
                    fixedNeighbors.add(nCoordinates);
            }
        }
        return fixedNeighbors;
    }

}
