package dice;

import java.util.Random;

public class DoubleDiceRoll implements BaseDice {

    @Override
    public int roll() {
        Random random = new Random();
        return random.nextInt(12 - 1) + 1;
    }
}
