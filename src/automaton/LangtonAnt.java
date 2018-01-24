package automaton;

import java.util.Set;

import static automaton.BinaryState.DEAD;

public class LangtonAnt extends Automaton2Dim {
    public LangtonAnt(CellNeighborhood neighborsStrategy, CellStateFactory stateFactory) {
        super(neighborsStrategy, stateFactory);
    }

    protected Automaton newInstance(CellStateFactory cellStateFactory, CellNeighborhood cellNeighborhood) {
        LangtonAnt newInstance = new LangtonAnt(cellNeighborhood, cellStateFactory);
        return newInstance;
    }

    protected CellState nextCellState(Cell targetCell, Set<Cell> neighborsStates) {
        CellState currentState = targetCell.state;

        LangtonCell antCell;
        AntState antstate;
        Coords2D antcoords;
        Coords2D targetcoords = (Coords2D) targetCell.coords;
        boolean isAnt = false;

        for (Cell c : neighborsStates) {
            if (c.state instanceof LangtonCell) {
                antCell = (LangtonCell) c.state;
                antstate = antCell.antState;
                antcoords = (Coords2D) c.coords;
                if ((antstate == AntState.WEST && (targetcoords.x == antcoords.x - 1))
                        || (antstate == AntState.EAST && (targetcoords.x - 1 == antcoords.x))
                        || (antstate == AntState.NORTH && (targetcoords.y == antcoords.y + 1))
                        || (antstate == AntState.SOUTH && (targetcoords.y + 1 == antcoords.y))) {
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
                        return
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
                        return
                    }
                } else {

                }

            } else
                return targetCell.state;
        }


       /* if (currentState instanceof LangtonCell) {
            LangtonCell antCell = (LangtonCell) currentState;
            AntState antState = antCell.antState;
            AntState newState = AntState.NONE;
            if (antCell.cellState == DEAD) {// white
                if (antState == AntState.NORTH)
                    newState = AntState.EAST;
                else if (antState == AntState.EAST)
                    newState = AntState.SOUTH;
                else if (antState == AntState.SOUTH)
                    newState = AntState.WEST;
                else if (antState == AntState.WEST)
                    newState = AntState.NORTH;
                antCell.antState = newState;
                return ALIVE;
            }
            else{// black
                if (antState == AntState.NORTH)
                    newState = AntState.WEST;
                else if (antState == AntState.EAST)
                    newState = AntState.NORTH;
                else if (antState == AntState.SOUTH)
                    newState = AntState.EAST;
                else if (antState == AntState.WEST)
                    newState = AntState.SOUTH;
                antCell.antState = newState;
                return DEAD;
            }
        }
        else
            return currentState;*/
    }
}
