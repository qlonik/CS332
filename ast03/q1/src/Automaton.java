import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Automaton {
    public static final String EPSILON = "\"\"";

    protected ArrayList<State> initial, accepting, all;
    protected ArrayList<Transition> transitions;
    protected ArrayList<String> symbols;

    protected Pattern startRegex = Pattern.compile("\\(START\\) \\|- (.*)");
    protected Pattern endRegex = Pattern.compile("(.*) -\\| \\(FINAL\\)");

    public Automaton() {
        initial = new ArrayList<>();
        accepting = new ArrayList<>();
        all = new ArrayList<>();
        transitions = new ArrayList<>();
        symbols = new ArrayList<>();
    }

    protected ArrayList<State> getStates() {
        return all;
    }
    protected State getState(State st) {
        for (State s : all) {
            if (s.equals(st)) {
                return s;
            }
        }
        return null;
    }
    protected State getState(String st) {
        for (State s : all) {
            if (s.equals(st)) {
                return s;
            }
        }
        return null;
    }
    protected boolean stateExist(State s) {
        return all.indexOf(s) > -1;
    }
    protected Transition getTransition(State a, String b, State c) {
        if (symbols.indexOf(b) != -1) {
            for (Transition t : transitions) {
                if (t.startsWith(a) && t.symbolIs(b) && t.endsWith(c)) {
                    return t;
                }
            }
        }
        return null;
    }
    protected ArrayList<Transition> getTransitions(State a, String b) {
        ArrayList<Transition> result = new ArrayList<>();
        for (String st : a.getStates()) {
            State s = getState(st);
            for (Transition t : transitions) {
                if (t.startsWith(s) && t.symbolIs(b)) {
                    result.add(t);
                }
            }
        }
        return result;
    }
    protected ArrayList<Transition> getTransitionsStartingWith(State a) {
        ArrayList<Transition> result = new ArrayList<>();
        for (Transition t : transitions) {
            if (t.startsWith(a)) {
                result.add(t);
            }
        }
        return result;
    }
    protected ArrayList<Transition> getTransitionsWithoutEpsilonStartingWith(State a) {
        ArrayList<Transition> result = new ArrayList<>();
        for (Transition t : transitions) {
            if (t.startsWith(a) && !t.symbolIs("\"\"")) {
                result.add(t);
            }
        }
        return result;
    }
    protected State getEpsilonClosure(State a) {
        State result = new State();
        for (Transition t : transitions) {
            if (t.startsWith(a) && t.symbolIs("\"\"")) {
                State to = t.getTo();
                result.addState(to);
            }
        }
        result.addState(a);
        return result;
    }
    protected void addState(State s) {
        if (all.indexOf(s) == -1) {
            all.add(s);
        }
    }
    protected void addInitial(State s) {
        if (initial.indexOf(s) == -1) {
            initial.add(s);
        }
    }
    protected void addAccepting(State s) {
        if (accepting.indexOf(s) == -1) {
            accepting.add(s);
        }
    }
    protected void addTransition(Transition t) {
        if (transitions.indexOf(t) == -1) {
            transitions.add(t);

            State a = t.getFrom(),
                    c = t.getTo();
            String b = t.getSymbol();

            addState(a);
            if (a.isInitial()) {
                addInitial(a);
            }
            if (a.isAccepting()) {
                addAccepting(a);
            }

            addSymbol(b);

            addState(c);
            if (c.isInitial()) {
                addInitial(c);
            }
            if (c.isAccepting()) {
                addAccepting(c);
            }
        }
    }
    protected void addSymbol(String s) {
        if (symbols.indexOf(s) == -1) {
            String el;
            int i = 0;

            while (i < symbols.size()) {
                el = symbols.get(i);
                if (el.length() < s.length()) {
                    i++;
                } else if (el.length() == s.length()) {
                    while (i < symbols.size() && s.compareTo(symbols.get(i)) > 0) {
                        if (symbols.get(i).length() > s.length()) {
                            break;
                        }
                        i++;
                    }
                    break;
                } else {
                    break;
                }
            }

            symbols.add(i, s);
        }
    }

    protected void populateAutomaton(Scanner in) {
        while (in.hasNext()) {
            State s;
            String line = in.nextLine(), start, end;

            Matcher mStart = startRegex.matcher(line);
            Matcher mEnd = endRegex.matcher(line);

            if (mStart.find()) {
                start = mStart.group(1);
                s = getState(start);
                if (s == null) {
                    s = new State();
                    s.addState(start);
                }
                addState(s);
                addInitial(s);
                s.setInitial(true);
            } else if (mEnd.find()) {
                end = mEnd.group(1);
                s = getState(end);
                if (s == null) {
                    s = new State();
                    s.addState(end);
                }
                addState(s);
                addAccepting(s);
                s.setAccepting(true);
            } else {
                Scanner lineSc = new Scanner(line);
                String fromS, symbS, toS;
                State from, to;
                Transition t;

                fromS = lineSc.next();
                symbS = lineSc.next();
                toS = lineSc.next();

                from = getState(fromS);
                to = getState(toS);

                if (from == null) {
                    from = new State();
                    from.addState(fromS);
                    addState(from);
                }
                if (to == null) {
                    to = new State();
                    to.addState(toS);
                    addState(to);
                }

                t = new Transition(from, symbS, to);
                addTransition(t);
                addSymbol(symbS);
            }
        }
    }

    @Override
    public String toString() {
        return all.toString() + "\n" + transitions.toString();
    }
}
