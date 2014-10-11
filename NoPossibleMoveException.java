/*
 * File: NoPossibleMoveException.java
 * Creator: George Ferguson
 * Created: Tue Jan 31 11:22:48 2012
 * Time-stamp: <Tue Jan 31 11:23:12 EST 2012 ferguson>
 */

/**
 * Exception thrown when no move is possible in a state.
 */
public class NoPossibleMoveException extends TTTRuntimeException {

    public NoPossibleMoveException() {
	super();
    }

    public NoPossibleMoveException(String detail) {
	super(detail);
    }

}
