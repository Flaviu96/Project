
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dezibil
 */
public class Call implements Callable<ArrayList<Account>>{
 public int id;
    public Account account;
    public String test;
    public Call(Account account,int id){
        this.account=account;
        this.id=id;
    }
    public Call(String test,int id){
        this.test=test;
        this.id=id;
    }
    @Override
    public ArrayList<Account> call() throws Exception {
        ArrayList<Account>myArray=null;
       switch(id){
           case 1:
               myArray=DataBase.getInstance().getFriends(account);
               break;
           case 2:
               myArray=DataBase.getInstance().getBlockList(account);
               break;
           
       }
       return myArray;
    }
}
