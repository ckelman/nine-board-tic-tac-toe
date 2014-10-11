/*
 * File: TTTRuntimeException.java
 * Creator: George Ferguson
 * Created: Tue Jan 31 09:54:08 2012
 * Time-stamp: <Tue Jan 31 09:54:32 EST 2012 ferguson>
 */

/**
 * Base class for RuntimeExceptions thrown in TTT.
 */
abstract public class TTTRuntimeException extends RuntimeException {

    public TTTRuntimeException() {
	super();
    }

    public TTTRuntimeException(String detail) {
	super(detail);
    }

}
