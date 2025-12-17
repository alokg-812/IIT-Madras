package Java.QuizPractice;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
class Investor{
    String name, company;
    double investment;
    public Investor(String name, String company, double investment){
        this.name = name;
        this.company = company;
        this.investment = investment;
    }
}

public class ShareMarket {
    public static void calculateInvestment(ArrayList<Investor> al){
        var map = new TreeMap<String, Double>();
        for(Investor inv : al){
            map.put(inv.name, map.getOrDefault(inv.name,0.0) + inv.investment);
        }
        for(Map.Entry<String, Double> e : map.entrySet()){
            System.out.println(e.getKey() + " = " + e.getValue());
        }
    }
    public static void main(String[] args) {
        ArrayList<Investor> al = new ArrayList<Investor>();
        al.add(new Investor("Ravi", "Infosys", 150000));
        al.add(new Investor("Ram", "Wipro", 120000));
        al.add(new Investor("Komal", "TCS", 100000));
        al.add(new Investor("Mehul", "Whirlpool", 200000));
        al.add(new Investor("Mehul", "KPMG", 500000));
        calculateInvestment(al);
    }
}
