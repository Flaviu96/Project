/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dezibil
 */
public class NewClass {
    public static void main(String[]args){
        Account myAccount=new User();
        myAccount.setNewPassword("Dsadsadsa");
        myAccount.setOldPassword("Sdas");
        myAccount.setUsername("Yuhuu");
        MyThreadPool.getInstance().get().execute(new FckIt(myAccount,1));
        MyThreadPool.getInstance().get().execute(new FckIt(myAccount,2));
        //System.out.println(myAccount.toString());
    }
}
