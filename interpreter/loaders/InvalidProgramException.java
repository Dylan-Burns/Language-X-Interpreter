package interpreter.loaders;

/**
 * Exception for when loadCode fails.
 * This exception is used to bubble up all
 * exceptions that can be thrown by loadCodes.
 *
 * Note that we are only exposing the constructor
 * from Exception that accepts a Throwable object.
 * This is to ensure we correctly chain exceptions
 * and not hide any information.
 *
 * DO NOT ADD ANY ADDITIONAL Constructors.
 */
public class InvalidProgramException extends Exception {
    public InvalidProgramException(Throwable ex) {
        super(ex);
    }
}
