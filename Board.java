/*
 * File: Board.java
 * Creator: George Ferguson
 * Created: Tue Jan 31 09:16:13 2012
 * Time-stamp: <Tue Jan 29 10:44:30 EST 2013 ferguson>
 */

import java.io.*;
import java.util.*;

/**
 * Representation of a TTT board: square grid containing Players or
 * null (empty).
 */
public class Board {

    // Properties

    protected int size;
    //x and y location of this board in a NineBoard's 2D array
    public int x;
    public int y;

    public Player[][] grid;

    // Constructors

    /**
     * Construct and return a new 3x3 TTT Board.
     */
    public Board() {
	this(3);
    }

    /**
     * Construct and return a new nxn TTT Board.
     */
    public Board(int n) {
	size = n;
	grid = new Player[n][n];
    }
    
    public Board(int xx, int yy)
    {
    	size = 3;
    	grid = new Player[3][3];
    	x=xx;
    	y=yy;
    	
    	
    }

    // Printing

    public void print(PrintWriter out) {
	int x,y;
	for (y=size-1; y >= 0; y--) {
	    out.print("    +");
	    for (x=0; x < size; x++) {
		out.print("---+");
	    }
	    out.println();
	    out.format("%2d: |", y);
	    for (x=0; x < size; x++) {
		out.format(" %s |", isEmpty(x,y) ? " " : grid[x][y]);
	    }
	    out.println();
	}
	out.print("    +");
	for (x=0; x < size; x++) {
	    out.print("---+");
	}
	out.println();
	out.print("     ");
	for (x=0; x < size; x++) {
	    out.format("%2d  ", x);
	}
	out.println();
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
     * Return true if the given location on this Board is empty.
     */
    public boolean isEmpty(int x, int y) {
	return grid[x][y] == null;
    }

    /**
     * Apply the given Move to this Board (i.e., mark the Move's
     * location on this Board with the Move's Player).
     */
    public void apply(Move move) throws TTTRuntimeException {
	int x = move.getX();
	int y = move.getY();
	if (x < 0 || x >= size || y < 0 || y >= size) {
	    throw new IllegalLocationException();
	} else if (!isEmpty(x, y)) {
	    throw new LocationOccupiedException();
	} else {
	    grid[x][y] = move.getPlayer();
	}
    }

    /**
     * Return true if this Board represents a finished game.
     * This can happen if one player has won or if there are no empty
     * spaces (that is, a draw).
     */
    public boolean isFinished() {
	if (getWinner() != null) {
	    return true;
	} else {
	    return isFull();
	}
    }

    /**
     * Return the player who has won this game (if any, else null).
     * This happens if one player has n (size) in a row horizontally,
     * vertically, or diagonally.
     */
    public Player getWinner() {
	Player p;
	p = checkHorizontals();
	if (p != null) {
	    return p;
	}
	p = checkVerticals();
	if (p != null) {
	    return p;
	}
	p = checkDiagonals();
	if (p != null) {
	    return p;
	}
	return null;
    }

    protected Player checkHorizontals() {
	for (int y=0; y < size; y++) {
	    Player p = checkHorizontal(y);
	    if (p != null) {
		return p;
	    }
	}
	return null;
    }

    protected Player checkHorizontal(int y) {
	return checkLine(0, y, 1, 0);
    }

    protected Player checkVerticals() {
	for (int x=0; x < size; x++) {
	    Player p = checkVertical(x);
	    if (p != null) {
		return p;
	    }
	}
	return null;
    }

    protected Player checkVertical(int x) {
	return checkLine(x, 0, 0, 1);
    }

    protected Player checkDiagonals() {
	Player p = checkLine(0, 0, 1, 1);
	if (p != null) {
	    return p;
	}
	p = checkLine(0, size-1, 1, -1);
	if (p != null) {
	    return p;
	}
	return null;
    }

    protected Player checkLine(int x, int y, int dx, int dy) {
	Player p = grid[x][y];
	if (p == null) {
	    return null;
	}
	while (x >= 0 && x < size && y >= 0 && y < size) {
	    if (grid[x][y] != p) {
		return null;
	    }
	    x += dx;
	    y += dy;
	}
	return p;
    }

    /**
     * Return true if this Board is full (no empty spaces).
     * This implementation is O(n^2).
     */
    public boolean isFull() {
	for (int x=0; x < size; x++) {
	    for (int y=0; y < size; y++) {
		if (isEmpty(x,y)) {
		    return false;
		}
	    }
	}
	return true;
    }

    /**
     * Return List of possible moves for given Player on this Board.
     * For TTT it doesn't matter which player is moving.
     */
    public List<Move> getPossibleMoves(Player player) {
	List<Move> moves = new ArrayList<Move>();
	for (int x=0; x < size; x++) {
	    for (int y=0; y < size; y++) {
		if (isEmpty(x, y)) {
		    moves.add(new Move(this,player, x, y));
		}
	    }
	}
	return moves;
    }

    /**
     * Return a new Board with a newly-allocated grid initialized
     * to the same values as this Board.
     */
    public Board copy() {
	Board b = new Board(size);
	for (int x=0; x < size; x++) {
	    for (int y=0; y < size;y++) {
		b.grid[x][y] = grid[x][y];
	    }

	}
    b.x = x;
    b.y = y;
	return b;
    }

    // Testing

    public static void main(String[] argv) {
	Board board = new Board();
	board.print();
    }
}
