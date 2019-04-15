
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import static java.lang.Compiler.command;
import java.net.Socket;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
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
public class Worker implements Runnable {
    public User d=new User();
    public Command com;
    public String username=null,genre;
    public  String codee=null;
    public  int cou=0,value=0;
    public static int counter=0;
    public static Object firstLocker=new Object();
    public static Object secondLocker=new Object();
    public static Object thirdLocker=new Object();
    public static Object fourthLocker=new Object();
    public static boolean finished=false,blocked;
    public static List<Worker1>CodeList;
    public static ConcurrentHashMap<String,Worker1>myLi=new ConcurrentHashMap<String,Worker1>();
    public static ConcurrentHashMap<Integer,SendAndRec>myList=new ConcurrentHashMap<Integer,SendAndRec>();
    public static ConcurrentHashMap<Integer,Mov>comedyList=new ConcurrentHashMap<Integer,Mov>();
    public static ConcurrentHashMap<Integer,Mov>SFList=new ConcurrentHashMap<Integer,Mov>();
    public static ConcurrentHashMap<Integer,Mov>crime=new ConcurrentHashMap<Integer,Mov>();
    public static ConcurrentHashMap<Integer,Mov>Thriller=new ConcurrentHashMap<Integer,Mov>();
    public static BlockingQueue<Mov> queue=new ArrayBlockingQueue<Mov>(10);
    private static AtomicInteger c = new AtomicInteger(0);
    public int id;
    public ObjectInputStream input;
    public ObjectOutputStream output,output1,no1,no2,no3,no4,no5;
    public Socket socket;
    public boolean online=false;
    public int test=0;
    public static int firstThread,secondThread,thirdThread,fourthThread;
    public String numeThread;
    public static BufferedWriter comedy,ThrillerBuff,SF,Crime;
    public static int comedyCounter,ThrillerCounter,SFCounter,CrimCounter;
    public static int once=0,anotherTest=0;
    public Worker(Socket socket,int id){
        this.socket=socket;
        this.id=id;
    }
    public Worker(int id,Command com){
        this.id=id;
        this.com=com;
    }
    public Worker(){
        
    }
    public Worker(int id,int value){
        this.id=id;
        this.value=value;
    }
    public Worker(String genre,int id){
        this.genre=genre;
        this.id=id;
    }
    public Worker(Socket socket,int id, String numeThread){
        this(socket,id);
        this.numeThread=numeThread;
    }
        public void setOutput(int i,ObjectOutputStream out,boolean status,Command command,String username) throws IOException{
        ObjectOutputStream result;
        switch(i){
            case 1:
                if(status){
                    no1=out;
                    output.writeObject(new Command(new User(username,1),new Operation(13)));
                }
                else{
                   
                    no1.writeObject(command);
                }
                break;
            case 2:
                if(status){
                    no2=out;
                    output.writeObject(new Command(new User(username,2),new Operation(13)));
                }
                else{
                   
                    no2.writeObject(command);
                }
                break;
            case 3:
                 if(status){
                     no3=out;
                     out.writeObject(new Command(new User(username,3),new Operation(13)));
                 }
                 else{
                     no3.writeObject(command);
                 }
                break;
            case 4:
                if(status){
                    no4=out;
                    out.writeObject(new Command(new User(username,1),new Operation(13)));
                }
                else{
                    no4.writeObject(command);
                }
                break;
            case 5:
                if(status){
                no5=out;
                out.writeObject(out);
                out.writeObject(new Command(new User(username,1),new Operation(13)));
                }
                else{
                    no5.writeObject(command);
                }
                break;
        }
        
    }

    
    public class Worker1 extends Thread{
        public boolean used=false;
        public Worker1(String mycode){
            codee=mycode;
           this.start();
        }
        
        @Override
        public void run() {
            int counter=9;
             while(counter>=0 && used!=true){
                 for(int i=60;i>=0 && !used;i--){
                     try {
                         Thread.sleep(1000);
                     } catch (InterruptedException ex) {
                         Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
                 counter--;
             }
             myLi.remove(codee);
            
        }
        
    }

   static class SendAndRec{
       String target,from;
       public void setTarget(String target){
           this.target=target;
       }
       public void setFrom(String from){
           this.from=from;
       }
   }
   static class Mov{
       String name;
       int value,counter;
       boolean used;
       public void setName(String name){
           this.name=name;
       }
       public void setValue(int value){
           this.value=value;
       }
       public void setUsed(boolean used){
           this.used=used;
       }
       public void setCounter(int counter){
           this.counter=counter;
       }
   }
    
    public void run(){
        
       Command command=null;
       
        try {
            DataBase.getInstance().Connection();
        
       switch(id){
           case 1:try{
               input=new ObjectInputStream(socket.getInputStream());
       output=new ObjectOutputStream(socket.getOutputStream());
               while((command=(Command)input.readObject())!=null){
           switch(command.operation.getID()){
               
               case 1:
                   Account account=DataBase.getInstance().authentification(command.account);
                   verify(account);
                 
                   break;
               case 2:
                  String code=DataBase.getInstance().checkCode(command.account.getCode());
                  searching4Code(code);
                   break;
               case 3:
                  searchingTheCode1(command.code);
                   break;
               case 4:
                   Command com=DataBase.getInstance().UsernameAndEmail((User)command.account);
                   checkEmailAndUsername(com.username,com.email,command.account);
                   break;
               case 5:
                   DataBase.getInstance().updateCode(codee);
                   break;
               case 6:
                   sendRequest(command);
                   break;
               case 7:
                   updateReqAndQue(command);
                   break;
               case 8:
                   sendRequests(command.account);
                   break;
               case 9:
                   boolean result=DataBase.getInstance().updatePassword(command.account);
                   output.writeObject(new Command(result,new Operation(6)));
                   break;
               case 10:
                   Command myComm=new Command(DataBase.getInstance().getProfile(command.account),new Operation(4));
                   output.writeObject(myComm);
                   break;
               case 11:
                   DataBase.getInstance().updateCode(command.account);
                   break;
               case 12:
                   searching((User) command.account);
                   if(online==false){
                       output.writeObject(new Command(false,new Operation(10)));
                   }
                   break;
               case 13:
                   sendPM(command);
                   break;
               case 14:
                   addBlock(command);
                   break;
               case 15:
                   getMovies(command.status);
                   break;
               case 16:
                   MyThreadPool.getInstance().get().execute(new Worker(3,command));
                   break;
               case 17:
                   getAllUsers();
                   break;
           }    
       }}catch(Exception e){
           e.printStackTrace();
       }
               break;
           case 2:
               switch(genre){
                   case "Comedy":
                       searchingAndUpdating(comedyList,firstLocker,genre);
                       break;
                   case "Fantasy":
                       searchingAndUpdating(SFList,secondLocker,genre);
                       break;
                   case "Thriller":
                       searchingAndUpdating(Thriller,thirdLocker,genre);
                       break;
                   case "Cryme":
                       searchingAndUpdating(crime,fourthLocker,genre);
                       break;
               }
               break;
           case 3:
               addInHashMap(com.movie.genre,com.movie.name,com.movie.score);
               break;
        }
       } catch (SQLException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    public void sendRequests(Account account) throws SQLException, IOException{
        ArrayList<Account>mylist=DataBase.getInstance().getRequests(account);
        output.writeObject(new Command(mylist,new Operation(9)));
    }
 
    
   
    public  void verify(Account account) throws IOException, SQLException, InterruptedException, ExecutionException{
        if(account==null){
            output.writeObject(new Command("Wrong",new Operation(1)));
        }
        else{
            if(account.getDate().isEmpty()){

                sendIt(account);
                Server.myLi.put(account.getUsername(), output);

               
            }else{

             Date1 myData0=Date1.convert(account.getDate());
             int result=Date1.check2(myData0, Date1.getDate());
             switch(result){
            case 1:
                DataBase.getInstance().updateBanDate(account);
               sendIt(account);
                Server.myLi.put(account.getUsername(), output);
                break;
            case 2:
                //banned
                output.writeObject(new Command("ban",account.getDate(),new Operation(1)));
                break;
            case 4:
                output.writeObject(new Command("ban",account.getDate(),new Operation(1)));
                //banned
                break;
            
        }
            }
        }
        
    }
    public void increment() {
         c.getAndIncrement();
      }

      public int value() {
         return c.get();
      }

    public void stop(){
        finished=true;
    }
    public void addInHashMap(String genre,String name,int value) throws InterruptedException{
        switch(genre){
            case "Comedy":
                adding(comedyList,value,name,firstLocker,"Comedy",firstThread);
                break;
            case "Fantasy":
                adding(SFList,value,name,secondLocker,"Fantasy",secondThread);
                break;
            case "Crime":
                adding(crime,value,name,thirdLocker,"Crime",thirdThread);
                break;
            case "Thriller":
                adding(Thriller,value,name,fourthLocker,"Thriller",fourthThread);
                break;
        }
    }
    public void adding(ConcurrentHashMap<Integer,Mov>myMap,int value, String name,Object locker,String genre,int thread) throws InterruptedException{
        anotherTest++;
        Integer myInt=new Integer(anotherTest);
        Worker.Mov myMovie=new Worker.Mov();
            myMovie.setName(name);
            myMovie.setValue(value);
            
        if(myMap.size()<8){
            myMap.put(myInt,myMovie);
        }
        else{
           if(thread==0){
               thread++;
            MyThreadPool.getInstance().get().execute(new Worker(genre,2));
            synchronized(locker){
                locker.wait();
                int countUp=0;
                for(Mov mov:queue){
                    countUp++;
                    adding(myMap,countUp,name,locker,genre,thread);
                }
                queue.removeAll(queue);
                thread=0;
                    
            }
        }
           else{
               queue.add(myMovie);
           }}
        
       
    }

    public void sendPM(Command command) throws IOException{
        Command anotherComm=new Command();
            anotherComm.message=command.message;
            anotherComm.setOperation(new Operation(12));
            anotherComm.username1=command.username1;
        if(command.status1==false && counter==0){
            searching((User)command.account,false);
            setOutput(1,null,false,anotherComm,null);
            counter++;
        }
        else{
          
        setOutput(command.account.id,null,false,anotherComm,null);
        }
    }
    public  void addBlock(Command d1)throws IOException, SQLException{
        String myArray[]=null,secondArray[]=null;
       String result= DataBase.getInstance().getBlock(d1.account.username);
       String result1=DataBase.getInstance().getFriends(((User)d1.account).target);
       if(result!=null){
           myArray=result.split("/");
       }
       if(result1!=null){
           secondArray=result1.split("/");
       }
       String firstResult=(result==null)?Integer.toString(d1.account.id):result+d1.account.id+"/";
       String secondResult=(result1==null)?Integer.toString(d1.operation.getID1()):findID(secondArray,Integer.toString(d1.operation.getID1()));
       DataBase.getInstance().updateBlockList(d1.account.username, firstResult, ((User)d1.account).target, secondResult);
       d1.operation.setID(14);
       setOutput(d1.account.id1,null,false,d1,null);  
    }
    public void sendIt(Account account)throws IOException, SQLException, InterruptedException, ExecutionException{
       Future<ArrayList<Account>>myList=MyThreadPool.getInstance().get().submit(new Call(account,1));
       Future<ArrayList<Account>>myList1=MyThreadPool.getInstance().get().submit(new Call(account,2));
       output.writeObject(new Command("ok",account.getUsername(),myList.get(),myList1.get(),new Operation(1,account.id)));
    }
    public void checkEmailAndUsername(boolean username,boolean email,Account account) throws SQLException, IOException{
        if(username==false && email==false){
            DataBase.getInstance().register((User) account);
            String target=DataBase.getInstance().updateCode(account.code);
            searching(target);
            if(online==true){
                output1.writeObject(new Command(new Operation(5)));
            }
            output.writeObject(new Command(username,email,new Operation(3)));
        }
        else{
            output.writeObject(new Command(username,email,new Operation(3)));
        }
    }
    public  void getMovies(String genre) throws SQLException, IOException, ClassNotFoundException{
       List<Movie>myList= DataBase.getInstance().getMovies(genre);
       for(Movie movie:myList){
           if(movie.blob.length()!=0){
               movie.setList(file(movie.blob));
           }
           
       }
       Command myCommand=new Command();
       myCommand.setList(myList);
       myCommand.setOperation(new Operation(15));
      
       output.writeObject(myCommand);
       }
    public void getAllUsers() throws SQLException, SQLException, IOException{
        ArrayList<Account>myList1=DataBase.getInstance().getAllUsers();
        Command myCommand=new Command();
        myCommand.myList0=myList1;
        myCommand.setOperation(new Operation(16));
        output.writeObject(myCommand);
    }
    
    public List<String> file(Blob blob) throws SQLException, IOException, ClassNotFoundException{
       List<String>myList=new ArrayList<String>();
       InputStream in= blob.getBinaryStream();
       BufferedReader bw = new BufferedReader(new InputStreamReader(in));
       String comm;
       while((comm=bw.readLine())!=null){
           myList.add(comm);
       }
       return myList;
   }
    public void hasascriem() throws FileNotFoundException, IOException{
       File fout = new File("out.txt");
	FileOutputStream fos = new FileOutputStream(fout);
 
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

 
	bw.close();
    }
    public void sendRequest(Command command) throws IOException, SQLException{
        User myUser=(User)command.account;
        searching(myUser.target);
        if(online==true){
            output1.writeObject(new Command(command.account,new Operation(8)));
             nothing(myUser);
        }
        else{
          nothing(myUser);
        }
        online=false;
    }
    public void updateReqAndQue(Command command) throws SQLException{
        User myUser=(User)command.account;
        String id=Integer.toString(myUser.id);
        String id1=Integer.toString(myUser.id1);
        Command myComm=DataBase.getInstance().friendMethod((User) command.account);
        String[]myArray=myComm.status.split("/");
        String req=findID(myArray,id1);//me
        String[]myArray1=myComm.que.split("/");
        String que=findID(myArray,id);//who sent
         DataBase.getInstance().updateRequestsAndQueue(myUser.target , myUser.username, que, req);
        if(command.status.equals("yes")){
            String result=DataBase.getInstance().getFriends(myUser.username);
            String anotherRes=DataBase.getInstance().getFriends(myUser.target);
            anotherRes=(anotherRes==null)?id1:anotherRes+"/"+id1;
            result=(result==null)?id:result+"/"+id;
            DataBase.getInstance().updateFriends(result,anotherRes,myUser.username,myUser.target);
        }
    }
    public static String findId(String []myArray,String id){
        String result=null;
        List<String>list=new ArrayList<String>(Arrays.asList(myArray));
        for(String string:list){
            if(string.equals(id)){
                list.remove(string);
                break;
            }
        }
        return result;
    }

    public static String findID(String []array,String id){
        String result=new String();
        int counter=0;
        List<String>list=new ArrayList<String>(Arrays.asList(array));
        Iterator it=list.iterator();
        while(it.hasNext()){
            String myString=(String) it.next();
            if(myString.equals(id)){
                it.remove();
            }
        }
        if(list.size()!=1){
            Iterator it1=list.iterator();
        while(it1.hasNext()){
            String myString=(String)it1.next();
           
            result+=myString+"/";
        }
        }
        return result;
    }
    public String[] splitString(String string,String regex){
        String []result=string.split(regex);
        return result;
    }
    public void searchingTheCode(String code) throws IOException{
        if(myLi.isEmpty()){
            myLi.put(code, new Worker1(code));
            output.writeObject(new Command("ok",new Operation(2)));
        }else{
        for(Map.Entry e: myLi.entrySet()){
            if(e.getKey().equals(code)){
                output.writeObject(new Command("used",new Operation(2)));
            }
            else{
                output.writeObject(new Command("ok",new Operation(2)));
            }
        }}

    }
    public void searchingTheCode1(String code)throws IOException{
        for(Map.Entry e:myLi.entrySet()){
            if(e.getKey().equals(code)){
                ((Worker1)e.getValue()).used=true;
                break;
            }
        }
    }
    public String req(String req,String id){
        boolean reqIsEmpty=false;
        if(req.isEmpty()){
            req=id+="/";
            reqIsEmpty=true;
        }
        else{
            req+=id+"/";
        }
        return req;
    }
    public String que(String que,String id){
        boolean  queIsEmpty=false;
        if(que.isEmpty()){
            que=id+="/";
            queIsEmpty=true;
        }
        else{
            que+=id+"/";
        }
        return que;
    }
    public void nothing(User myUser) throws SQLException{
        Command myCommand= DataBase.getInstance().getRequestsAndGetQue(myUser);
        String id= Integer.toString(DataBase.getInstance().getId(myUser.target));
        String id1=Integer.toString(myUser.id1);
        String que=que(myCommand.que,id);
        String req=req(myCommand.status,id1);
        DataBase.getInstance().updateRequestsAndQueue(myUser.username, myUser.target, que , req);
    }
    public void searching4Code(String code) throws IOException{
        if(code==null){
            output.writeObject(new Command("wrong",new Operation(2)));
        }
        else{
            searchingTheCode(code);
        }
    }
    public void searching(String target,Command command) throws IOException{
        for(Map.Entry e: Server.myLi.entrySet()){
            if(e.getKey().equals(username)){
                online=true;
                output1=(ObjectOutputStream)e.getValue();
                output1.writeObject(command);
            }
        }
    }
    public void searching(String target){
        for(Map.Entry e:Server.myLi.entrySet()){
            if(e.getKey().equals(target)){
                output1=(ObjectOutputStream)e.getValue();
                online=true;
                break;
            }
        }
    }
    public void searching(User account) throws IOException{
        for(Map.Entry e: Server.myLi.entrySet())
        {
            if(e.getKey().equals(account.target)){
                searchingInSecAndRec(account.target,account.username);
                online=true;
                cou++;
                Command my=new Command();
                 my.status1=true;
                 my.account=account;
                 my.operation=new Operation(10);
                 output.writeObject(my);
                setOutput(cou,(ObjectOutputStream)e.getValue(),true,null,account.target);
                break;
            }
        }}
    public void searching(User account,boolean ah) throws IOException{
        for(Map.Entry e: Server.myLi.entrySet())
        {
            if(e.getKey().equals(account.target)){
                searchingInSecAndRec(account.target,account.username);
                online=true;
                cou++;
                setOutput(cou,(ObjectOutputStream)e.getValue(),true,null,account.target);
                break;
            }
        }
    }
    public   void searchingInSecAndRec(String target,String myUsername) throws IOException{

        if(myList.isEmpty()){
         
            Worker.SendAndRec firstObject=new Worker.SendAndRec();
            firstObject.setFrom(myUsername);
            firstObject.setTarget(target);
            myList.put(cou, firstObject);
            output.writeObject(new Command(true,new Operation(11)));
        }else{
        for(Map.Entry  sec:myList.entrySet()){  
            String targeet=((SendAndRec)sec.getValue()).target;
            String froom=((SendAndRec)sec.getValue()).from;
            if(myUsername.equals(targeet) && target.equals(froom)){
                output.writeObject(new Command(false,new Operation(11)));
            }
            else{
                Worker.SendAndRec firstObject=new Worker.SendAndRec();
                firstObject.setFrom(myUsername);
                firstObject.setTarget(target);
                myList.put(cou,firstObject);
                output.writeObject(new Command(true,new Operation(11)));
                
            }
        }}
    }
    public static String getFileAndUpdate(ConcurrentHashMap<Integer,Mov>comedy){
        String firstMovie=null,secondMovie=null;
        boolean found=false;
        int counter=0,id=1,firstTime=0,anotherCounter=3,another=comedy.size(),loopCounter=0,mda=0;
        int movCounter=0;
      loop:while(another>0){
          secondLoop:for(Map.Entry e:comedy.entrySet()){              
              if(firstTime==0){
                  loos:switch(id){
                  case 1:
                      if(((Mov)e.getValue()).counter==0){
                      firstMovie=((Mov)e.getValue()).name;
                      ((Mov)e.getValue()).used=true;
                      ((Mov)e.getValue()).setCounter(1);
                  
                  }
                      else{
                          boolean done=false;
                          loopa:for(Map.Entry e1:comedy.entrySet()){
                              if(((Mov)e1.getValue()).counter==0){
                              done=true;
                              firstMovie=((Mov)e1.getValue()).name;
                              ((Mov)e1.getValue()).setCounter(1);
                              ((Mov)e1.getValue()).used=true;
                        
                              break loopa;
                          }
                          }
                          if(done==false){
                              firstMovie=((Mov)e.getValue()).name;
                              ((Mov)e.getValue()).setCounter(1);
                              ((Mov)e.getValue()).used=true;
                          }
                      }
                      firstTime=1;
                      movCounter=0;
                      counter++;
      
                      break;
                  case 2:
                      System.out.println();
                      break;
                  case 3:
       
                      anotherLoop:for(Map.Entry anotherValue:comedy.entrySet()){
                          if(!firstMovie.equals(((Mov)anotherValue.getValue()).name)){
                          secondMovie=firstMovie;
                          firstMovie=((Mov)anotherValue.getValue()).name;
                          ((Mov)anotherValue.getValue()).used=true;;
                          ((Mov)anotherValue.getValue()).setCounter(1);
                          counter++;
                         break anotherLoop;
                      }
                      }
                 
                      break;
                  case 4:
                
                      anotherLoops:for(Map.Entry anotherValue:comedy.entrySet()){
                          if(!firstMovie.equals(((Mov)anotherValue.getValue()).name) && !secondMovie.equals(((Mov)anotherValue.getValue()).name)){
                              firstMovie=((Mov)anotherValue.getValue()).name;
                              ((Mov)anotherValue.getValue()).used=true;
                              ((Mov)anotherValue.getValue()).setCounter(1);
                              counter++;
                      
                              break anotherLoops;
                          }
                      }
          
                      break;
                  default:
                      setUsed(comedy);
                      firstTime=0;
                      id=1;
       
                      break;
              }}
              firstTime++;

              if(firstMovie.equals(((Mov)e.getValue()).name) && ((Mov)e.getValue()).used==false){
                  ((Mov)e.getValue()).used=true;
               
                  ((Mov)e.getValue()).setCounter(1);
                  counter++;
              }
              if(counter>=anotherCounter){
                  found=true;
         
                 break loop;
              }
              ((Mov)e.getValue()).used=false;
          }
          loopCounter++;
          
          switch(loopCounter){
              case 1:
                  firstTime=0;
                  id=3;
                  break;
              case 2:
                  firstTime=0;
                  id=4;
                  break;
              case 3:setUsed(comedy);
              firstTime=0;
              id=1;
              firstMovie=null;
              secondMovie=null;
              loopCounter=0;
              if(anotherCounter>2){
                  anotherCounter--;
              }
                  break;
          }
       another--;
       counter=0;
       
     }

      return firstMovie+"/"+found;
    }
    public static void setUsed(ConcurrentHashMap<Integer,Mov>myMap){
        for(Map.Entry e:myMap.entrySet()){
            ((Mov)e.getValue()).used=false;
        }
        
    }
    public static void searchingAndUpdating(ConcurrentHashMap<Integer,Mov>myMap,Object mylocker,String genre) throws SQLException, IOException{
        synchronized(mylocker){
        int counter=3;
        String myString;
        loop:while(counter>0){
            String genre1=getFileAndUpdate(myMap);
            String []myArray=genre1.split("/");
            String name=myArray[0];
            String status=myArray[1];
            if(status.equalsIgnoreCase("false")){
                for(Map.Entry my:myMap.entrySet()){
                    myString=((Mov)my.getValue()).name+"/"+((Mov)my.getValue()).value;
                    writeIntoFile(myString,genre);
                }
                myMap.clear();
                break loop;
            }
            else{
            update(myMap,name);
            delete(myMap,name);
            }
            counter--;
        }
        mylocker.notify();
        }
    }
    public static void writeIntoFile(String myString,String genre) throws IOException{
        switch(genre){
            case "Comedy":
                if(comedyCounter==0){
                comedy=new BufferedWriter(new FileWriter("Comedy.txt",true));
                comedyCounter++;}
                else{
                    comedy.write(myString);
                    comedy.newLine();
                    comedy.flush();
                }
                break;
            case "Thriller":
                if(ThrillerCounter==0){
                ThrillerBuff=new BufferedWriter(new FileWriter("Thriller.txt",true));
                ThrillerCounter++;
                }
                else{
                    ThrillerBuff.write(myString);
                    ThrillerBuff.newLine();
                    ThrillerBuff.flush();
                }
                break;
            case "Fantasy":
                if(SFCounter==0){
                    SF=new BufferedWriter(new FileWriter("Fantasy",true));
                    SFCounter++;
                }
                else{
                    SF.write(myString);
                    SF.newLine();
                    SF.flush();
                }
                break;
            case "Crime":
                if(CrimCounter==0){
                    Crime=new BufferedWriter(new FileWriter("Crime",true));
                }
                else{
                    Crime.write(myString);
                    SF.newLine();
                    SF.flush();
                }
                break;
        }
    }
    public static void delete(ConcurrentHashMap<Integer,Mov>comedy,String name){
        for(Map.Entry e:comedy.entrySet()){
           if(((Mov)e.getValue()).name.equals(name)){
               comedy.remove(e.getKey());

           }
        }

    }
    public static void update(ConcurrentHashMap<Integer,Mov>comedy,String name) throws SQLException{
        String raiting=DataBase.getInstance().getMovie(name);
        double amount=0;
        int split=0;
        String []my=raiting.split("/");
        int user=Integer.parseInt(my[0]);
        int note=Integer.parseInt(my[1]);
        for(Map.Entry e:comedy.entrySet()){
            if(((Mov)e.getValue()).name.equals(name)){
                amount+=((Mov)e.getValue()).value;
                split++;
            }
        }
       delete(comedy,name);
   
       amount/=split;
       user+=split;
       double a=(note+amount)/2;
      String result=Integer.toString(user)+"/"+Double.toString(a);
        DataBase.getInstance().updateRaiting(name, result);
        
    }
    public static void getValue(ConcurrentHashMap<String,Mov>comedy,String name){
        int amount=0;
        for(Map.Entry e:comedy.entrySet()){
            if(((Mov)e.getValue()).name.equals(name)){
                amount+=((Mov)e.getValue()).value;
            }
        }
       
    }
    public static void main(String[]args) throws SQLException, IOException, ClassNotFoundException, InterruptedException{
    }
}
