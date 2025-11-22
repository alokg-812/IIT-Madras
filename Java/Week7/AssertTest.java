package Java.Week7;
class Validation{
    public boolean validation(int a, int b){
        assert a>0 : "a should be greater than 0";
        assert b>=1: "b should not be less than 1";
        return true;
    }
}

public class AssertTest {
    public static void main(String[] args) {
        int a = 1,b = -1, res = 0;
        Validation val = new Validation();
        if(val.validation(a, b)){
            res = a/b;
        }
        assert res>0: "result should be greater than 0";
        System.out.println(res);
    }
}
