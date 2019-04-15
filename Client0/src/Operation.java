
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dezibil
 */
public class Operation implements Serializable {
    private int id,id1;
    public Operation(int id){
        this.id=id;}
    public Operation(int id,int id1){
        this.id=id;
        this.id1=id1;
    }
    public void setID(int id){
        this.id=id;
    }
    public int getID(){
        return id;
    }
    public int getID1(){
        return id1;
    }
}
