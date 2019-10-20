/**
 * A class that takes an input file and creates a service file from it
 * Homework Assignment: I/O Exceptions
 * @author William Gusmanov, Bryan Vu
 */
package fileInputOutPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class DistributeSales {
	
	public static void main(String[] args) {
		DistributeSales Distribute = new DistributeSales();
		File inputFile = Distribute.inputFile();
		Distribute.CreateNewServiceFiles(inputFile);	
	}//end of main
	/**
	 * Prompts user to choose the inputFile that contains service information
	 * @return the input file if it is valid. If there is an invalid file, return null
	 */
	public File inputFile() {
		JFileChooser jFileChooser = new JFileChooser(); 
			if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				//assume sales.txt exists already in file directory
				String outname = jFileChooser.getSelectedFile().getAbsolutePath();
				File validFile = new File (outname); //input file path as chosen using JFileChooser 
				return validFile; 
			}//end of if statement 
			else return null; 
		}//end of function definition
	/**
	 * Takes in the file used to put in service information
	 * uses a Hashmap to store the name of the service as the key, with the string of all matching services as the String element
	 * creates new files with the name of the service 
	 * @param validFile the file that will be used to be put in service information
	 */
	public void CreateNewServiceFiles(File validFile) {
		HashMap<String, String> list = new HashMap<String, String>();  
		try {
			Scanner textFile = new Scanner(validFile);
			while (textFile.hasNext()) { //for every line in the textfile
				String input = textFile.nextLine();
				String[] splitInput = input.split(";");
				isValid(splitInput); //check validation throws exception 
				String currentServiceName = splitInput[1]; 
				list.computeIfPresent(currentServiceName, (key, val) -> val.concat(input+"\n"));
				list.putIfAbsent(currentServiceName, input+"\n");
			}//end of while loop
			textFile.close();
		File validOutFile = null;
		PrintWriter out = null;				
			for (String currentServiceName : list.keySet()) {
				validOutFile = new File(validFile.getParent()+"/"+currentServiceName+".txt"); 
				out = new PrintWriter(validOutFile);
				out.write(list.get(currentServiceName));
				out.close();
			} //end for loop 
		} //end try block
		catch (FileNotFoundException e) {
			System.out.println("file not found.");
			}//end of catch for FileNotFoundException
		catch (InputException e) {
			System.out.println(e.getMessage());
		}//end of catch for InputException
		} // end of function
	//Name, service, price, date
	/**
	 * Checks validation of values read in the input file
	 * @param splitInput An array of the parts of the input split up
	 * @throws FloatNumberException invalid price value
	 */
	public void isValid(String[] splitInput) throws InputException {
	    String[] services = new String[] {"Breakfast","Lunch","Dinner","Conference","Tea","Massage"};
	    //Check if its a name
	    for (int i = 0; i < splitInput[0].length(); i++) { // checking for correct price format
            if (!Character.isAlphabetic(splitInput[0].charAt(i)) && !Character.isWhitespace(splitInput[0].charAt(i))) {
                throw new InputException("Invalid name input"); 
            }//end if
        }//end for loop
	    //check if its a service 
	    boolean found = false; 
	    for (int i = 0; i < services.length; i++) { // checking for correct price format
            if (services[i].equals(splitInput[1])) {
            	found = true; 
            }//end if
	    }//end for loop
        if (!found) {
        	throw new InputException("Invalid float value input.");
        }//end if
	    //check the price
	    for (int i = 0; i < splitInput[2].length(); i++) { // checking for correct price format
	            if (splitInput[2].charAt(i) != '.' && !Character.isDigit(splitInput[2].charAt(i))) {
	                throw new InputException("Invalid price input."); 
	            }//end if
	        }//end for loop
	    //check the date
	    for (int i = 0; i < splitInput[3].length(); i++) { // checking for correct price format
            if (splitInput[3].charAt(i) != '/' && !Character.isDigit(splitInput[3].charAt(i))) {
                throw new InputException("Invalid date input."); 
            }//end if
        }//end for loop
	}//end isValid function defintion
}//end class definition
