
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dezibil
 */
public class MyThreadPool {
    public static ObjectInputStream input;
    public static ObjectOutputStream output;
    public static ThreadPoolExecutor myExecutor=(ThreadPoolExecutor) Executors.newFixedThreadPool(10);
     private static MyThreadPool instance;
     public static  BlockingQueue<Command> myQue=new ArrayBlockingQueue<Command>(6);
     public static MyThreadPool getInstance(){
		if(instance == null) 
			instance = new MyThreadPool();
		return instance;
	}
     public ThreadPoolExecutor get(){
         return myExecutor;
     }
}
