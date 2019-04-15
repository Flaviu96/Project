/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dezibil
 */
public class ClasaNormala {
    static class StaticNetestedClass{
        public int x;
    }
    public static void main(String[]args){
            ClasaNormala.StaticNetestedClass obj=new ClasaNormala.StaticNetestedClass();
            obj.x=12;
            ClasaNormala.StaticNetestedClass obj1=new ClasaNormala.StaticNetestedClass();
            obj1.x=15;
            System.out.println(obj.x);
    }
}
