
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
public abstract class Account implements Serializable{
    private static final long serialVersionUID=42L;
    protected String username,password,date,code,oldPassword;
    protected int isAdmin,id,id1,invites;
    public static User user;
    public Account(String username,String password){
        this.username=username;
        this.password=password;
    }
   
    public void setAccount(User user){
        this.user=user;
    }
    public void setId(int id){
        this.id=id;
    }
    public void setCode(String code){
        this.code=code;
    }
    public Account(String code){
        this.code=code;
    }
    public Account(String username, String password, String date){
        this(username,password);
        this.date=date;
    }
    public Account(String username,int id){
        this.username=username;
        this.id=id;
    }
    public Account(String username,String password, String date,int isAdmin,int id){
        this(username,password,date);
        this.isAdmin=isAdmin;
        this.id=id;
    }
    public Account(String username,String password,int isAdmin){
        this(username,password);
        this.isAdmin=isAdmin;
    }
    public void setNewPassword(String newPassword){
        this.password=newPassword;
    }
    public void setOldPassword(String oldPassword){
        this.oldPassword=oldPassword;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public abstract Account myFunction(String nume,int id);
    public abstract Account myFunction1(String name,String target,int id);
    public Account(){
    }
    public void setUsername(){
        this.username=username;
    }
    public String getUsername(){
        return username;
        
    }
    public String getPassword(){
        return password;}
    public String getDate(){
        return date;
    }
    public int getID(){
        return id;
    }
    public String toString(){
        return username;
    }
    public String getCode(){
        return code;
    }
    public int getInvites(){
        return invites;
    }
    public abstract String getS();
}
