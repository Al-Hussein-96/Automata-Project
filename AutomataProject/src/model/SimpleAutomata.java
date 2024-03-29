package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleAutomata {

    private final State DeadState = new State(-1, "T");
    private int NumberOfStates;
    private int StartState;
    public String Alphabet;
    private List<State> ListStates = new ArrayList<>();

    public SimpleAutomata(String DirFile) {
        File file = new File(DirFile);

        try {
            Scanner input = new Scanner(file);
            this.Alphabet =input.nextLine();
            System.out.println("Alphbet" + this.Alphabet);
            this.NumberOfStates = input.nextInt();
            this.StartState = input.nextInt();
            this.ListStates.add(DeadState);
            for (int i = 0; i < NumberOfStates; i++) {
                int ok = input.nextInt();
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
                st.AddTrans(new Transitions(en, ch));
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(SimpleAutomata.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public SimpleAutomata(int NumberOfStates, int StartState, List<String> FinalState, String Alphabet) {
        this.NumberOfStates = NumberOfStates;
        this.StartState = StartState;
        this.Alphabet = Alphabet;
        this.ListStates.add(DeadState);
        for (int i = 0; i < NumberOfStates; i++) {
            State state = new State(i, "q" + String.valueOf(i));
            ListStates.add(state);
        }
        for(String u:FinalState)
        {
            State state = getState(u);
            state.setIsFinal(true);
        }
    }

    public void AddTransition(String from, String to, char ch) {
        State F = getState(from);
        State T = getState(to);
        F.AddTrans(new Transitions(T, ch));
    }

    public State getState(int from) {
        for (State u : this.ListStates) {
            if (u.getID() == from) {
                return u;
            }
        }
        return null;
    }

    public State getState(String from) {
        for (State u : this.ListStates) {
            if (u.getName().equals(from)) {
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

    public int getNumberOfStates() {
        return NumberOfStates;
    }

    public void setNumberOfStates(int NumberOfStates) {
        this.NumberOfStates = NumberOfStates;
    }

    public int getStartState() {
        return StartState;
    }

    public void setStartState(int StartState) {
        this.StartState = StartState;
    }

    public List<State> getListStates() {
        return ListStates;
    }

    public void setListStates(List<State> ListStates) {
        this.ListStates = ListStates;
    }

}
