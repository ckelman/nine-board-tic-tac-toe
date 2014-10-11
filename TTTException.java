/*
 * File: TTTException.java
 * Creator: George Ferguson
 * Created: Tue Jan 31 09:40:24 2012
 * Time-stamp: <Tue Jan 31 09:41:12 EST 2012 ferguson>
 */

/**
 * Base class for Exceptions thrown in TTT.
 */
abstract public class TTTException extends Exception {

    public TTTException() {
	super();
    }

    public TTTException(String detail) {
	super(detail);
    }

}
