
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dezibil
 */
public class AnotherThread implements Runnable {
    public int id;
    public String from,target;
    ArrayList<SendAndRec>myList=new ArrayList<>();
    static class SendAndRec{
       String target,from;
       public void setTarget(String target){
           this.target=target;
       }
       public void setFrom(String from){
           this.from=from;
       }
       public SendAndRec(String target,String from){
           this.from=from;
           this.target=target;
       }
   }
    public AnotherThread(int id){
        this.id=id;
    }
    public void run(){
        switch(id){
            case 1:
                myList.add(new SendAndRec(from,target));
                break;
            case 2:
                break;
        }
    }
}
