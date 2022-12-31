package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * Die Klasse DominoGame steuert das Spiel.
 *
 * @author Tom Griesbach
 * @version 07.10.2018
 */
public class DominoGame
{
    private ArrayList<Player> allPlayers = new ArrayList<>();
    private List<Domino> stonesPool = new LinkedList<>();
    private ArrayList<Integer> resultsAfterGame = new ArrayList<>();
    private Domino currentStone;
    private int choice;
    private UserDialog dialog = new UserDialog();
    public DominoGame()
    {

    }
    private Domino getCurrentStone() {
        return currentStone;
    }
    private int getChoice() {
        return choice;
    }
    public void addPlayer(Player player) {
        allPlayers.add(player);
    }
    public void startGame() {
        createDominoStones();
        distributeStones();
        currentStone = stonesPool.get(0);
        gameIsRunning();
    }
    private void gameIsRunning() {
        System.out.println("Willkommen zum Domino-Spiel. Der erste Stein lautet: ");
        System.out.println(getCurrentStone());
        stonesPool.remove(0);
        boolean run = true;
        while(run) {
            for(int i = 0; i < allPlayers.size(); i++) {
                allPlayers.get(i).acceptCurrentStone(getCurrentStone());
                if(allPlayers.get(i).iAmHuman()) {
                    allPlayers.get(i).makeMove();
                    System.out.println("Ihre Dominos: " + allPlayers.get(i).stonesPlayer.toString());
                    printChoices(allPlayers.get(i).getpossibleChoicesString());
                    processChoice(getChoice(), i);
                    if(getChoice() < allPlayers.get(i).listWithPossibleStones.size()) {
                        System.out.println("Ich: " + allPlayers.get(i).listWithPossibleStones.get(getChoice()).toString());
                    }
                    System.out.println("Anlegemöglichkeiten: " + getCurrentStone());
                    allPlayers.get(i).resetPossibleStonesAndChoices();
                } else {
                    allPlayers.get(i).makeMove();
                    processChoice(allPlayers.get(i).getChoice(), i);
                    if(allPlayers.get(i).getChoice() < allPlayers.get(i).listWithPossibleStones.size()) {
                        System.out.println("Wahl des Computers: " + allPlayers.get(i).listWithPossibleStones.get(allPlayers.get(i).getChoice()).toString());
                        System.out.println("Anlegemöglichkeiten: " + getCurrentStone());
                    } else if(allPlayers.get(i).getChoice() == allPlayers.get(i).listWithPossibleStones.size()) {
                        System.out.println("Wahl des Computers: ziehen");
                        System.out.println("Anlegemöglichkeiten: " + getCurrentStone());
                    }
                    allPlayers.get(i).resetPossibleStonesAndChoices();
                }
                if(allPlayers.get(i).stonesPlayer.isEmpty()) {
                    run = false;
                    printGameEnd();
                    calculateResult();
                    printResult();
                    i = allPlayers.size();
                    String[] possibles = {"Nein", "Ja"};
                    choice = dialog.getUserInput("Weitere Runde? ", possibles);
                    if(getChoice() == 0) {
                        System.out.println("Tschüß");
                    } else {
                        newGame();
                    }
                }
                if(run) {
                    if(stonesPool.isEmpty()) {
                        run = false;
                        printGameEnd();
                        calculateResult();
                        printResult();
                        String[] possibles = {"Nein", "Ja"};
                        choice = dialog.getUserInput("Weitere Runde? ", possibles);
                        if(getChoice() == 0) {
                            System.out.println("Tschüß");
                            i = allPlayers.size();
                        } else {
                            newGame();
                        }
                    }
                }
            }
        }
    }
    private void newGame() {
        for(int i = 0; i < allPlayers.size(); i++) {
            allPlayers.get(i).stonesPlayer.clear();
        }
        stonesPool.clear();
        startGame();
    }
    private void printChoices(String[] possibles) {
        choice = dialog.getUserInput("Auswahlmöglichkeiten: ", possibles);
    }
    private void printGameEnd() {
        System.out.println("Spielende");
        for(int i = 0; i < allPlayers.size(); i++) {
            if(allPlayers.get(i).iAmHuman()) {
                System.out.println("Ihre Dominos: " + allPlayers.get(i).stonesPlayer.toString());
            } else {
                System.out.println("Dominos Spieler " + i + ":" + allPlayers.get(i).stonesPlayer.toString());
            }
        }
    }
    private void printResult() {
        for(int i = 0; i < allPlayers.size(); i++) {
            if(allPlayers.get(i).iAmHuman()) {
                System.out.println("Spielstand: ");
                System.out.println("Ihr Ergebnis: " + resultsAfterGame.get(i));
            } else if(!allPlayers.get(i).iAmHuman()) {
                System.out.println("Computer: " + resultsAfterGame.get(i));
            }
        }
    }
    private void calculateResult() {
        int value = 0;
        for(int i = 0; i < allPlayers.size(); i++) {
            for(int counter = 0; counter < allPlayers.get(i).stonesPlayer.size(); counter++) {
                if(allPlayers.get(i).stonesPlayer.size() != 0) {
                    value = value + allPlayers.get(i).stonesPlayer.get(counter).getLeftSide() + allPlayers.get(i).stonesPlayer.get(counter).getRightSide();
                }
            }
            if(resultsAfterGame.size() < allPlayers.size()) {
                resultsAfterGame.add(i, value);
            } else {
                resultsAfterGame.set(i, resultsAfterGame.get(i) + value);
            }
            value = 0;
        }
    }
    private void processChoice(int i, int player) {
        if(i == allPlayers.get(player).listWithPossibleStones.size()) {
            allPlayers.get(player).stonesPlayer.add(stonesPool.get(0));
            stonesPool.remove(0);
        } else {
            createNewStone(i, player);
        }
    }
    private void createNewStone(int i, int player) {
        Domino choiceOfPlayer;
        choiceOfPlayer = allPlayers.get(player).listWithPossibleStones.get(i);
        boolean leftSideEqual = false;
        boolean rightSideEqual = false;
        if(getCurrentStone().getLeftSide() == choiceOfPlayer.getRightSide()) {
            leftSideEqual = true;
        }
        if(getCurrentStone().getRightSide() == choiceOfPlayer.getLeftSide()) {
            rightSideEqual = true;
        }
        if(leftSideEqual & !rightSideEqual) {
            currentStone = new Domino(choiceOfPlayer.getLeftSide(), getCurrentStone().getRightSide());
            allPlayers.get(player).deleteChoice(choiceOfPlayer);
        }
        if(!leftSideEqual & rightSideEqual) {
            currentStone = new Domino(getCurrentStone().getLeftSide(), choiceOfPlayer.getRightSide());
            allPlayers.get(player).deleteChoice(choiceOfPlayer);
        }
        if(allPlayers.get(player).iAmHuman()) {
            if(leftSideEqual & rightSideEqual) {
                String[] whichSideYouWant = {"links anlegen?","rechts anlegen?"};
                printChoices(whichSideYouWant);
                if(getChoice() == 0) {
                    currentStone = new Domino(choiceOfPlayer.getLeftSide(), getCurrentStone().getRightSide());
                } else {
                    currentStone = new Domino(getCurrentStone().getLeftSide(), choiceOfPlayer.getRightSide());
                }
                allPlayers.get(player).deleteChoice(choiceOfPlayer);
            }
        }
        if(!allPlayers.get(player).iAmHuman()) {
            if(leftSideEqual & rightSideEqual) {
                if(allPlayers.get(player).iAmSmart()) {
                    allPlayers.get(player).makeChoice(1);
                    if(allPlayers.get(player).getChoice() == 0) {
                        currentStone = new Domino(choiceOfPlayer.getLeftSide(), getCurrentStone().getRightSide());
                    } else {
                        currentStone = new Domino(getCurrentStone().getLeftSide(), choiceOfPlayer.getRightSide());
                    }
                } else {
                    currentStone = new Domino(choiceOfPlayer.getLeftSide(), getCurrentStone().getRightSide());
                }
                allPlayers.get(player).deleteChoice(choiceOfPlayer);
            }
        }
    }
    private void createDominoStones() {
        DominoPool pool = new DominoPool();
        stonesPool = pool.provideShuffledDominoHeap();
    }
    private void distributeStones() {
        for(int counter = 0; counter < allPlayers.size(); counter++)
        {
            if(allPlayers.get(counter).iAmHuman()) {
                for(int i = 0; i < 5; i++) {
                    allPlayers.get(counter).stonesPlayer.add(stonesPool.get(counter));
                    stonesPool.remove(counter);
                }
            } else {
                for(int i = 0; i < 5; i++) {
                    allPlayers.get(counter).stonesPlayer.add(stonesPool.get(counter));
                    stonesPool.remove(counter);
                }
            }
        }
    }
}

