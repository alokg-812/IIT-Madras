import java.util.*;
abstract class StringOperations{
  public abstract String reverse(String s);
  public abstract int vowelCount(String s);
}
//Fill the code here
// Class that extends StringOperations but only defines reverse()
abstract class StringReverse extends StringOperations {
  @Override
  public String reverse(String s) {
    StringBuilder sb = new StringBuilder(s);
    return sb.reverse().toString();
  }
}

// Class that extends StringReverse and defines the remaining abstract method vowelCount()
class UpdatedStrings extends StringReverse {
  @Override
  public int vowelCount(String s) {
    int count = 0;
    // Convert the string to lowercase to count both cases easily
    String lowerS = s.toLowerCase();
    
    for (int i = 0; i < lowerS.length(); i++) {
      char c = lowerS.charAt(i);
      if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
        count++;
      }
    }
    return count;
  }
}


class Example {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String s = sc.next();
    UpdatedStrings str = new UpdatedStrings();
    System.out.println("Reverse of "+ s + " is "+ str.reverse(s));
    System.out.println("Vowel count of "+ s + " is "+ str.vowelCount(s));
  }
}
