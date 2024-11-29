package dice;

import java.util.Random;

public class SingleDiceRoll implements BaseDice{

    @Override
    public int roll() {
        Random random = new Random();
        return random.nextInt(7 - 1) + 1;
    }
}
