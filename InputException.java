/**
 * A class to create an input exception
 * Homework Assignment : I/O Exceptions
 * @author William Gusmanov, Bryan Vu
 */

package fileInputOutPackage;

public class InputException extends Exception {

/*
 * default constructor for Input Exception
 */
InputException(){
	super("invalid input exception.");
	}
/*
 * overloaded constructor for Input Exception
 * @param input the user's input, that will be reiterated when the exception is thrown
 */
InputException(String input){
	super(input);
}
}
