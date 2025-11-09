package Java.OPPEPractice;

import java.util.*;

interface Attackable {
    public String attack();
}

interface Castable {
    public String castSpell();
}

class GameCharacter implements Attackable, Castable {
    String name;

    GameCharacter(String name) {
        this.name = name;
    }

    public String attack() {
        return name + " attacks with a sword";
    }

    public String castSpell() {
        return name + " casts a spell";
    }
}

public class AttackableCastable {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> actionsList = new ArrayList<>();

        while (sc.hasNext()) {
            String input = sc.next(); // W or M
            String name = sc.next();  // character name

            if (input.equalsIgnoreCase("W")) {
                Attackable w = new GameCharacter(name);
                actionsList.add(w.attack());
            } else {
                Castable m = new GameCharacter(name);
                actionsList.add(m.castSpell());
            }
        }

        for (String action : actionsList) {
            System.out.println(action);
        }
    }
}
