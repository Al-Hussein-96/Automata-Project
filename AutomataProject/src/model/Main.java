package model;

import java.util.List;



public class Main {
    public static void main(String[] args) {
        PhpAutomat DFA = new PhpAutomat();
        List<State> Resutl = DFA.Solve("+2.125");
        
        State LastState = Resutl.get(Resutl.size()-1);
        if(LastState.isIsFinal())
            System.out.println("Accepted : " + ((PhpState)LastState).getTypeAutomat()); 
        else 
            System.out.println("Not Accepted");
        
        for(State u:Resutl)
            System.out.println(u.getName());
        
        
        
        
    }
}
