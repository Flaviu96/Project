
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
public class NewClass1 extends Thread{
    public static Object myObject=new Object();
    public int id;
    public boolean mda;
    public NewClass1(int id){
        this.id=id;}
    public void run(){
        switch(id){
            case 1:
                while(!mda){
                synchronized(myObject){
            try {
                myObject.wait();
                System.out.println("Am facut cv");
            } catch (InterruptedException ex) {
                Logger.getLogger(NewClass1.class.getName()).log(Level.SEVERE, null, ex);
            }
                }}
                break;
            case 2:
                synchronized(myObject){
                  myObject.notify();
                }
                break;
        }
    }
}
