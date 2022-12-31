package org.example;

/**
 * Die Klasse Domino stellt einen Domino-Stein dar.
 *
 * @author Tom Griesbach
 * @version 07.10.2018
 */
public class Domino
{
    private int valueLeftSide;
    private int valueRightSide;
    /**
     * Constructor for objects of class Domino
     */
    public Domino(int left, int right)
    {
        valueLeftSide = left;
        valueRightSide = right;
    }
    public int getLeftSide() {
        return valueLeftSide;
    }
    public int getRightSide() {
        return valueRightSide;
    }
    @Override
    public String toString() {
        return "[" + valueLeftSide + "|" + valueRightSide + "]";
    }
}
