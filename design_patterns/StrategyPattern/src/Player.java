import rackets.Racket;

public class Player {
    String name;
    Racket racket;

    Player(String name) {
        this.name = name;
    }

    public void setRacketType(Racket racket) {
        this.racket = racket;
    }

    public void displayRacketDetails() {
        System.out.printf("%s uses racket with following details\n", this.name);
        this.racket.racketSpeed();
        this.racket.racketWeight();
    }
}
