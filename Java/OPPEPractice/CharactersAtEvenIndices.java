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
        char[] arr = str.toCharArray();
        System.out.println(str);
        System.out.println(arr[0]);
    }    
}
