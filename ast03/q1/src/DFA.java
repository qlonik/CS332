import java.util.ArrayList;
import java.util.Scanner;

public class DFA extends Automaton {
    private final int COLUMN_WIDTH = 15;

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

    public String getTable() {
        String result = "";

        for (int i = 0; i < all.size(); i++) {
            State s = all.get(i);
            s.setAlias("q_" + i);
        }

        result += "|";
        result += addSpaces("", COLUMN_WIDTH);
        result += "|";
        for (int i = 0; i < symbols.size(); i++) {
            result += addSpaces(symbols.get(i) + "    ", COLUMN_WIDTH);
            result += "|";
        }
        result += "\n";

        for (int i = 0; i < all.size(); i++) {
            State s = all.get(i);
            String st = addSpaces(s.toString(), 5);
            if (s.isAccepting()) {
                st = "*" + st;
            }
            if (s.isInitial()) {
                st = "->" + st;
            }

            result += "|";
            result += addSpaces(st + " ", COLUMN_WIDTH) + "|";

            for (int j = 0; j < symbols.size(); j++) {
                State end = getState(getTransitions(s, symbols.get(j)).get(0).getTo());
                result += addSpaces(end.toString() + "   ", COLUMN_WIDTH) + "|";
            }

            result += "\n";
        }

        return result;
    }

    public String getGrail() {
        String result = "";

        for (int i = 0; i < all.size(); i++) {
            State s = all.get(i);
            s.setAlias("q_" + i);
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
}
