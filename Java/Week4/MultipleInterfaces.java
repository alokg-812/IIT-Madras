interface Person {
    public default String getName(){
        return("No name");
    }
}

interface Designation{
    public default String getName(){
        return("No Designation");
    }

}

public class MultipleInterfaces implements Person, Designation {
    public String getName(){
        return("Multiple Interfaces");
    }
}
