package fileInputOutPackage;

public class InputException extends Exception {

InputException(){
	super("invalid input exception.");
	}
InputException(String input){
	super(input);
}
}
