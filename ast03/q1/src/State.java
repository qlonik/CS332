import java.util.ArrayList;
import java.util.Collections;

public class State {
    private ArrayList<String> states;
    private boolean initial;
    private boolean accepting;

    private String alias;

    public State() {
        states = new ArrayList<>();
        initial = false;
        accepting = false;
        alias = "";
    }

    public State(State s) {
        states = new ArrayList<>(s.getStates());
        initial = s.isInitial();
        accepting = s.isAccepting();
        alias = "";
    }

    public ArrayList<String> getStates() {
        return states;
    }

    public void addState(String add) {
        if (states.indexOf(add) == -1) {
            String el;
            int i = 0;

            while (i < states.size()) {
                el = states.get(i);
                if (el.length() < add.length()) {
                    i++;
                } else if (el.length() == add.length()) {
                    while (i < states.size() && add.compareTo(states.get(i)) > 0) {
                        if (states.get(i).length() > add.length()) {
                            break;
                        }
                        i++;
                    }
                    break;
                } else {
                    break;
                }
            }

            states.add(i, add);
        }
    }

    public void addState(State add) {
        ArrayList<String> st = add.getStates();
        for (String s : st) {
            addState(s);
        }
        if (!isInitial()) {
            setInitial(add.isInitial());
        }
        if (!isAccepting()) {
            setAccepting(add.isAccepting());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != State.class) {
            return false;
        }

        boolean result = true;
        State st = (State) obj;
        ArrayList<String> states = st.getStates();

        if (states.size() != this.states.size()) {
            result = false;
        }
        for (int i = 0; i < states.size() && result; i++) {
            if (!states.get(i).equals(this.states.get(i))) {
                result = false;
            }
        }

        return result;
    }

    public boolean equals(String st) {
        String sString = "";
        for (String s : states) {
            sString += s + ',';
        }

        return sString.equals(st + ',');
    }

    public void setInitial(boolean initial) {
        this.initial = initial;
    }

    public void setAccepting(boolean accepting) {
        this.accepting = accepting;
    }

    public boolean isInitial() {
        return initial;
    }

    public boolean isAccepting() {
        return accepting;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public boolean isEmpty() {
        return states.size() == 0;
    }

    @Override
    public String toString() {
//        return states.toString() + " initial: " + initial + " final: " + accepting;
        return !alias.equals("") ? alias : states.toString();
    }
}
