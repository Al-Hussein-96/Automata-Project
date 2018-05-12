package model;

import java.util.ArrayList;
import java.util.List;

public class PhpAutomat {

    private final State DeadState = new PhpState(-1, "DeadState");
    private final int NumberOfStates = 35;
    private final int StartState = 0;
    private final int FinalState[] = {2, 4, 6, 12, 17, 19, 22, 27, 30, 34};
    private final String TypeState[] = {"Variables", "Natural Digit", "Real Digit", "static", "while", "if", "for", "class", "case", "Comment"};
    private final List<State> ListStates = new ArrayList<>();

    public PhpAutomat() {
        for (int i = 0; i < NumberOfStates; i++) {
            State NewState = new PhpState(i, "q" + String.valueOf(i));
            this.ListStates.add(NewState);
        }
        for (int i = 0; i < FinalState.length; i++) {
            PhpState state = getState(FinalState[i]);
            state.setIsFinal(true);
            state.setTypeAutomat(TypeState[i]);
        }
        for (int i = 0; i < this.NumberOfStates; i++) {
            PhpState state = getState(i);
            switch (i) {
                case 0:
                    state.AddTrans(new Transitions(getState(1), '$'));
                    state.AddTrans(new Transitions(getState(3), '+'));
                    state.AddTrans(new Transitions(getState(3), '-'));
                    AddDigit(state, getState(4));
                    state.AddTrans(new Transitions(getState(31), '/'));

                    state.AddTrans(new Transitions(getState(7), 's'));
                    AddWords(getState(7), "tatic");

                    state.AddTrans(new Transitions(getState(13), 'w'));
                    AddWords(getState(13), "hile");

                    state.AddTrans(new Transitions(getState(18), 'i'));
                    AddWords(getState(18), "f");

                    state.AddTrans(new Transitions(getState(20), 'f'));
                    AddWords(getState(20), "or");

                    state.AddTrans(new Transitions(getState(23), 'c'));

                    break;
                case 1:
                    AddAlpha(state, getState(2));
                    state.AddTrans(new Transitions(getState(2), '_'));
                    break;
                case 2:
                    state.AddTrans(new Transitions(getState(2), '_'));
                    AddAlpha(state, getState(2));
                    AddDigit(state, getState(2));
                    break;
                case 3:
                    AddDigit(state, getState(4));
                    break;
                case 4:
                    state.AddTrans(new Transitions(getState(5), '.'));
                    AddDigit(state, state);
                    break;
                case 5:
                    AddDigit(state, getState(6));
                    break;
                case 6:
                    AddDigit(state, state);
                    break;
                case 23:
                    state.AddTrans(new Transitions(getState(24), 'l'));
                    AddWords(getState(24), "ass");

                    state.AddTrans(new Transitions(getState(28), 'a'));
                    AddWords(getState(28), "se");

                    break;
                case 31:
                    state.AddTrans(new Transitions(getState(32), '*'));
                    break;
                case 32:
                    state.AddTrans(new Transitions(getState(33), '*'));
                    AddComment(state, state);
                    break;
                case 33:
                    state.AddTrans(new Transitions(getState(33), '*'));
                    state.AddTrans(new Transitions(getState(34), '/'));
                    AddComment(state, getState(32));
                    break;
            }

        }

    }

    private PhpState getState(int from) {
        for (State u : this.ListStates) {
            if (u.getID() == from) {
                return (PhpState) u;
            }
        }
        return null;
    }

    public List<State> Solve(String Pattern) {
        List<State> Path = new ArrayList<>();
        State Start = getState(0);
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

    private void AddDigit(PhpState from, PhpState to) {
        for (char i = '0'; i <= '9'; i++) {
            from.AddTrans(new Transitions(to, i));
        }
    }

    private void AddAlpha(PhpState from, PhpState to) {
        for (char i = 'a'; i <= 'z'; i++) {
            from.AddTrans(new Transitions(to, i));
        }
        for (char i = 'A'; i <= 'Z'; i++) {
            from.AddTrans(new Transitions(to, i));
        }
    }

    private void AddComment(PhpState from, PhpState to) {
        AddAlpha(from, to);
        AddDigit(from, to);
    }

    private void AddWords(PhpState state, String Word) {
        for (int i = 0; i < Word.length(); i++) {
            PhpState nextState = getState(state.getID() + 1);
            state.AddTrans(new Transitions(nextState, Word.charAt(i)));
            state = nextState;
        }
    }

}
