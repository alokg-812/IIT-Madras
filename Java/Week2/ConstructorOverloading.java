class Date{
    int day;
    int month;
    int year;

    public Date(int d, int m, int yr){
        day = d;
        month = m;
        year = yr;
    }

    public Date(int d, int m){
        day = d;
        month = m;
        year = 2025;
    }

    public Date(Date other){
        this.day = other.day;
        this.month = other.month;
        this.year = other.year;
    }
}


public class ConstructorOverloading{
    public static void main(String []args){
        Date d1, d2, d3;
        d1 = new Date(18, 9, 2025);
        d2 = new Date(28, 12, 1994);
        d3 = new Date(d1);
        System.out.println(d1.day);
        System.out.println(d1.month);
        System.out.println(d1.year);
        System.out.println(d2.day);
        System.out.println(d2.month);
        System.out.println(d2.year);
        System.out.println(d3.day);
        System.out.println(d3.month);
        System.out.println(d3.year);
    }
}
