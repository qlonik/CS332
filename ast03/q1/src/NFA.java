import java.util.ArrayList;
import java.util.Scanner;

public class NFA extends Automaton {
    public NFA() {
        super();
    }

    public NFA(Scanner in) {
        super();
        populateAutomaton(in);
    }

    private State getEndStates(Transition transition) {
        State newState = new State(),
                endState = transition.getTo();
        newState.addState(endState);
        State epsClosure = getEpsilonClosure(endState);
        newState.addState(epsClosure);
        return newState;
    }

    public DFA convertToDFA() {
        DFA dfa = new DFA();
        State next, newState, epsilonClosure;
        ArrayList<State> queue = new ArrayList<>(),
                explored = new ArrayList<>();
        ArrayList<Transition> endTransition;

        for (State s : initial) {
            epsilonClosure = getEpsilonClosure(s);
            if (queue.indexOf(epsilonClosure) == -1) {
                queue.add(epsilonClosure);
            }
        }


        while (queue.size() > 0) {
            next = queue.remove(0);
            for (String symbol : symbols) {
                if (!symbol.equals(Automaton.EPSILON)) {
                    newState = new State();

                    endTransition = getTransitions(next, symbol);
                    for (Transition t : endTransition) {
                        newState.addState(getEndStates(t));
                    }

                    dfa.addTransition(new Transition(next, symbol, newState));

                    if (explored.indexOf(next) == -1) {
                        explored.add(next);
                    }
                    if (queue.indexOf(newState) == -1 &&
                            explored.indexOf(newState) == -1) {
                        queue.add(newState);
                    }
                }
            }
        }

        return dfa;
    }
}
