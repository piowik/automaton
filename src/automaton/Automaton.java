package automaton;

import java.util.Map;

public abstract class Automaton {
   private Map<CellCoordinates,CellState> cells;

   private CellNeighborhood neighborsStrategy;


    public Automaton nextState() {
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
