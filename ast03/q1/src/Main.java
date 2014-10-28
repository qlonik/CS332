import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // testfile one
        Scanner testfile = new Scanner(new File("testfile"));
        NFA nfa = new NFA(testfile);
        DFA dfa = nfa.convertToDFA();

        for (State s : nfa.getStates()) {
            System.out.println("State: " + s);
            System.out.println("\tEPSILON-Closure: " + nfa.getEpsilonClosure(s));
        }
        System.out.println(dfa.getTable());
        System.out.println(dfa.getGrail());


        // testfile two
        Scanner testfile_2 = new Scanner(new File("testfile_2"));
        NFA nfa_2 = new NFA(testfile_2);
        DFA dfa_2 = nfa_2.convertToDFA();

        for (State s : nfa_2.getStates()) {
            System.out.println("State: " + s);
            System.out.println("\tEPSILON-Closure: " + nfa_2.getEpsilonClosure(s));
        }
        System.out.println(dfa_2.getTable());
        System.out.println(dfa_2.getGrail());


        // testfile three
        Scanner testfile_3 = new Scanner(new File("testfile_3"));
        NFA nfa_3 = new NFA(testfile_3);
        DFA dfa_3 = nfa_3.convertToDFA();

        for (State s : nfa_3.getStates()) {
            System.out.println("State: " + s);
            System.out.println("\tEPSILON-Closure: " + nfa_3.getEpsilonClosure(s));
        }
        System.out.println(dfa_3.getTable());
        System.out.println(dfa_3.getGrail());
    }
}
