package org.example;


/**
 * Die Klasse StupidPlayer stellt eine Art von ComputernPlayern dar.
 *
 * @author Tom Griesbach
 * @version 07.10.2018
 */
public class StupidPlayer extends Player
{
    public StupidPlayer()
    {

    }
    public boolean iAmSmart() {
        return false;
    }
    public void makeMove() {
        makelistWithPossibleStones();
        choice = 0;
    }
}

