
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dezibil
 */
public class Command implements Serializable {
    private static final long serialVersionUID=45L;
    public Account account;
     public Admin admin;
    public Movie movie;
    public Operation operation;
    public int value;
    public String status,date,username1,message,code;
    public boolean status1,username,email;
    public ArrayList<Account>myList0,myList1;
    public List<Movie>myList2;
    public Command(Account account,Operation operation){
        this.account=account;
        this.operation=operation;
    }
    public Command(){
        
    }
        public Command(ArrayList<Account>myList,Operation operation){
        this.myList0=myList;
        this.operation=operation;
    }
    public Command(String status,String username1,ArrayList<Account>myList0,ArrayList<Account>myList1,Operation operation){
        this.status=status;
        this.username1=username1;
        this.myList0=myList0;
        this.myList1=myList1;
        this.operation=operation;
    }
    public Command(boolean username,boolean email){
        this.username=username;
        this.email=email;
    }
    public Command(Operation operation){
        this.operation=operation;
    }
    public Command(boolean username,boolean email,Operation operation){
        this(username,email);
        this.operation=operation;
    }
    public Command(boolean status1,Operation operation){
        this.status1=status1;
        this.operation=operation;
    }
    public Command(String status, Account account, Operation operation){
         this(account,operation);
         this.status=status;
    }
    public Command(String status,Operation operation){
        this.status=status;
        this.operation=operation;
    }
    public Command(String code,boolean status1,Operation operation){
        this.code=code;
        this.status1=status1;
        this.operation=operation;
    }
    public Command(String status,String date,Operation operation){
        this(status,operation);
        this.date=date;
    }
    public void setMessage(String message){
        this.message=message;
    }
    public void setOperation(Operation operation){
        this.operation=operation;
    }
     public void setList(List<Movie>myList){
        this.myList2= myList;
    }
     public void setValue(int value){
         this.value=value;
     }
    public static void main(String[]args){
       
    }
}
