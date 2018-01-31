package automaton.neighborhood;

import automaton.coordinates.CellCoordinates;
import automaton.coordinates.Coords1D;
import automaton.coordinates.Coords2D;

import java.util.HashSet;
import java.util.Set;

public class OneDimNeighborhood implements CellNeighborhood {
    private int size;

    public OneDimNeighborhood(int size) {
        this.size = size;
    }

    public Set<CellCoordinates> cellNeighborhood(CellCoordinates cellCoordinates) {
        Coords1D coords = (Coords1D) cellCoordinates;
        Set<CellCoordinates> neighbors = new HashSet<>();
        for(int i=-1;i<=1;i++)
        {
            neighbors.add(new Coords1D(coords.x-i));
        }
        return neighbors;
    }
}