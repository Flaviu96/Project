




import java.util.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.validator.EmailValidator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dezibil
 */
public class MainThread implements Runnable{
    public static ObjectOutputStream myOutput;
    public static Object object1=new Object();
     public static boolean moreSec=false,finished=false,hasGUI=false,updateCode=false,firstTime,hasChat=false,firstLoop,sender,blocked;
     public static Object myObject;
     public static char alphabet[]={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    public int id;
    public static int id1;
    public  List<String>myList=new ArrayList<String>();
    public static List<Chat>myLst=new ArrayList<Chat>();
    public static String name,mycode,response;
    public static Command command;
    public static Command myCom=new Command();
    public Command myCommand;
    public static User myUser;
    public static int count=0,visible=0,isAdmin;
    final static Object monitor = new Object();
    private static CountDownLatch latch=new CountDownLatch(1);
    public MainThread(Command command,int id){
        this.command=command;
        this.id=id;
    }
    public MainThread(Command myCommand,int id,boolean status){
        this.myCommand=myCommand;
        this.id=id;
        
    }
    public MainThread(CountDownLatch count,int id){
        this.latch=count;
        this.id=id;
    }
    public MainThread(String response,int id){
        this.response=response;
        this.id=id;
    }
    public MainThread(int id){
        this.id=id;
    }
    public void run(){
        
        try{
            if(count==0){
            myOutput=new ObjectOutputStream(InputThread.socket.getOutputStream());}
            count++;
            switch(id){
                case 1:getAccount();
                    break;
                case 2:cas3(command);
                    break;
                case 3:
                    addtoFriendList(command);
                    break;
                case 4:
                    addToBlockList(command);
                    break;
                case 5:
                    getCode();
                    break;
                case 6:
                    synchronized(object1){
                        object1.notify();
                    }
                    break;
                case 7:
                    showjDialog1();
                    break;
                case 8:
                    stop();
                    break;
                case 9:
                    register();
                    break;
                case 10:
                    register1(command);
                    break;
                case 11:
                    ProfileInfo1();
                    break;
                case 12:
                    ProfileInfo((User)command.account);
                    break;
                case 13:
                    updateCode();
                    break;
                case 14:
                    changePassword();
                    break;
                case 15:
                    backToProfileInfo();
                    break;
                case 16:
                    changePassword(command);
                    break;
                case 17:
                    generateCode1();
                    break;
                case 18:
                    intoRequestList(command);
                    break;
                case 19:
                    showUsername();
                    break;
                case 20:
                    sendRequest();
                    break;
                case 21:
                    add(command);
                    break;
                case 22:
                    sendResponse(response);
                    break;
                case 23:
                    getRequests();
                    break;
                case 24:
                    sendPM();   
                    break;
                case 25:
                    sendPM1(command);
                    break;
                case 26:
                    Chat();
                    break;
                case 27:
                    awake();
                    break;
                case 28:
                    receivePM(command);
                    break;
                case 29:
                    setSender(command);
                    break;
                case 30:
                    changeId(myCommand);
                    break;
                case 31:
                    blockCurrentPerson();
                    break;
                case 32:
                    blockChat(command);
                    break;
                case 33:
                    Action();
                    break;
                case 34:
                    addMovies(command);
                    break;
                case 35:
                    getGenre();
                    break;
                case 36:
                    getVote();
                    break;
                case 37:
                    setInvisible();
                    break;
                case 38:
                    setVisible();
                    break;
                case 39:
                    getVote();
                    break;
                case 40:
                    createJDialog(command);
                    break;
                case 41:
                    myOutput.writeObject(new Command(new Operation(17)));
                    break;
                case 42:
                    showDetails();
                    break;
            }
        }catch(Exception e){
            
        }
        
    }
    public void showUsername(){
        String welcome = new String("Welcome, ");
        
        for(int i=0;i<name.length();i++){
           welcome+=Character.toString(name.charAt(i));
           if(isAdmin==1){
               AdminGUI.jLabel2.setText(welcome);
           }else{
           GUI0.jLabel5.setText(welcome);}
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    ////////////////////////////////////////////////////Login
     public void verifyAccount(String username,String password,javax.swing.JLabel label,String message, Command command) throws IOException{
        if((username.length()==0 && password.length()==0 )||(username.length()==0 || password.length()==0)){
            label.setText(message);
        }
        else{
            myOutput.writeObject(command);
            
        }
    }
      public void register1(Command command){
        if(command.email==false &&command.username==false){
            finished=true;
            moreSec=true;
            Login.jLabel17.setText("Your account has been created");
            Login.jPanel12.setVisible(false);
        }else{
            if(command.email==true && command.email==true){
                System.out.println("Both of them exists");
            }
            else{
                if(command.email==true){
                    Login.jLabel17.setText("The email exist already");
                }
                if(command.username==true){
                    Login.jLabel17.setText("The username is used");
                }
            }
        }
    }
     public void stop() throws IOException{
         myOutput.writeObject(new Command(mycode,true,new Operation(3)));
     }
       public void cas3(Command command){
       switch(command.status){
           case "ok":
                MainThread.name=command.username1;
               if(command.account instanceof User){
                   GUI0 myGui=new GUI0();
                   this.id1=command.account.getID();
                    MyThreadPool.getInstance().get().execute(new MainThread(command,4));
                    MyThreadPool.getInstance().get().execute(new MainThread(command,5));
                   
               }
               else{
                  Login.myLogin.dispose();
                   AdminGUI admingui=new AdminGUI();
                   this.id1=command.operation.getID1();
                   isAdmin=1;
                //  MyThreadPool.getInstance().get().execute(new MainThread(command,4));
                 // MyThreadPool.getInstance().get().execute(new MainThread(command,5));
               }
               break;
           case "wrong":
               Login.jLabel6.setText("Wrong info");
               break;
           case "ban":
               Login.jLabel6.setText("Banned until "+command.date);
               break;
       }        
    }
      public void getAccount() throws IOException{
        String username=Login.jTextField1.getText();
        String password=Login.jPasswordField1.getText();
        verifyAccount(username,password,Login.jLabel6,"Fill the fields",new Command(new User(username,password),new Operation(1)));  
    }
      public static void getCode() throws IOException, InterruptedException{
        String code=Login.jTextField2.getText();
        if(code.length()==0){
            
        }
        else{
            myOutput.writeObject(new Command(new User(code),new Operation(2)));
            synchronized(object1){
                object1.wait();
                mycode=code;
                getCode1(command.status);
                
                
            }
        }
    }
      public static void getCode1(String response) throws InterruptedException, IOException{
        switch(response){
            case "ok":
                showJDialog1();
                break;
            case "wrong":
                Login.jLabel16.setText("Wrong");
                break;
            case "used":
                Login.jLabel16.setText("Used");
                break;
        }
    }
      public static void showJDialog1() throws InterruptedException, IOException{
       Login.jDialog1.dispose();
       Login.jDialog2.setSize(450, 500);
       Login.jDialog2.show();
       countingDown();
    }
      public static void countingDown() throws InterruptedException, IOException{
        int count=9;
        Login.jLabel10.setText(Integer.toString(count));
        Login.jLabel11.setText("60");
        while(count>=0 && !finished){
            for(int i=60;i>=0 && !finished;i--){
                Thread.sleep(1000);
                Login.jLabel11.setText(Integer.toString(i));
            }
            count--;
            Login.jLabel10.setText(Integer.toString(count));
        }
        if(moreSec==true){
            for(int i=15;i>=0;i--){
                Thread.sleep(1000);
            }
        }
        hasGUI=false;
        Login.jDialog2.dispose();
    }
      public void showjDialog1(){
        if(!hasGUI){
            Login.jDialog1.setSize(450, 230);
            Login.jDialog1.show();
            hasGUI=true;
        }
            
    }
      public void register() throws IOException{
        String username=Login.jTextField3.getText();
        String password=Login.jTextField4.getText();
        String email=Login.jTextField5.getText();
        EmailValidator validator = EmailValidator.getInstance();
        if(validator.isValid(email)){
            User myUser=new User();
            myUser.setEmail(email);
            myUser.setUsername(username);
            myUser.password=password;
            myUser.code=mycode;
            verifyAccount(username,password,Login.jLabel17,"Fill the fields",new Command(myUser,new Operation(4)));
            Login.jTextField3.setText("");
            Login.jTextField4.setText("");
            Login.jTextField5.setText("");
        }else{
            Login.jLabel17.setText("The email isn't valide");
        }
    }
      ///////////////////////////////////////////////////////////////////////////////////////
       public void addtoFriendList(Command command){
             for(Account account:command.myList0){
            GUI0.myModel.addElement(account);
        }
        
    }
    public void addToBlockList(Command command){
            for(Account account:command.myList1){
                GUI0.myModel2.addElement(account.getUsername());
            }
        
    }
      //////////////////////////////////////////////////////////////////////////////////////////
     public void ProfileInfo1() throws IOException{
        User myUser=new User();
        myUser.setUsername(name);
        myOutput.writeObject(new Command(myUser,new Operation(10)));
    }
      public void ProfileInfo(User account){
        if(updateCode==true){
            myUser.setCode("");
        }
        myUser=account;
        ProfileInfo myProfileInfo=new ProfileInfo();
        ProfileInfo.jTextField1.setText(name);
        ProfileInfo.jTextField2.setText(((User)account).getEmail());
        if(account.invites>0){
            ProfileInfo.jLabel3.setText("Invites: "+account.getInvites());
        }
        else{
            if(myUser.code.equals("")){
                ProfileInfo.jLabel3.setText("Nothing");
                ProfileInfo.jTextField3.setVisible(false);
            }
            else{
            ProfileInfo.jLabel3.setText("The code:"+account.getCode());}
        }
    }
      public void updateCode(){
          
          ProfileInfo.jLabel3.setText("Nothing");
          ProfileInfo.jTextField3.setVisible(false);
          updateCode=true;
      }
      ////////////////////////////////////////////////////////////////////////////////////////////////////////
      public void sendRequest() throws IOException{
        String target=GUI0.jTextField1.getText();
        if(target.equals(name)){
            GUI0.jTextField1.setText("");
        }else{
        if(!firstTime){
            for(int i=0;i<GUI0.myModel.getSize();i++){
                myList.add(GUI0.myModel.get(i).toString());
            }
            firstTime=true;
            if(searchingIntoFriendList(target)){
                GUI0.jTextField1.setText("");
            }
            else{
                User newUser=new User();
                newUser.setUsername(name);
                newUser.target=target;
                newUser.id1=id1;
                myOutput.writeObject(new Command(newUser,new Operation(6)));
                GUI0.jTextField1.setText(Integer.toString(newUser.id1));

            }
        }
        else{
            if(searchingIntoFriendList(target)){
                GUI0.jTextField1.setText(""); 
                System.out.println("You have already this name");
            }
            else{
                User newUser=new User();
                newUser.setUsername(name);
                newUser.target=target;
                newUser.id1=id1;
                myOutput.writeObject(new Command(newUser,new Operation(6)));
                GUI0.jTextField1.setText(Integer.toString(newUser.id1));

            }
        }}
    }
       public void sendResponse(String response) throws IOException{
        int myId= GUI0.jList2.getSelectedIndex();
        User d=(User) GUI0.jList2.getSelectedValue();
       String target=GUI0.myModel1.getElementAt(myId).toString();
       User newUser=new User();
       newUser.setUsername(name);
       newUser.target=target;
       newUser.id1=id1;
       newUser.id=d.id1;
        switch(response){
            case "Yes":      
                myOutput.writeObject(new Command("yes",newUser,new Operation(7)));
                GUI0.myModel1.remove(myId);
                GUI0.myModel.addElement(new User());
                firstTime=false;
                break;
            case "No":
                myOutput.writeObject(new Command("no",newUser,new Operation(7)));
                GUI0.myModel1.remove(myId);
                break;
        }
    }
       public void changeId(Command command) throws InterruptedException{
           
          if(sender==true){
         synchronized(monitor){
             monitor.wait();
           for(Chat chat:myLst){

               if(chat.getTitle().equals(((User)command.account).username)){
                   
                   chat.jLabel5.setText(Integer.toString(command.account.id));
               }
           }
           for(int i=0;i<GUI0.myModel.size();i++){
              String name=GUI0.myModel.getElementAt(i).toString();
              if(name.equals(command.account.username)){
                  GUI0.myModel.remove(i);
                  GUI0.myModel.addElement(command.account);
                  break;
              }
          }
       }}
          else{
              if(myLst.isEmpty()){
                  System.out.println("Ii empty");
              }
            for(Chat chat:myLst){
               if(chat.getTitle().equals(((User)command.account).username)){
                   
                   chat.jLabel5.setText(Integer.toString(command.account.id));
                   
               }
           }
           for(int i=0;i<GUI0.myModel.size();i++){
              String name=GUI0.myModel.getElementAt(i).toString();
              if(name.equals(command.account.username)){
                  GUI0.myModel.remove(i);
                  GUI0.myModel.addElement(command.account);
                  break;
              }
          }  
          }}
       
       public void setSender(Command command){
          if(command.status1==true){
           sender=true;
       }
       }
       public void getRequests() throws IOException{
           User myUser=new User();
           myUser.setUsername(name);
           myOutput.writeObject(new Command(myUser,new Operation(8)));
       }
       public void add(Command command){
           for(Account myUser:command.myList0){
               GUI0.myModel1.addElement(myUser);
           }
       }
      public void intoRequestList(Command command){
          GUI0.myModel1.addElement(command.account);
          int counter=Integer.parseInt(GUI0.jLabel4.getText());
          counter++;
          GUI0.jLabel4.setText(Integer.toString(counter));
      }
      public boolean searchingIntoFriendList(String target){
        boolean found=false;
        for(String e:myList){
            if(e.equals(target)){
                found=true;
                break;
            }
        }
        return found;
    }
      ///////////////////////////////////////////////////////////////////////////////////
      public void changePassword() throws IOException{
       String oldPassword=ProfileInfo.jPasswordField1.getText();
       String newPassword=ProfileInfo.jPasswordField2.getText();
       if((oldPassword.length()==0 && newPassword.length()==0 )||(oldPassword.length()==0||newPassword.length()==0)){
           
       }
       else{
           int result=(newPassword.length()<5)?1:0;
           ProfileInfo.jPasswordField1.setText("");
           ProfileInfo.jPasswordField2.setText("");
           switch(result){
               case 0:
                   Account myAccount=new User();
                   myAccount.setOldPassword(oldPassword);
                   myAccount.setNewPassword(newPassword);
                   myAccount.setUsername(name);
                   myOutput.writeObject(new Command(myAccount,new Operation(9)));
                   break;
               case 1:
                   ProfileInfo.jLabel10.setText("The password must be longer");
                   break;
           }
       }
    }
      public void changePassword(Command command){
        if(command.status1==true){
            ProfileInfo.jLabel10.setText("Nice");
        }
        else{
            ProfileInfo.jLabel10.setText("Mda");
        }
    }
      public void backToProfileInfo(){
          ProfileInfo.jDialog1.dispose();
          ProfileInfo(myUser);
      }
      public void sendPM() throws IOException{
        int i=GUI0.jList1.getSelectedIndex();
        String target=GUI0.myModel.get(i).toString();
        User myAccount=new User();
        myAccount.setUsername(name);
        myAccount.setTarget(target);
        myOutput.writeObject(new Command(myAccount,new Operation(12)));
    }
      public void blockChat(Command command)throws IOException{
          String result;
          for(Chat myChat:myLst){
              if(myChat.getTitle().equals(command.account.username)){
                  myChat.jPanel3.setVisible(false);
                  myChat.jTextArea1.append("\n");
                  myChat.jTextArea1.append("BLLOOOOOOOCKK");
                  break;
              }
          }
          for(int i=0;i<GUI0.myModel.size();i++){
              result=GUI0.myModel.get(i).toString();
              if(result.equals(command.account.username)){
                  GUI0.myModel.remove(i);
                  break;
              }
          }
      }
      public void Chat() throws InterruptedException, IOException{
          int counter=0;
        myObject=new Object();
        while(!firstLoop){
            synchronized(myObject){
                myObject.wait();
                String message=Chat.jTextField2.getText();
                Chat.jTextField2.setText("");
                Chat.jTextArea1.append("\n");
                Chat.jTextArea1.append(name+message);
                Command myCommand=new Command();
                myCommand.setMessage(message);
                myCommand.setOperation(new Operation(13));
                myCommand.username1=name;
                myCommand.status1=sender;
                User myUser1=new User();
                myUser1.setId(1);
                myCommand.account=myUser1;
                if(sender==true){
                    myOutput.writeObject(myCommand);
                }
                else{
                 User myUser=new User();
                 myUser.target=Chat.jLabel3.getText();
                 myUser.username=name;
                 myCommand.account=myUser;
                 myOutput.writeObject(myCommand);
                }
                
               
            }
        }
    }
      public void awake(){
          synchronized(myObject){
              myObject.notify();
          }
      }
      public void sendPM1(Command command){
        if(command.status1==false){
            System.out.println("Nu ii on");
        }
        else{
            Chat d=new Chat();
            myLst.add(d);
            hasChat=true;
            d.setTitle(((User)command.account).target);
            Chat.jLabel1.setText("To:");
            Chat.jLabel3.setText(((User)command.account).target);
            synchronized(monitor){
                monitor.notify();
            }}
        
    }
      public void blockUser(){
          String nume=Chat.jLabel3.getText();
          String jList;
          for(int i=0;i<GUI0.myModel.size();i++){
              jList=GUI0.myModel.getElementAt(i).toString();
              if(jList.equals(nume)){
                  GUI0.myModel.removeElement(jList);
                  break;
              }
          }
          GUI0.myModel2.addElement(nume);
      }
       public void receivePM(Command command) throws IOException{
        if(!hasChat && !blocked){
            Chat myChat=new Chat();
            Chat.jLabel1.setText("To");
            Chat.jLabel3.setText(command.username1);
            Chat.jTextArea1.append(command.username1+":"+command.message);
            myChat.setTitle(command.username1);
            myLst.add(myChat);
            hasChat=true;
        }
        else if(!blocked){
            Chat.jTextArea1.append("\n");
            Chat.jTextArea1.append(Chat.jLabel3.getText()+":"+command.message);
        }
        else{
                myOutput.writeObject(new Command(true,new Operation(14)));
        }
    }
       public void blockCurrentPerson() throws IOException{
           int counter=0;
           String anotherString;
           String target=Chat.jLabel3.getText();
           User myUser=new User();
           myUser.target=target;
           for(;counter<GUI0.myModel.size();counter++){
               anotherString=GUI0.myModel.get(counter).toString();
               if(anotherString.equals(target)){
                   myUser=(User)GUI0.myModel.get(counter);
                   GUI0.myModel.remove(counter);
                   break;
               }
       }
           myUser.target=target;
           myUser.username=name;
           myUser.id1=Integer.parseInt(Chat.jLabel5.getText());
           myOutput.writeObject(new Command(myUser,new Operation(14,id1)));
       }
       
       public void getGenre() throws IOException{
           GUI0.jDialog2.dispose();
           String genre="Comedy";
           GUI0.jPanel12.setVisible(false);
           myOutput.writeObject(new Command(genre,new Operation(15)));
       }
       public void addMovies(Command command){
           MGUI mgui=new MGUI();
           for(Movie movie:command.myList2){
               MGUI.myModel.addElement(movie);
           }
       }
       public void Action(){
           MGUI.jLabel5.setVisible(true);
           if(visible==0){
               setVisible();
               visible++;
           }
           MGUI.jTextArea1.selectAll();
           MGUI.jTextArea1.replaceSelection("");
           Movie movie=MGUI.jList1.getSelectedValue();
           String metascore=Integer.toString(movie.metascore);
           MGUI.jLabel1.setText(movie.name);
           MGUI.jLabel2.setText(metascore);
           MGUI.jLabel3.setText(Integer.toString(movie.year));
           MGUI.jLabel4.setText(movie.genre);
           MGUI.jLabel12.setText("MAAAAA");
           
           if(movie.counterVote!=0){
              MGUI.jLabel5.setVisible(false);
           }
           for(String myString:movie.comments){
               MGUI.jTextArea1.append(myString);
               MGUI.jTextArea1.append("\n");
           }
       }
       public void setInvisible(){
           MGUI.jPanel7.setVisible(false);
           MGUI.jLabel5.setVisible(false);
           MGUI.jLabel6.setVisible(false);
           MGUI.jLabel7.setVisible(false);
           MGUI.jLabel8.setVisible(false);
           MGUI.jLabel9.setVisible(false);
           MGUI.jLabel10.setVisible(false);
           MGUI.jLabel11.setVisible(false);
           MGUI.jPanel3.setVisible(false);
           MGUI.jPanel4.setVisible(false);
           MGUI.jPanel5.setVisible(false);
           MGUI.jPanel6.setVisible(false);
         
           
       }
       public void setVisible(){
           MGUI.jPanel7.setVisible(true);
           MGUI.jLabel5.setVisible(true);
           MGUI.jLabel6.setVisible(true);
           MGUI.jLabel7.setVisible(true);
           MGUI.jLabel8.setVisible(true);
           MGUI.jLabel9.setVisible(true);
           MGUI.jLabel10.setVisible(true);
           MGUI.jLabel11.setVisible(true);
           MGUI.jPanel3.setVisible(true);
           MGUI.jPanel4.setVisible(true);
           MGUI.jPanel5.setVisible(true);
           MGUI.jPanel6.setVisible(true);
      
       }
       public void getVote() throws IOException{
          if(Character.isLetter(MGUI.jTextField1.getText().charAt(0))){
              MGUI.jTextField1.setText("Only numbers");
          }else{
              int score=Integer.parseInt(MGUI.jTextField1.getText());
              if(score>=0 && score<=10){
                  Movie name=MGUI.jList1.getSelectedValue();
                  name.setCounterVote(1);
                  MGUI.myModel.remove(MGUI.jList1.getSelectedIndex());
                  MGUI.myModel.addElement(name);
                  MGUI.jLabel5.setVisible(false);
                  MGUI.jTextField1.setText("");
                  Command myCommand=new Command();
                  Movie anotherMovie=new Movie(name.name,score,name.genre);
                  myCommand.movie=anotherMovie;
                  myCommand.setOperation(new Operation(16));
                  myOutput.writeObject(myCommand);
              }
              else{
                  MGUI.jTextField1.setText("From 1 to 10");
              }
          }
       }
       public void addYourComm(){
           String comm=MGUI.jTextField2.getText();
           if(comm.length()>15){
               MGUI.jTextField2.setText("It's greater than 15 characters");
          //     myOutput.writeObject(new Command());
           }
           else{
               MGUI.jTextArea1.append(comm);
           }
       }
       public void getNote(){
           int score=Integer.parseInt(MGUI.jTextField1.getText());
           if(score>=0 && score<=10){
               Movie name=MGUI.jList1.getSelectedValue();
               name.setCounterVote(1);
               MGUI.myModel.remove(MGUI.jList1.getSelectedIndex());
               MGUI.myModel.addElement(name);
              
           }
       }
      ///////////////////////////////////////////////////// code
       public String generateCode(){
        Random random=new Random();
        StringBuilder myStringBuilder=new StringBuilder();
        int randomNumber;
        for(int i=0;i<14;i++){
           randomNumber=random.nextInt(25);
           myStringBuilder.append(alphabet[randomNumber]);
        }
        return myStringBuilder.toString();
    }
    public String findDuplicates(String string){
        int count[]=new int[255];
        Random random=new Random();
        StringBuilder myString=new StringBuilder();
        char anotherChar;
        for(int i=0;i<255;i++){
            count[i]=0;
        }
        for(int i=0;i<string.length();i++){
            char myChar=string.charAt(i);
            ++count[myChar];
            if(count[myChar]==1){
                myString.append(myChar);
            }
            if(count[myChar]==2){
                anotherChar=alphabet[random.nextInt(24)];
                if(anotherChar!=myChar){
                    if(random.nextInt(99)>50){
                        myString.append(Character.toUpperCase(anotherChar));
                    }
                    else{
                        myString.append(anotherChar);
                    }
                }
            }
        }
        return myString.toString();
    }
    public void generateCode1() throws IOException{
      String text= ProfileInfo.jLabel3.getText();
      text=text.substring(text.length()-1, text.length());
      if(text.equals("1")){
          String code=findDuplicates(generateCode());
          ProfileInfo.jLabel3.setText("The code");
          ProfileInfo.jTextField3.setText(code);
          Account myAccount=new User();
          myAccount.setUsername(name);
          myAccount.setCode(code);
          myOutput.writeObject(new Command(myAccount,new Operation(11)));
      }
    }
    ////////////////////////////////////////////////////////////////////////////
    /* ADMIIIIIIIIIIIIIIIINNNNNNNNNNNNN*/
    public void createJDialog(Command command){
        AdminGUI.jDialog1.setSize(450,350);
        AdminGUI.jDialog1.show();
        myCom.myList0=command.myList0;
       for(Account acc:command.myList0){
            AdminGUI.model.addRow(new Object[]{acc.getUsername()});
        }
    }
    public void showDetails(){
        int i=AdminGUI.jTable1.getSelectedRow();
        name=AdminGUI.model.getValueAt(i, 0).toString();
        for(Account account:myCom.myList0){
            if(account.username.equals(name)){
                AdminGUI.jDialog2.setSize(500,450);
                AdminGUI.jDialog2.show();
                AdminGUI.jTextField2.setText(account.username);
            }
        }
    }
}
