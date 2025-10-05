import java.util.*;
public class SeriesSum {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
//Fill your code here
    long sum = 0;
    for(int k = 1; k<=n;k++){
	    long termSum = 0;
		for(int i = 1;i<=k;i++){
			termSum +=(long) i*i;
		}
	sum += termSum;
	}
System.out.println(sum);
    
  }
}
