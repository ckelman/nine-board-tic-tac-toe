/*
 * File: Game.java
 * Creator: George Ferguson
 * Created: Tue Jan 29 15:37:34 2013
 * Time-stamp: <Tue Jan 29 15:56:51 EST 2013 ferguson>
 */

import java.io.*;

/**
 * The abstract Game class has methods for playing a game, given an
 * initial State.
 */
abstract public class Game {

    /**
     * The current State of this Game.
     */
    protected State state;

    /**
     * Main public method for a Game: play it!
     */
    public void play() {
	init();
	mainLoop();
	gameOver();
    }

    /**
     * Initialize this Game at the start of play.
     */
    protected void init() {
	state = new State();
    }

    /**
     * Until this Game is over, get the next move and apply it.
     */
    protected void mainLoop() {
	while (!state.isTerminal()) {
	    state.print();
	    Move move = getNextMove();
	    System.out.println("move: " + move);
	    try {
		state.apply(move);
	    } catch (TTTRuntimeException ex) {
		System.out.println(ex);
	    }
	}
    }

    /**
     * Called at the end of play once the game is over.
     */
    protected void gameOver() {
	state.print();
	Player winner = state.getWinner();
	if (winner != null) {
	    System.out.println("The winner is " + winner + ".");
	} else {
	    System.out.println("The game is a draw.");
	}
    }

    /**
     * Return the next move in this Game.
     * This method must be mplemented by subclasses.
     */
    abstract protected Move getNextMove();

}
