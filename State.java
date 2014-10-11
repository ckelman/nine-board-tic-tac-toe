/*
 * File: State.java
 * Creator: George Ferguson
 * Created: Tue Jan 31 10:01:20 2012
 * Time-stamp: <Tue Jan 29 10:42:43 EST 2013 ferguson>
 */

import java.io.*;
import java.util.*;

/**
 * Representation of the state of a TTT game.
 */
public class State {

    // Properties

    protected NineBoard board;

    protected Player nextPlayer;
    
    //keeps track of the value
    public Integer value = null;

    public NineBoard getBoard() {
	return board;
    }

    public Player getNextPlayer() {
	return nextPlayer;
    }

    // Constructors

    /**
     * Construct and return a new State with the given Board and next Player.
     */
    //changed the Board to the NineBoard
    public State(NineBoard board, Player player) {
	this.board = board;
	this.nextPlayer = player;
    }

    /**
     * Construct and return a new State with an empty Board of the given size,
     * with the given Player next to play.
     */
    public State(int size, Player player) {
	this(new NineBoard(), player);
    }

    /**
     * Construct and return a new State with an empty Board of the given size,
     * with Player X next to play.
     */


    /**
     * Construct and return a new State with an empty Board of the default
     * size and the given Player next to play.
     */
    public State(Player player) {
	this(new NineBoard(), player);
    }

    /**
     * Construct and return a new State with an empty Board of the default
     * size and Player X next to play.
     */
    public State() {
	this(Player.X);
    }

    // Printing

    public void print(PrintWriter out) {
	board.print(out);
	out.println("Next to play: " + nextPlayer);
    }

    public void print(PrintStream out) {
	print(new PrintWriter(out, true));
    }

    public void print() {
    	
	print(System.out);
    }

    public String toString() {
	StringWriter writer = new StringWriter();
	PrintWriter out = new PrintWriter(writer);
	print(out);
	out.flush();
	return writer.toString();
    }

    // Methods

    /**
     * Update this State by applying the Move to our Board and
     * toggling our nextPlayer.
     */
    public void apply(Move move) {
	board.apply(move);
	nextPlayer = nextPlayer.otherPlayer();
    }

    /**
     * Return true if this is a terminal State (i.e., represents a finished
     * game).
     */
    public boolean isTerminal() {
	return board.isFinished();
    }

    /**
     * Return the player who has won this game (if any).
     */
    public Player getWinner() {
	return board.getWinner();
    }

    /**
     * Return List of possible moves (for the nextPlayer) in this State.
     */
    public List<Move> getPossibleMoves() {
	return board.getPossibleMoves(nextPlayer);
    }

    /**
     * Return a new State whose Board is a copy of this state,
     * with the same nextPlayer.
     */
    public State copy() {
	return new State(board.copy(), nextPlayer);
    }

    // Testing

    public static void main(String[] argv) {
	State state = new State();
	state.print();
    }
    
    public int getValue()
    {
    	if(value!=null)
    		return value;
    	
    	if (isTerminal())
    	{
    		Player p = getWinner();
    		if(p==Player.X)
    		{
    			value = 1;
    			return 1;
    		}
    		else
    		{
    			value = -1;
    			return -1;
    		}
    			
    	}
    	else
    	{
    		int sum = 0;
    		for (Move m: getPossibleMoves())
    		{
    			State s = copy();
    			s.apply(m);
    			
    			sum+=s.getValue();

    		}
			if(value==null||sum>value)
				value = sum;
    		
    		
    		//System.out.println(value);
    		return value;
    	}
    }
    
   

}
