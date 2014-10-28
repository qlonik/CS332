import java.util.ArrayList;
import java.util.Scanner;

public class DFA extends Automaton {

    public DFA() {
        super();
    }

    public DFA(Scanner in) {
        super();
        populateAutomaton(in);
    }

    @Override
    protected ArrayList<Transition> getTransitions(State a, String b) {
        ArrayList<Transition> result = new ArrayList<>();
        for (Transition t : transitions) {
            if (t.startsWith(a) && t.symbolIs(b)) {
                result.add(t);
            }
        }
        return result;
    }

    private String addSpaces(String s, Integer i) {
        String result = s;
        while (result.length() < i) {
            result = " " + result;
        }
        return result;
    }

    public String getTable(int col_width) {
        String result = "";

        for (int i = 0; i < all.size(); i++) {
            State s = all.get(i);
            s.setAlias("" + i);
        }

        result += "|";
        result += addSpaces("", col_width);
        result += "|";
        for (String symbol : symbols) {
            result += addSpaces(symbol + " ", col_width);
            result += "|";
        }
        result += "\n";

        for (State s : all) {
            String state;

            state = addSpaces(s.toString() + " ", col_width - 4);
            if (s.isAccepting()) {
                state = "*" + state;
            }
            if (s.isInitial()) {
                state = "->" + state;
            }
            state = addSpaces(state, col_width);

            result += "|" + state + "|";

            for (String symbol : symbols) {
                State end = getState(getTransitions(s, symbol).get(0).getTo());
                result += addSpaces(end.toString() + " ", col_width) + "|";
            }

            result += "\n";
        }

        return result;
    }

    public String getGrail() {
        String result = "";

        for (int i = 0; i < all.size(); i++) {
            State s = all.get(i);
            s.setAlias("" + i);
        }

        for (State s : initial) {
            result += "(START) |- " + s.toString() + "\n";
        }

        for (Transition t : transitions) {
            String a = getState(t.getFrom()).toString(),
                    b = t.getSymbol(),
                    c = getState(t.getTo()).toString();

            result += a + " " + b + " " + c + " \n";
        }

        for (State s : accepting) {
            result += s.toString() + " -| (FINAL)\n";
        }

        return result;
    }

    public String getMatchingTable(int col_width) {
        String result = "";

        for (State s : all) {
            result += "|";
            result += addSpaces(s.getStates().toString() + " ", col_width);
            result += "|";
            result += " " + addSpaces(s.getAlias() + " ", 4);
            result += "|\n";
        }

        return result;
    }
}
