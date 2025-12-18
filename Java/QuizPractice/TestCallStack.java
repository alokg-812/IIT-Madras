package Java.QuizPractice;

class Calculator{
    public static int compute(int n){
        if(n<=1){ return 1; }
        else{ return compute(n-1) + helper(n); }
    }
    public static int helper(int x){
        return x*2;
    }
}

public class TestCallStack {
    public static void main(String[] args) {
        int res = Calculator.compute(4);
        System.out.println(res);
    }    
}
