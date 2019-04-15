/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dezibil
 */
import java.util.Arrays; 
import java.util.Random;
  
public class GDG { 
      
    static final int MAX_CHAR = 256; 
       public static char alphabet[]={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

       static void another(String str){
                   Random random=new Random();

        int randomNumber;
        char anotherChar;
        StringBuilder myBuilder=new StringBuilder();
           int count[]=new int[256];
           for(int i=0;i<256;i++){
               count[i]=0;
           }
           for(int i=0;i<str.length();i++){
               char c=str.charAt(i);
               ++count[c];
               
               if(count[c]==1){
                  myBuilder.append(c);
               }
               if(count[c]==2){
                   randomNumber=random.nextInt(24);
                  anotherChar= alphabet[randomNumber];
                  if(anotherChar!=c){
                      if(random.nextInt(99)>50){
                      myBuilder.append(Character.toUpperCase(anotherChar));}
                      else{
                          myBuilder.append(anotherChar);
                      }
                  }
               }
           }
           System.out.println(myBuilder);
       }
  static void printDistinct(String str) 
    { 
        Random random=new Random();
        int n = str.length(); 
        int randomNumber;
       
        // count[x] is going to store count of 
        // character 'x' in str. If x is not present, 
        // then it is going to store 0. 
        int[] count = new int[222]; 
       
        // index[x] is going to store index of character 
        // 'x' in str. If x is not present or x is 
        // more than once, then it is going to store a  
        // value (for example, length of string) that  
        // cannot be a valid index in str[] 
        int[] index = new int[122]; 
       
        // Initialize counts of all characters and  
        // indexes of distinct characters. 
        for (int i = 0; i < 122; i++) 
        { 
            count[i] = 0; 
            index[i] = 13; // A value more than any  
                          // index in str[] 
        } 
       
        // Traverse the input string 
        for (int i = 0; i < n; i++) 
        { 
            // Find current character and increment  
            // its count 
            char x = str.charAt(i); 
            ++count[x]; 
       
            // If this is first occurrence, then set  
            // value in index as index of it. 
            if (count[x] == 1 && x !=' ') {
                index[x] = i;
                System.out.println(index[x]+"ala mare");
            }
            
       
            // If character repeats, then remove it  
            // from index[] 
            if (count[x] == 2) {
                randomNumber=random.nextInt(24);
               // System.out.println(randomNumber);
                index[x] = 13;
                System.out.println(index[x]+"A lui ala");
               // if((int)anotherChar)
                char anotherChar=alphabet[randomNumber];
//                if((int)anotherChar==count[x]){
//                    randomNumber=random.nextInt(24);
//                }
              //  anotherChar=alphabet[randomNumber];
              System.out.println(alphabet[randomNumber]+"Sori");
                ++count[anotherChar];
                index[anotherChar]=1;
                System.out.println(index[anotherChar]+"INDEXUL FMM");
            }
            if(count[x]>=3){
                System.out.println("SUntem aici");
            }
        } 
       
        // Since size of index is constant, below  
        // operations take constant time. 
        Arrays.sort(index); 
          StringBuilder myString=new StringBuilder();
        for (int i = 0; i < 122&& index[i] != 13; 
                                                  i++) {
            myString.append(str.charAt(index[i]));
      //      System.out.print(index[i]+"a");
          // System.out.print(index[i]+"/n    "); }
        
        
    }
        System.out.println(myString);
    }
    // Driver code 
    public static void main(String args[]) 
    { 
        //int x=0;
       // System.out.println(x++);
        int count[]={1,2,3};
       // System.out.println(++count[1]);
       String str = "flla"; 
        another(str); 
    } 
} 
// T