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
    @Override
    public void makeMove() {
        makelistWithPossibleStones();
        choice = 0;
    }

    @Override
    public void makeChoice(int range) {

    }
}

