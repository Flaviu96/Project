
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dezibil
 */
public class Date1 {
  public int day,month,year;
  public Date1(int day,int month,int year){
      this.day=day;
      this.month=month;
      this.year=year;
  }
  public Date1(){
      
  }
  public static Date1 getDate(){
             java.util.Date now=new java.util.Date();
            int month=now.getMonth()+1;
            int year=1900+now.getYear();
            int day=now.getDate();
            Date1 data=new Date1(day,month,year);
            return data;
        }
  public static  Date1 convert(String date){
      if(date.length()==0){
          return new Date1(0,0,0);
      }else{
      String []d=date.split("/");
      int day=Integer.parseInt(d[0]);
      int month=Integer.parseInt(d[1]);
      int year=Integer.parseInt(d[2]);
      return new Date1(day,month,year);}
  }
  public static String dateString(){
      SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String dateString = format.format( new Date()   );
      return dateString;
     
  }
  public static int check1(Date1 ban,Date1 date0){
      int i=0;
      if(ban.day==0 && ban.month==0 && ban.year==0){
          i=1;
          //no ban
      }
      else{
          if(date0.year>ban.year){
              i=1;
              //no ban
          }
          else{
              if(date0.month>ban.month){
                 i=1;
                 //no ban
              }
              else{
                  if(date0.day>=ban.day){
                      i=2;
                  }
                  if(date0.month>=ban.month){
                     i+=2;
                  }
              }
          }
      }
      return i;
  }
  public static int check2(Date1 ban,Date1 date){
      int counter=0;
      if(ban.day==0 && ban.month==0 && ban.year==0){
          counter=1;
      }
      else{
          if(date.year>ban.year){
              counter=1;
          }
          else{
              if(date.month==ban.month){
                  counter=date.day>=ban.day?1:2;
                //  System.out.println("Ii aici");
              }
              else{
                  if(date.day>=ban.day){
                      counter=2;
                  }
                  if(date.month>=ban.month){
                      counter+=2;
                  }
              }
          }
      }
      return counter;
  }
  public String toString(){
      return day+"/"+month+"/"+year;
  }
  public static void main(String[]args){
      Date1 neBan=new Date1(16,1,2020);
      Date1 ban=new Date1(15,2,2019);
      int resu=check2(ban,neBan);
      System.out.println(resu);

  }
  
}
