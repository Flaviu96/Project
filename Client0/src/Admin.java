
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
public class Admin extends Account implements Serializable{
private static final long serialVersionUID=52L;
    public Admin(String username, String password) {
        super(username, password);
    }
    public Admin(String username,String password,int isAdmin){
        super(username,password,isAdmin);
    }
    public Admin(){
        
    }

    @Override
    public String getS() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Account myFunction(String nume,int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public Account myFunction1(String name, String target, int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
