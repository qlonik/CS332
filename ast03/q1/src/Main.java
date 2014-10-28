import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        /*
        Scanner testfile = new Scanner(new File("testfile"));

        NFA nfa = new NFA(testfile);
        DFA dfa = nfa.convertToDFA();

        for (State s : nfa.getStates()) {
            System.out.println("State: " + s);
            System.out.println("\tEPSILON-Closure: " + nfa.getEpsilonClosure(s));
        }
        System.out.println(dfa.getTable(7));
        System.out.println(dfa.getMatchingTable(15));
        System.out.println(dfa.getGrail());
        */

        Scanner testfile_2 = new Scanner(new File("testfile_2"));

        NFA nfa_2 = new NFA(testfile_2);
        DFA dfa_2 = nfa_2.convertToDFA();

        for (State s : nfa_2.getStates()) {
            System.out.println("State: " + s);
            System.out.println("\tEPSILON-Closure: " + nfa_2.getEpsilonClosure(s));
        }
        System.out.println(dfa_2.getTable(8));
        System.out.println(dfa_2.getMatchingTable(33));
        System.out.println(dfa_2.getGrail());
    }
}
