package interpreter.virtualmachine;
/**
 * Exception for when there is any illegal
 * accesses into the runtimestack class.
 * This exception is used to bubble up all
 * exceptions that can be thrown by any
 * RunTimeStack class runctions.
 *
 * Note that we are only exposing the constructor
 * from Exception that accepts a Throwable object.
 * This is to ensure we correctly chain exceptions
 * and not hide any information.
 *
 * DO NOT ADD ANY ADDITIONAL Constructors.
 */
public class RuntimeStackIllegalAccess extends Exception {
    public RuntimeStackIllegalAccess(Exception ex) {
        super(ex);
    }

}
