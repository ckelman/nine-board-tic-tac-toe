/*
 * File: HumanGame.java
 * Creator: George Ferguson
 * Created: Tue Jan 31 09:55:32 2012
 * Time-stamp: <Tue Jan 29 15:56:22 EST 2013 ferguson>
 */

import java.io.*;
import java.util.StringTokenizer;

/**
 * A Game played between human players.
 */
public class HumanGame extends Game {

    /**
     * Return the next move in this Game.
     * For a HumanGame, prompt the user for a move, read it from stdin,
     * and return a new Move.
     */
    @Override
    protected Move getNextMove() {
	int x,y;
	
	while(state.board.currentBoard==null||state.board.currentBoard.isFull())
	{
		System.out.println("Enter what Board you would like to play on (X Y):");
		try{
			String line = stdin.readLine();
			StringTokenizer tokens = new StringTokenizer(line);
			x = Integer.valueOf(tokens.nextToken());
			y = Integer.valueOf(tokens.nextToken());
			state.board.currentBoard = state.board.board[x][y];
			
		}
		catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	while (true) {
	    System.out.print("Enter move (X Y): ");
	    try {
		String line = stdin.readLine();
		StringTokenizer tokens = new StringTokenizer(line);
		x = Integer.valueOf(tokens.nextToken());
		y = Integer.valueOf(tokens.nextToken());
		break;
	    } catch (Exception ex) {
		System.out.println(ex);
	    }
	}
	return new Move(state.board.currentBoard,state.getNextPlayer(), x, y);
    }

    protected BufferedReader stdin =
	new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] argv) {
	new HumanGame().play();
    }

}
