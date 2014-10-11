/*
 * File: LocationOccupiedException.java
 * Creator: George Ferguson
 * Created: Tue Jan 31 09:52:28 2012
 * Time-stamp: <Tue Jan 31 09:54:52 EST 2012 ferguson>
 */

/**
 * Exception thrown when a move is made and the location is not empty.
 */
public class LocationOccupiedException extends TTTRuntimeException {

    public LocationOccupiedException() {
	super();
    }

    public LocationOccupiedException(String detail) {
	super(detail);
    }

}
