import rackets.Racket;
import rackets.YonexSeriesA;
import rackets.YonexSeriesB;

// Strategy Pattern example using BadmintonPlayer
public class Main {
    public static void main(String[] args) {
        Racket racketType1 = new YonexSeriesA();
        Racket racketType2 = new YonexSeriesB();

        Player p1 = new Player("Player1");
        Player p2 = new Player("Player2");
        Player p3 = new Player("Player3");

        p1.setRacketType(racketType1);
        p3.setRacketType(racketType1);
        p2.setRacketType(racketType2);

        p1.displayRacketDetails();
        p2.displayRacketDetails();
        p3.displayRacketDetails();

        // change in the pattern
        p3.setRacketType(racketType2);
        p3.displayRacketDetails();
    }
}