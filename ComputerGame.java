/*
 * File: ComputerGame.java
 * Creator: George Ferguson
 * Created: Tue Jan 31 10:54:47 2012
 * Time-stamp: <Tue Jan 29 16:01:10 EST 2013 ferguson>
 */

import java.io.*;
import java.util.*;

/**
 * A Game played between a human and the computer.
 * Arguably not an extension of HumanGame, more like a sibling, but
 * it's nice to reuse the IO code...
 */
public class ComputerGame extends HumanGame {

    /**
     * Which player is the human.
     */
    protected Player humanPlayer;

    /**
     * Initialize this Game at the start of play.
     * For a ComputerGame, we also need to know which player (X or O)
     * the human wants to play.
     */
    @Override
    public void init() {
	super.init();
	humanPlayer = getHumanPlayer();
    }

    /**
     * Prompt the user to play either X or O, and return the
     * corresponding Player.
     */
    protected Player getHumanPlayer() {
	while (true) {
	    System.out.print("Do you want to play X or O: ");
	    try {
		String line = stdin.readLine();
		if (line.equalsIgnoreCase("x")) {
		    return Player.X;
		} else if (line.equalsIgnoreCase("o")) {
		    return Player.O;
		}
	    } catch (IOException ex) {
		System.out.println(ex);
	    }
	}
    }

    /**
     * Return the next move in this Game.
     * For a ComputerGame, either get the move from the human or
     * figure it ourselves, depending on who is next to play.
     */
    @Override
    protected Move getNextMove() {
	if (state.getNextPlayer().equals(humanPlayer)) {
	    return super.getNextMove();
	} else {
	    return getNextComputerMove();
	}
    }

    /**
     * Compute and return the best Move for the current state of
     * this Game.
     */
    protected Move getNextComputerMove() {
	List<Move> moves = state.getPossibleMoves();
	if (moves.size() == 0) {
	    throw new NoPossibleMoveException();
	}
	// You could be smarter...
	Move m = null;
	for(Move mo:moves)
	{
		if(m==null)
			m=mo;
		else if(mo!=null)
		{
			
			
			if(state.nextPlayer==Player.X)
			{
				State s1 = state.copy();
				State s2 = state.copy();
				s1.apply(m);
				s2.apply(mo);
				if(s1.getValue()<s2.getValue())
					m=mo;
			
			}
			else
			{
				State s1 = state.copy();
				State s2 = state.copy();
				s1.apply(m);
				s2.apply(mo);
				if(state.getNextPlayer()==Player.X)
				{
				if(s1.getValue()<s2.getValue())
					m=mo;
				}
				
				else if(state.getNextPlayer() == Player.O)
				{
					if(s1.getValue()>s2.getValue())
						m=mo;
				}
			}
		}
	}
		return m;
    }

    
    protected Random random = new Random();

    public static void main(String[] argv) {
	new ComputerGame().play();
    }
}
