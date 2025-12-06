package Java.Week9;

import java.util.*;
class User{
      public String name;
      public String pass;
      User(String name,String pass){
            this.name=name;
            this.pass=pass;
     }
     String checkUser(){
            if(pass.equals("1234")){
                  return name;
            }
            else{
                  return pass;
            } 
      }
}
public class Test{
     public static void main(String[] args){
            Optional<String> op1=Optional.ofNullable(new User(null,"1234").checkUser());
            Optional<String> op2=Optional.ofNullable(new User("Moon","4321").checkUser());
            System.out.println(op1.orElse("Sun")+"\n"+op2.orElse("Sun"));
     }
}