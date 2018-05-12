/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Al-Hussein
 */
public class PhpState extends State{
    String TypeAutomat;
    public PhpState(int ID, String Name) {
        super(ID, Name);
    }   

    public void setTypeAutomat(String TypeAutomat) {
        this.TypeAutomat = TypeAutomat;
    }

    public String getTypeAutomat() {
        return TypeAutomat;
    }
    
    
}
