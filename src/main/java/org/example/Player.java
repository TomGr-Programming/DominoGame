package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
/**
 * Die Klasse Player stellt einen Spieler (Teilnehmer) des Spiels dar.
 *
 * @author Tom Griesbach
 * @version 07.10.2018
 */
public class Player
{
    protected List<Domino> stonesPlayer = new LinkedList<>();
    protected Domino currentStone;
    protected ArrayList <Domino> listWithPossibleStones = new ArrayList<>();
    protected String[] choices;
    protected int choice = 0;
    protected Random random = new Random();
    public Player()
    {

    }
    public ArrayList getlistWithPossibleStones() {
        return listWithPossibleStones;
    }
    public String[] getpossibleChoicesString() {
        return choices;
    }
    public Domino getCurrentStone() {
        return currentStone;
    }
    public int getChoice() {
        return choice;
    }
    protected void makeMove() {

    }
    protected void makeChoice(int range) {

    }
    protected boolean iAmHuman() {
        return false;
    }
    protected boolean iAmSmart() {
        return true;
    }
    public void acceptCurrentStone(Domino stone) {
        currentStone = stone;
    }
    public void makelistWithPossibleStones() {
        int right = getCurrentStone().getRightSide();
        int left = getCurrentStone().getLeftSide();
        boolean possibleChoiceLeft = false;
        boolean possibleChoiceRight = false;
        for(int counter = 0; counter < stonesPlayer.size(); counter++) {
            if (stonesPlayer.get(counter).getLeftSide() == right) {
                possibleChoiceLeft = true;
            }
            if (stonesPlayer.get(counter).getRightSide() == left) {
                possibleChoiceRight = true;
            }
            if (possibleChoiceLeft | possibleChoiceRight) {
                listWithPossibleStones.add(stonesPlayer.get(counter));
                possibleChoiceLeft = false;
                possibleChoiceRight = false;
            }
        }
    }
    public void possibleChoicesToStringArray() {
        choices = new String[listWithPossibleStones.size() + 1];
        choices[0] = "ziehen";
        for(int i = 0; i < listWithPossibleStones.size(); i++) {
            choices[i] = listWithPossibleStones.get(i).toString();
            if(i == listWithPossibleStones.size() - 1) {
                choices[i + 1] = "ziehen";
            }
        }
    }
    public void resetPossibleStonesAndChoices() {
        choices = new String[0];
        listWithPossibleStones.clear();
    }
    public void deleteChoice(Domino stone) {
        int position = 0;
        for(int counter = 0; counter < stonesPlayer.size(); counter++) {
            if(stonesPlayer.get(counter).equals(stone)) {
                position = counter;
            }
        }
        stonesPlayer.remove(position);
    }
}

