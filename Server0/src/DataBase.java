
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class DataBase {
 private static DataBase instance;
 public User d=new User();
       public int counter=0;
	public Connection con;
        public static boolean authentification=false;
        public int movies=0;
    public static DataBase getInstance() throws SQLException {
		if(instance == null) 
			instance = new DataBase();
		return instance;
	}
	public void Connection(){
		String dbUrl="jdbc:mysql://localhost:3306/project";
		String username="root";
		String pass="root";
		try{
			con=(Connection) DriverManager.getConnection(dbUrl, username, pass);
			if(con!=null){
				System.out.println("Connected");
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
        public Account authentification(Account account) throws SQLException{
            Account myAccount=null;
            String sql="Select Username,Password,isAdmin,Date,Id from accounts where Username=? and Password=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1, account.getUsername());
            st.setString(2, account.getPassword());
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                String date=rs.getString("Date");
                String username=rs.getString("Username");
                String password=rs.getString("Password");
                int isAdmin=rs.getInt("isAdmin");
                int Id=rs.getInt("Id");
                myAccount=(isAdmin==1)?new Admin(username,password,date,isAdmin,Id):new User(username,password,date,isAdmin,Id);
                
            }
            return myAccount;
        }
        public void updateCode(Account account)throws SQLException{
            String sql="update accounts set Code='"+account.code+"' where Username='"+account.getUsername()+"'";
            String sql1="update accounts set Invites=0 where Username='"+account.getUsername()+"'";
            Statement st=con.createStatement();
            con.setAutoCommit(false);
            st.addBatch(sql);
            st.addBatch(sql1);
            st.executeBatch();
            con.commit();
        }
        public String getMovie(String name) throws SQLException{
            String userRaiting=null;
            String sql="Select userRating from movies where Name=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,name);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                userRaiting=rs.getString("userRating");
            }
            return userRaiting;
            
        }
        public Account getProfile(Account account)throws SQLException{
            User myUser=new User();
            String sql="Select Email,Invites,Code from accounts where Username=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1, account.getUsername());
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                myUser.setEmail(rs.getString("Email"));
                myUser.setInvites(rs.getInt("Invites"));
                myUser.setCode(rs.getString("Code"));
            }
            return myUser;
        }
        public ArrayList<Account> getRequests(Account account)throws SQLException{
            ArrayList<Account>myList=new ArrayList<Account>();
            String firstSql="Select Requests from accounts where Username=?";
            String secondSql="Select Username,Id from accounts where Id=?";
            PreparedStatement st=con.prepareStatement(firstSql);
            st.setString(1, account.getUsername());
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                String req=rs.getString("Requests");
                if(req.equals("")){
                    
                }else{
                String [] myArray=req.split("/");
                st.close();
                for(String myString:myArray){
                    st=con.prepareStatement(secondSql);
                    st.setInt(1, Integer.parseInt(myString));
                    ResultSet rs1=st.executeQuery();
                    if(rs1.next()){
                        String name=rs1.getString("Username");
                        int anotherId=rs1.getInt("Id");
                        myList.add(new User(name,anotherId));
                    }
                }
            }}
            return myList;
        }

        public void register(User account) throws SQLException{
            String sql="Insert into accounts(Username,Password,Invites,Date,Email,isAdmin,LoggedIn,lastLogged)values(?,?,?,?,?,?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1, account.getUsername());
            st.setString(2, account.getPassword());
            st.setInt(3, 1);
            st.setString(4, "");
            st.setString(5, account.getEmail());
            st.setInt(6, 0);
            st.setInt(7, 0);
            st.setString(8, "");
            st.execute();
            st.close();
        }
        public String updateCode(String code)throws SQLException{
            String username=null;
            String sql="update accounts set Code=? where Code=?";
            String sql1="Select Username from accounts where Code=?";
            try(PreparedStatement st1=con.prepareStatement(sql1)){
         st1.setString(1, code);
         ResultSet rs=st1.executeQuery();
         if(rs.next()){
             username=rs.getString("Username");
         }
     }
     try (PreparedStatement st = con.prepareStatement(sql)) {
         st.setString(1, "");
         st.setString(2, code);
         st.executeUpdate();
         
     }
     return username;
        }


         public ArrayList<Account> getFriends(Account account)throws SQLException{
            ArrayList<Account>myFriends=null;
            String friends=null;
            String sql="Select Friends from accounts where Username=?";
            String sql1="Select Username from accounts where Id=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1, account.getUsername());
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                myFriends=new ArrayList<Account>();
                 friends=rs.getString("Friends");
                 st.close();
                 if(friends!=null){
                 String ar[]=friends.split("/");
                 for(String id:ar){
                     st=con.prepareStatement(sql1);
                     st.setInt(1, Integer.parseInt(id));
                     ResultSet rs1=st.executeQuery();
                     if(rs1.next()){
                         String name=rs1.getString("Username");
                         //int id1=rs1.getInt("Id");
                         myFriends.add(new User(name,Integer.parseInt(id)));
                     }
                 }
            }
                     
                 }
            return myFriends;
        }
         public boolean getPassword(Account account)throws SQLException{
             boolean hasPassword=false;
             String sql="Select Password from accounts where Username=?";
             PreparedStatement st=con.prepareStatement(sql);
             st.setString(1, account.getUsername());
             ResultSet rs=st.executeQuery();
             if(rs.next()){
                 hasPassword=true;
             }
             return hasPassword;
             
         }
         public ArrayList<Movie> getMovies(String genre)throws SQLException{
             List<Movie>myList=new ArrayList<Movie>(); 
             String name,genre1;
             int metascore,year;
             String userRating;
             String sql="Select Name,Genre,Metascore,Year,Comments,userRating from movies where Genre=?";
             PreparedStatement st=con.prepareStatement(sql);
             st.setString(1, genre);
             ResultSet rs=st.executeQuery();
             Blob blob=null;
             while(rs.next()){
                 name=rs.getString("Name");
                 metascore=rs.getInt("Metascore");
                 year=rs.getInt("Year");
                 blob=rs.getBlob("Comments");
                 userRating=rs.getString("userRating");
                 myList.add(new Movie(name,genre,metascore,year,blob,userRating));
             }
             return (ArrayList<Movie>) myList;
         }
         public ArrayList<Account> getAllUsers()throws SQLException{
             ArrayList<Account>myList=new ArrayList<Account>();
             String sql="Select Username,Invites,Email,Date from accounts";
             PreparedStatement st=con.prepareStatement(sql);
             ResultSet rs=st.executeQuery();
             while(rs.next()){
                 User myUser=new User();
                 myUser.username=rs.getString("Username");
                 myUser.invites=rs.getInt("Invites");
                 myUser.email=rs.getString("Email");
                 myUser.date=rs.getString("Date");
                 myList.add(myUser);
             }
             return  myList;
         }
         public void updateFile(Command command, FileInputStream file) throws SQLException{
            String sql="UPDATE movies set Comments=? WHERE Name=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setBlob(1, file);
            st.setString(2, "Yas");
            st.executeUpdate();
        }
         public void delete() throws SQLException{
             String sql="delete from movies where Name=?";
             PreparedStatement st=con.prepareStatement(sql);
             st.setString(1, "Yuda");
             st.executeUpdate();
         }
         public Blob getFile(String string) throws SQLException, IOException, ClassNotFoundException{
            String sql="Select TEST from accounts where Username=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1, string);
            ResultSet rs=st.executeQuery();
            Blob blob=null;
            while(rs.next()){
               blob=rs.getBlob("TEST");
          
            }
           return blob;    
        }
         public boolean updatePassword(Account account)throws SQLException{
             //boolean result=false;
             if(getPassword(account)==true){
             String sql="update accounts set Password=? where Username=?";
             PreparedStatement st=con.prepareStatement(sql);
             st.setString(1, account.getPassword());
             st.setString(2, account.getUsername());
             st.executeUpdate();
             return true;}
             else{
                 return false;
             }
         }
         public Command getRequestsAndGetQue(User account)throws SQLException{
            String requests=null;
            String que=null;
            Command command=null;
            String sql="Select Requests from accounts where Username=?";
            String sql1="Select Queue from accounts where Username=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1, account.target);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                requests=rs.getString("Requests");
                st.close();
            }
            st=con.prepareStatement(sql1);
            st.setString(1, account.username);
            ResultSet rs1=st.executeQuery();
            if(rs1.next()){
                que=rs1.getString("Queue");
                st.close();
            }
            command=new Command(requests,que);
            return command;
        }
         public void updateBatch(String stirn,String string) throws SQLException{
             String sql1="Update accounts set Requests=? where Username=?";
             PreparedStatement st=con.prepareStatement(sql1);  
             con.setAutoCommit(false); 
             st.setString(1, stirn);
             st.setString(2, string);
             st.addBatch();
             String sql="Update accounts set Queue=? where Username=?";
             st.setString(1, stirn);
             st.setString(2, string);
             st.addBatch();
             st.executeBatch();
             con.commit();
             
         }
         public void updateQueAndFriendList(String que, String req,String firstName,String secondName)throws SQLException{
            Statement st=con.createStatement();
            con.setAutoCommit(false);
             String sql="Update accounts set Requests='"+req+"' where Username='"+firstName+"'";
             st.addBatch(sql);
              String sql1="Update accounts set Queue='"+que+"'where Username='"+secondName+"'";
              st.addBatch(sql1);
              st.executeBatch();
              con.commit();
              
             
         }
         public void updateRaiting(String name,String raiting) throws SQLException{
             String sql="update movies set userRating=? where Name=?";
             PreparedStatement st=con.prepareStatement(sql);
             st.setString(1,raiting);
             st.setString(2, name);
             st.executeUpdate();
         }
         public Command friendMethod(User account)throws SQLException{
             String que=null;
             String req=null;
             int counter=0;
             String sql="Select Queue from accounts where Username=?";
             String sql1="Select Requests from accounts where Username=?";
             ArrayList<String>myList=new ArrayList<>();
             myList.add(sql);
             myList.add(sql1);
             PreparedStatement st=null;
             for(String e:myList){
                 switch(counter){
                     case 0:
                         st=con.prepareStatement(e);
                         st.setString(1, account.target);
                         ResultSet rs=st.executeQuery();
                         if(rs.next()){
                             que=rs.getString("Queue");
                         }
                         break;
                     case 1:
                         st=con.prepareStatement(sql1);
                         st.setString(1, account.username);
                         ResultSet rs1=st.executeQuery();
                         if(rs1.next()){
                             req=rs1.getString("Requests");
                         }
                         break;
                 }
                 counter++;
             }
             return new Command(req,que);
         }
         public int getId(String account)throws SQLException{
             int getId=0;
             String sql1="Select Id from accounts where Username=?";
             PreparedStatement st=con.prepareStatement(sql1);
             st.setString(1, account);
             ResultSet rs=st.executeQuery();
             if(rs.next()){
                 getId=rs.getInt("Id");
             }
             return getId;
         }
         public void updateRequests(String account,String string)throws SQLException{
             String sql="Update accounts set Requests=? where Username=?";
             PreparedStatement st=con.prepareStatement(sql);
             st.setString(1, string);
             st.setString(2, account);
             st.executeUpdate();
         }
         public ArrayList<Account>getBlockList(Account account)throws SQLException{
             ArrayList<Account>blockList=null;
             String block=null;
             String sql="Select BlockList from accounts where Username=?";
             String sql1="Select Username from accounts where Id=?";
             PreparedStatement st=con.prepareStatement(sql);
             st.setString(1, account.getUsername());
             ResultSet rs=st.executeQuery();
             if(rs.next()){
                blockList=new ArrayList<Account>();
                 block=rs.getString("BlockList");
                 st.close();
                 if(block!=null){
                    String ar[]=block.split("/");
                 for(String id:ar){
                     st=con.prepareStatement(sql1);
                     st.setInt(1, Integer.parseInt(id));
                     ResultSet rs1=st.executeQuery();
                     if(rs1.next()){
                         String name=rs1.getString("Username");
                         blockList.add(d.myFunction(name, 1));
                     }
                 }
             }
                 }
             return blockList;
         }
        public Command UsernameAndEmail(User account)throws SQLException{
            boolean username=false, email=false;
            String firstSql="Select Username from accounts where Username=?";
            String secondSql="Select Email from accounts where Email=?";
            PreparedStatement st=con.prepareStatement(firstSql);
            st.setString(1, account.getUsername());
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                username=true;
            }
            st=con.prepareStatement(secondSql);
            st.setString(1, account.getEmail());
            rs=st.executeQuery();
            if(rs.next()){
                email=true;
            }
            return new Command(username,email);
            
        }
        public void updateRequestsAndQueue(String firstName,String secondName,String que,String requests) throws SQLException{
            Statement stmt=con.createStatement();
            con.setAutoCommit(false);
            String sql="update accounts set Queue='"+que+"' where Username='"+firstName+"'";
            stmt.addBatch(sql);
           String sql1="update accounts set Requests='"+requests+"' where Username='"+secondName+"'";
            stmt.addBatch(sql1);
            stmt.executeBatch();
            con.commit();
        }
        public void updateFriendListBatch(String firstName,String secondName,String que,String requests)throws SQLException{
            Statement st=con.createStatement();
            con.setAutoCommit(false);
            String sql="update accounts set Friends='"+que+"' where Username='"+firstName+"'";
           st.addBatch(sql);
           String sql1="update account set Friends='"+que+"where Username='"+secondName+"'";
           st.addBatch(sql1);
           st.executeBatch();
           con.commit();
            
        }
        public String checkCode(String code)throws SQLException{
            String code1=null;
            String sql="select Code from accounts where Code=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1, code);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                code1=rs.getString("Code");
            }
            return code1;
        }
        public void updateFriends(String list,String list1,String username,String username1)throws SQLException{
            Statement st=con.createStatement();
            con.setAutoCommit(false);
            String sql="Update accounts set Friends='"+list+"'where Username='"+username+"'";
            st.addBatch(sql);
            String sql1="Update accounts set Friends='"+list1+"'where Username='"+username1+"'";
            st.addBatch(sql1);
            st.executeBatch();
            con.commit();
        }
        public String getFriends(String username)throws SQLException{
            String result=null;
            String sql="Select Friends from accounts where Username=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                result=rs.getString("Friends");
            }
            return result;
            
        }
        
        public String getBlock(String username)throws SQLException{
            String result=null;
            String sql="Select BlockList from accounts where Username=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
                result=rs.getString("BlockList");
            }
            return result;
        }
        public void updateBlockList(String username,String blockList,String secondUsername,String friends)throws SQLException{
            Statement st=con.createStatement();
            con.setAutoCommit(false);
            String sql="update accounts set BlockList='"+blockList+"' where Username='"+username+"'";
           st.addBatch(sql);
           String sql1="update accounts set Friends='"+friends+"' where Username='"+secondUsername+"'";
           st.addBatch(sql1);
           st.executeBatch();
           con.commit();
            
        }
        public void updateBanDate(Account account)throws SQLException{
            String sql="update accounts set Date=? where Username=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1, "");
            st.setString(2,account.getUsername());
            st.executeUpdate();
        }
        public static void main(String[]args) throws SQLException{
            User my=new User();
            DataBase.getInstance().Connection();
            DataBase.getInstance().delete();
            Account mya=new User();
            mya.username="Flaviu";
            mya.password="loveforlove1";

            //DataBase.getInstance().updateCode(mya);
           // DataBase.getInstance().getProfile(new User("Flaviu","sasa"));
        //    DataBase.getInstance().updateRequestsAndQueue("Fla", "Fla", "222", "22222");
         //   DataBase.getInstance().getRequests(my.myFunction("Flaviu", 1));
       //     DataBase.getInstance().getFriends(my.myFunction("Flaviu", 1));
          //  DataBase.getInstance().updateRequestsAndQueue("Flaviu", "Fla", "1", "2");
         //   DataBase.getInstance().s("Noii", "Fla");
          //  DataBase.getInstance().updateBatch("Fla", "Fla");
          //  DataBase.getInstance().getBlockList(new User().myFunction("Flaviu", 1));
           // DataBase.getInstance().getFriends(new User().myFunction("Flaviu", 1));
        }
}
