class Date{
    int day;
    int month;
    int year;

    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public boolean equals(Date d){
        return ((this.day == d.day) && (this.month == d.month) && (this.year == d.year));
        // if(d instanceof Date){
        //     Date myDate = (Date)d;
        //     return ((this.day == myDate.day) && (this.month == myDate.month) && (this.year == myDate.year));
        // }
        // return false;
    }
}

public class OverridingEqualsMethod {
    public static void main(String[] args) {
        Date d1 = new Date(24, 4, 2004);
        Date d2 = new Date(8, 12, 2004);
        System.out.println(d1.equals(d2));
    }
}
