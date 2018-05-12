package model;

import java.util.ArrayList;
import java.util.List;

public class State {
    private int ID;
    private final String Name;
    private boolean IsFinal;
    private List<Transitions> Trans;

    public State(int ID,String Name) {
        this.ID = ID;
        this.Name = Name;
        Trans = new ArrayList<>();
    }

    public void AddTrans(Transitions NewTrans) {
        if (!this.Trans.contains(NewTrans)) {
            this.Trans.add(NewTrans);
        }
    }

    public void setIsFinal(boolean IsFinal) {
        this.IsFinal = IsFinal;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public boolean isIsFinal() {
        return IsFinal;
    }

    public List<Transitions> getTrans() {
        return Trans;
    }
    
    

}
