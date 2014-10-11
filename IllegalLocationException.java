/*
 * File: IllegalLocationException.java
 * Creator: George Ferguson
 * Created: Tue Jan 31 09:50:41 2012
 * Time-stamp: <Tue Jan 31 09:54:42 EST 2012 ferguson>
 */

/**
 * Exception thrown when a given location is not on the board.
 */
public class IllegalLocationException extends TTTRuntimeException {

    public IllegalLocationException() {
	super();
    }

    public IllegalLocationException(String detail) {
	super(detail);
    }

}
