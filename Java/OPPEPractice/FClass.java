package Java.OPPEPractice;
import java.util.*;

class Rectangle{
    int w;
    int h;

    public void setw(int width){
        this.w = width;
    }

    public void seth(int height){
        this.h = height;
    }
    
    public int area(){
        return this.w * this.h;
    }

}

public class FClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int w = Integer.parseInt(sc.nextLine());
        int h = Integer.parseInt(sc.nextLine());
        Rectangle r = new Rectangle();
        r.setw(w);
        r.seth(h);
        int area = r.area();
        System.out.println(area);
    }    
}
