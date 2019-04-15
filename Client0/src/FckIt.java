
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dezibil
 */
public class FckIt implements Runnable{
    public static Account account;
    public int id;
    public FckIt(Account account,int id){
        this.account=account;
        this.id=id;
    }
    @Override
    public void run() {
        switch(id){
            case 1:
        {
            try {
                mda(account);
            } catch (InterruptedException ex) {
                Logger.getLogger(FckIt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            case 2:
        {
            try {
                yuhu(account);
            } catch (InterruptedException ex) {
                Logger.getLogger(FckIt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
        }
    }
    public void mda(Account account) throws InterruptedException{
        Thread.sleep(111111);
        System.out.println(account.username);
    }
    public void yuhu(Account account) throws InterruptedException{
//        Thread.sleep(1111111);
        System.out.println(account.username);
    }
    
}
