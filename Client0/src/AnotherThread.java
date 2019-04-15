

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
public class AnotherThread implements Runnable{
    public static ObjectInputStream input;
    public static Socket socket;
    public void run(){
        try{
         socket=new Socket("localhost",8500);
           // output=new ObjectOutputStream(socket.getOutputStream());
             input=new ObjectInputStream(socket.getInputStream());
            Command object;
            // System.out.println("Sunt ac");
            loop:while((object=(Command)input.readObject())!=null){
                switch(object.operation.getID()){
                    case 1:
                        System.out.println("Sunt aici");
                        break;
                }
            }}catch(Exception e){
                
            }
    }
    
}
