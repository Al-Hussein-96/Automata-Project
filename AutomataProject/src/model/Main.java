package model;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        Automata DFA = new Automata("automat.txt");
        List<State> Resutl = DFA.Solve(0, "101110000");
        
        State LastState = Resutl.get(Resutl.size()-1);
        if(LastState.isIsFinal())
            System.out.println("Accepted"); 
        else 
            System.out.println("Not Accepted");
        
        for(State u:Resutl)
            System.out.println(u.getName());
        
        
        
    }
}
