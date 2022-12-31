package org.example;

public class Main {
    public static void main(String[] args) {
        DominoGame dominoGame = new DominoGame();
        dominoGame.addPlayer(new HumanPlayer());
        dominoGame.addPlayer(new StupidPlayer());
        dominoGame.startGame();
    }
}