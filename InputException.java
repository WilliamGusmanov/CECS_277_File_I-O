/**
 * A class to create an input exception
 * Homework Assignment : I/O Exceptions
 * @author William Gusmanov, Bryan Vu
 */

package fileInputOutPackage;

public class InputException extends Exception {

InputException(){
	super("invalid input exception.");
	}
InputException(String input){
	super(input);
}
}
