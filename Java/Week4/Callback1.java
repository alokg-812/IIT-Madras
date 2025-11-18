package Java.Week4;

interface DoughReadyCallback{
    void onDoughReady(String dough);
}

class DoughMaker{
    void makeDough(DoughReadyCallback callback){
        System.out.println("DoughMaker: Starting to prepare dough...");
        String dough = "Soft Dough";
        System.out.println("DoughMaker: Dough is ready!");
        callback.onDoughReady(dough);
    }
}

class Baker{
    void bakeCake(String dough){
        System.out.println("Baler: Received " + dough + ".Baking cake...");
        System.out.println("Baker: Cake is ready!");
    }
}

class Coordinator{
    DoughMaker doughMaker = new DoughMaker();
    Baker baker = new Baker();
    void makeCake(){
        System.out.println("Coordinator: I want to make a cake");

        doughMaker.makeDough(new DoughReadyCallback() {
            @Override
            public void onDoughReady(String dough){
                System.out.println("Coordinator: Got the dough, baking cake now...");
                baker.bakeCake(dough);
            }
        });
    }
}
public class Callback1 {
    public static void main(String[] args) {
        Coordinator coordinator = new Coordinator();
        coordinator.makeCake();
    }
}
