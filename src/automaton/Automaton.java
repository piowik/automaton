package automaton;

public abstract class Automaton {
    public Automaton nextState() {
        return this;
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
