/*
 * Write a program to accept a string input from user and print the characters at even indices
 * input - string, static, void
 */

package Java.OPPEPractice;
import java.util.*;

public class CharactersAtEvenIndices {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        evenDisplay(str);
    }    
    
    public static void evenDisplay(String s){
        for(int i = 0;i<s.length();i+=2){
            System.out.println(s.charAt(i));
        }
    }
}
