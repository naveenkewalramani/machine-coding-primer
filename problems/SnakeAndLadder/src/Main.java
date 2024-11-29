import dice.BaseDice;
import dice.DoubleDiceRoll;
import dice.SingleDiceRoll;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Snake and Ladder");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Snake Count, Ladder Count and Player Count");
        int snakeCount = sc.nextInt();
        int ladderCount = sc.nextInt();
        int playerCount = sc.nextInt();
        GameBoard gameBoard = new GameBoard(snakeCount, ladderCount, playerCount);

        System.out.println("Enter the Snake position in format of a,b");
        for (int i = 0; i < snakeCount; i++) {
            String snakePosition = sc.next();
            String[] position = snakePosition.split(",");
            gameBoard.setSnake(new Snake(Integer.parseInt(position[0]), Integer.parseInt(position[1])));
        }

        System.out.println("Enter the Ladder position in format of a,b");
        for (int i = 0; i < ladderCount; i++) {
            String ladderPosition = sc.next();
            String[] position = ladderPosition.split(",");
            gameBoard.setLadder(new Ladder(Integer.parseInt(position[0]), Integer.parseInt(position[1])));
        }

        System.out.println("Enter the Player names");
        for (int i = 0; i < playerCount; i++) {
            String name = sc.next();
            gameBoard.setPlayer(new Player(name));
        }

//        BaseDice singleDice = new SingleDiceRoll();
        BaseDice doubleDice = new DoubleDiceRoll();

        gameBoard.setDice(doubleDice);

        System.out.println("Starting the game");
        gameBoard.startGame();
        System.out.println("Finishing the game");
    }
}