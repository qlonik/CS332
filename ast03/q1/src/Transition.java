public class Transition {
    private State from, to;
    private String symbol;

    public Transition(State from, String symbol, State to) {
        this.from = from;
        this.symbol = symbol;
        this.to = to;
    }

    public State getFrom() {
        return from;
    }

    public String getSymbol() {
        return symbol;
    }

    public State getTo() {
        return to;
    }

    public boolean startsWith(State a) {
        return from.equals(a);
    }

    public boolean endsWith(State a) {
        return to.equals(a);
    }

    public boolean symbolIs(String a) {
        return symbol.equals(a);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Transition.class) {
            return false;
        }

        Transition t = (Transition) obj;
        return this.from.equals(t.getFrom()) &&
                this.symbol.equals(t.getSymbol()) &&
                this.to.equals(t.getTo());
    }

    @Override
    public String toString() {
        return from.getStates().toString()
                + "--" + symbol + "->"
                + to.getStates().toString();
    }
}
