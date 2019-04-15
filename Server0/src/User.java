
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
public class User extends Account implements Serializable {
    public String email,target;
    public  User myUser;
    public User(String username, String password) {
        super(username, password);
    }
    public void setAccount(User user){
        this.myUser=user;
    }
    public User(String username,String password,String email){
        super(username,password);
        this.email=email;
    }
    public User(String username,int id){
        super(username,id);
    }
    public User(String code){
        super(code);
    }
    public User(String username,String password,int isAdmin){
        super(username,password,isAdmin);
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setInvites(int invites){
        this.invites=invites;
    }
    public void setTarget(String target){
        this.target=target;
    }
     public User(String username,String password, String date,int isAdmin,int id){
     super(username,password,date,isAdmin,id);
 }
    public User(){
    }
    public String getEmail(){
        return email;
    }

   

    @Override
    public String getS() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Account myFunction(String nume,int id) {
        User myUser=new User();
        if(id==1){
            myUser.username=nume;
        }
        else{
            myUser.code=nume;
        }
        return myUser;
        
    }

    @Override
    public Account myFunction1(String name, String target,int id) {
        User myUser=new User();
        myUser.username=name;
        myUser.target=target;
        myUser.id=id;
        return myUser;
        
    }
     public Account myFunction(String name,String target,int id,int id1){
        User myUser=new User();
         myUser.username=name;
        myUser.target=target;
        myUser.id=id;
        myUser.id1=id1;
        return myUser;
        
    }
     
}
