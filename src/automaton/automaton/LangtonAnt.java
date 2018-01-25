package automaton.automaton;

import automaton.cell.Cell;
import automaton.cell.LangtonCell;
import automaton.coordinates.Coords2D;
import automaton.factory.CellStateFactory;
import automaton.neighborhood.CellNeighborhood;
import automaton.state.AntState;
import automaton.state.CellState;

import java.util.Set;

import static automaton.state.BinaryState.ALIVE;
import static automaton.state.BinaryState.DEAD;

public class LangtonAnt extends Automaton2Dim {
    private int width;
    private int height;

    public LangtonAnt(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory, int width, int height) {
        super(neighborsStrategy, stateFactory, width, height);
        this.width = width;
        this.height = height;
    }

    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        return new LangtonAnt(cellNeighborhood, cellStateFactory, width, height);
    }

    protected CellState nextCellState(Cell targetCell, Set<Cell> neighborsStates) {
        LangtonCell antCell;
        AntState antstate;
        int antid;
        Coords2D antcoords;
        Coords2D targetcoords = (Coords2D) targetCell.coords;
        if (targetCell.state instanceof LangtonCell) {
            LangtonCell langtonCell = (LangtonCell) targetCell.state;
            return langtonCell.cellState;
        }
        for (Cell c : neighborsStates) {
            if (c.state instanceof LangtonCell) {
                antCell = (LangtonCell) c.state;
                antstate = antCell.antState;
                antid = antCell.antId;
                antcoords = (Coords2D) c.coords;
                if ((antstate == AntState.WEST && (targetcoords.x == antcoords.x - 1) && (targetcoords.y == antcoords.y))
                        || (antstate == AntState.EAST && (targetcoords.x - 1 == antcoords.x) && (targetcoords.y == antcoords.y))
                        || (antstate == AntState.NORTH && (targetcoords.y == antcoords.y + 1) && (targetcoords.x == antcoords.x))
                        || (antstate == AntState.SOUTH && (targetcoords.y + 1 == antcoords.y) && (targetcoords.x == antcoords.x))) {
                    if (targetCell.state == DEAD) {
                        switch (antstate) {
                            case EAST:
                                antstate = AntState.SOUTH;
                                break;
                            case SOUTH:
                                antstate = AntState.WEST;
                                break;
                            case WEST:
                                antstate = AntState.NORTH;
                                break;
                            case NORTH:
                                antstate = AntState.EAST;
                                break;

                        }
                        return new LangtonCell(antstate, antid, ALIVE);
                    } else {
                        switch (antstate) {
                            case EAST:
                                antstate = AntState.NORTH;
                                break;
                            case SOUTH:
                                antstate = AntState.EAST;
                                break;
                            case WEST:
                                antstate = AntState.SOUTH;
                                break;
                            case NORTH:
                                antstate = AntState.WEST;
                                break;

                        }
                        return new LangtonCell(antstate, antid, DEAD);
                    }
                } else {
                    return targetCell.state;
                }

            }
        }
        return targetCell.state;
    }
}
