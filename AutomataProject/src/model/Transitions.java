package model;

public class Transitions {
    private final State To;
    private final char ch;

    public Transitions(State To, char ch) {
        this.To = To;
        this.ch = ch;
    }

    public State getTo() {
        return To;
    }

    public char getCh() {
        return ch;
    }
    
    
}
