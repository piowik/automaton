package automaton;

import java.util.Map;
import java.util.Set;

public abstract class Automaton {
   private Map<CellCoordinates,CellState> cells;

   private CellNeighborhood neighborsStrategy;

   private CellStateFactory stateFactoryl;



    public Automaton nextState() {
        //TODO
    }

    public void insertStructure(Map<? extends CellCoordinates, ? extends CellState> structure){
        //TODO
    }


    public CellIterator cellIterator(){
        //TODO
    }


    protected abstract Automaton newInstance(CellStateFactory cellStateFactory ,CellNeighborhood cellNeighborhood);

    protected abstract boolean hasNextCoordinates(CellCoordinates coords);

    protected abstract CellCoordinates initialCoordinates(CellCoordinates coords);

    protected abstract CellCoordinates nextCoordinates(CellCoordinates coords);

    protected abstract CellState nextCellState(CellState currentState, Set<Cell> neighborsStates);

    private Set<Cell> mapCoordinates(Set<CellCoordinates> coords){
        //TODO
    }

    class CellIterator {
        private CellState currentState; // TODO: rozbieżność
        public boolean hasNext() {
            // TODO: hasNext
            return true;
        }
        public Cell next() {
            // TODO: next
            return new Cell();
        }
        public void setState(CellState newState) {
            currentState = newState;
        }

    }
}
