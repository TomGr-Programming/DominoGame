package org.example;


/**
 * Die Klasse SmartPlayer stellt eine Art von ComputerPlayern dar.
 *
 * @author Tom Griesbach
 * @version 07.10.2018
 */
public class SmartPlayer extends ComputerPlayer
{
    public SmartPlayer()
    {

    }
    public void makeMove() {
        makelistWithPossibleStones();
        makeChoice(listWithPossibleStones.size());
    }
    public void makeChoice(int range) {
        choice = random.nextInt(range);
    }
}
