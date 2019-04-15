
import java.io.ObjectInputStream;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dezibil
 */
public class InputThread implements Runnable{
    public static ObjectInputStream input;
    public static Socket socket;
    public void run(){
        try{
         socket=new Socket("localhost",8500);
             input=new ObjectInputStream(socket.getInputStream());
            Command object;
            loop:while((object=(Command)input.readObject())!=null){
                switch(object.operation.getID()){
                    case 1:
                        MyThreadPool.getInstance().get().execute(new MainThread(object,2));
                        break;
                    case 2:
                        MyThreadPool.getInstance().get().execute(new MainThread(object,6));
                        break;
                    case 3:
                        MyThreadPool.getInstance().get().execute(new MainThread(object,10));
                        break;
                    case 4:
                        MyThreadPool.getInstance().get().execute(new MainThread(object,12));
                        break;
                    case 5:
                        MyThreadPool.getInstance().get().execute(new MainThread(13));
                        break;
                    case 6:
                        MyThreadPool.getInstance().get().execute(new MainThread(16));
                        break;
                    case 7:
                        MyThreadPool.getInstance().get().execute(new MainThread(object,8));
                        break;
                    case 8:
                        MyThreadPool.getInstance().get().execute(new MainThread(object,18));
                        break;
                    case 9:
                        MyThreadPool.getInstance().get().execute(new MainThread(object,21));
                        break;
                    case 10:
                        MyThreadPool.getInstance().get().execute(new MainThread(object,25));
                        break;
                    case 11:
                        MyThreadPool.getInstance().get().execute(new MainThread(object,29));
                        break;
                    case 12:
                        MyThreadPool.getInstance().get().execute(new MainThread(object,28));
                        break;
                    case 13:
                        MyThreadPool.getInstance().get().execute(new MainThread(object,30,false));
                        break;
                    case 14:
                        MyThreadPool.getInstance().get().execute(new MainThread(object,32));
                        break;
                    case 15:
                        System.out.println("Sunt in 15");
                        MyThreadPool.getInstance().get().execute(new MainThread(object,34));
                        break;
                    case 16:
                        MyThreadPool.getInstance().get().execute(new MainThread(object,40));
                        break;
                }
            }}catch(Exception e){
                
            }
    }
    
}