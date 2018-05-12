package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Automata {

    private final State DeadState = new State(-1, "DeadState");
    private int NumberOfStates;
    private int StartState;
    private List<State> ListStates = new ArrayList<>();

    public Automata(String DirFile) {
        File file = new File(DirFile);

        try {
            Scanner input = new Scanner(file);
            this.NumberOfStates = input.nextInt();
            this.StartState = input.nextInt();
            System.out.println("NumberOfState : " + this.NumberOfStates);
            System.out.println("StartState : " + this.StartState);
            this.ListStates.add(DeadState);
            for (int i = 0; i < NumberOfStates; i++) {
                int ok = input.nextInt();
                System.out.println("State : " + i + " : " + ok);
                State state = new State(i, "q" + String.valueOf(i));
                state.setIsFinal((ok != 0));
                ListStates.add(state);
            }
            int NumberOfTranstion = input.nextInt();
            for (int i = 0; i < NumberOfTranstion; i++) {
                int from = input.nextInt();
                char ch = input.next().charAt(0);
                int to = input.nextInt();

                State st = getState(from);
                State en = getState(to);
                System.out.println(st.getName() + " : " + ch + " : " + en.getName());
                st.AddTrans(new Transitions(en, ch));
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Automata.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private State getState(int from) {
        for (State u : this.ListStates) {
            if (u.getID() == from) {
                return u;
            }
        }
        return null;
    }

    public List<State> Solve(int IDStart, String Pattern) {
        List<State> Path = new ArrayList<>();
        State Start = getState(IDStart);
        Path.add(Start);
        State Current = Start;
        for (int i = 0; i < Pattern.length(); i++) {
            char ch = Pattern.charAt(i);

            State NextState = GetNextState(Current, ch);
            Path.add(NextState);
            if (NextState == DeadState) {
                return Path;
            }

            Current = NextState;
        }

        return Path;
    }

    private State GetNextState(State Current, char ch) {
        List<Transitions> Trans = Current.getTrans();

        for (Transitions u : Trans) {
            if (u.getCh() == ch) {
                return u.getTo();
            }
        }
        return DeadState;
    }

}
