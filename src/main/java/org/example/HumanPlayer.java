package org.example;

/**
 * Die Klasse HumanPlayer stellt eine Art von Spielern (Playern) dar.
 *
 * @author Tom Griesbach
 * @version 07.10.2018
 */
public class HumanPlayer extends Player
{
    public HumanPlayer()
    {

    }
    public boolean iAmHuman() {
        return true;
    }
    public void makeMove() {
        makelistWithPossibleStones();
        possibleChoicesToStringArray();
    }
}

