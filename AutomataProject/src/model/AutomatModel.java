//package model;
//
//import java.util.List;
//
//
//
//public class AutomatModel {
//
//    public AutomatModel(String dir) {
//    }
//    
//    
//    
//    
//    
//    public static void main(String[] args) {
//        SimpleAutomata DFA = new SimpleAutomata("automat.txt");
//        
//        List<State> Resutl = DFA.Solve(0,"101");
//        
//        State LastState = Resutl.get(Resutl.size()-1);
//        if(LastState.isIsFinal())
//            System.out.println("Accepted : "); 
//        else 
//            System.out.println("Not Accepted");
//        
//        for(State u:Resutl)
//            System.out.println(u.getName());
//        
//        
//        
//        
//    }
//}
