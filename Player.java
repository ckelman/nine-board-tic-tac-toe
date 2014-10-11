/*
 * File: Player.java
 * Creator: George Ferguson
 * Created: Tue Jan 31 09:15:25 2012
 * Time-stamp: <Tue Jan 31 10:06:59 EST 2012 ferguson>
 */

/**
 * Enumeration of the possible Players for TTT: X or O.
 */
public enum Player {

    X,
    O;

    public Player otherPlayer() {
	if (this == X) {
	    return O;
	} else {
	    return X;
	}
    }

}
