import java.util.ArrayList;
import java.util.Random;

import dice.BaseDice;

public class GameBoard {
    private ArrayList<Snake> snakes;
    private ArrayList<Ladder> ladders;
    private ArrayList<Player> players;
    private BaseDice dice;

    GameBoard(int snakeCount, int ladderCount, int playerCount) {
        snakes = new ArrayList<>(snakeCount);
        ladders = new ArrayList<>(ladderCount);
        players = new ArrayList<>(playerCount);
    }

    public void setSnake(Snake snake) {
        this.snakes.add(snake);
    }

    public void setLadder(Ladder ladder) {
        this.ladders.add(ladder);
    }

    public void setPlayer(Player player) {
        this.players.add(player);
    }

    public void setDice(BaseDice dice) {
        this.dice = dice;
    }

    public int computePosition(Player player, int diceRoll) {
        int currentPosition = player.getCurrentPosition();
        int newPosition = currentPosition + diceRoll;
        if (newPosition > 100) {
            return currentPosition;
        }
        while (true) {
            int tempPosition = newPosition;
            for (int i = 0; i < this.snakes.size(); i++) {
                if (tempPosition == this.snakes.get(i).getHead()) {
                    tempPosition = this.snakes.get(i).getTail();
                }
            }

            for (int i = 0; i < this.ladders.size(); i++) {
                if (tempPosition == this.ladders.get(i).getStart()) {
                    tempPosition = this.ladders.get(i).getEnd();
                }
            }
            if (tempPosition == newPosition) {
                break;
            } else {
                newPosition = tempPosition;
            }
        }
        return newPosition;
    }

    public void startGame() {
        while (true) {
            for (int i = 0; i < this.players.size(); i++) {
                int diceValue = this.dice.roll();
                int newPosition = computePosition(this.players.get(i), diceValue);
                this.players.get(i).setPosition(newPosition, diceValue);
                if (players.get(i).checkWinner()) {
                    return;
                }
            }
        }
    }
}
