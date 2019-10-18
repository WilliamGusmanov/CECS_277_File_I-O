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
		
	}
	/**
	 * Prompts user to choose the inputFile that contains service information
	 * @return
	 */
	public File inputFile() {
		JFileChooser jFileChooser = new JFileChooser(); 
			if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				//assume sales.txt exists already in file directory
				String outname = jFileChooser.getSelectedFile().getAbsolutePath();
				File validFile = new File (outname); //input file path as chosen using JFileChooser 
				return validFile; 
			}
			else return null; 
		}
	/**
	 * Takes in the file used to put in service information
	 * uses a Hashmap to store the name of the service as the key, with the string of all matching services as the String element
	 * creates new files with the name of the service 
	 * @param validFile
	 */
	public void CreateNewServiceFiles(File validFile) {
		HashMap<String, String> list = new HashMap<String, String>();  
		try {
			Scanner textFile = new Scanner(validFile);
			while (textFile.hasNext()) { //for every line in the textfile
				String input = textFile.nextLine();
				String[] splitInput = input.split(";");
				//isValid(splitInput); //check validation
				String currentServiceName = splitInput[1]; 
				list.computeIfPresent(currentServiceName, (key, val) -> val.concat(input+"\n"));
				list.putIfAbsent(currentServiceName, input+"\n");
			}
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
			}
		} // end of function
	
	/*
	public void isValid(String[] splitInput) throws IOexception {
	        Service serviceTest = new Service();
	        for (int i = 0; i < splitInput[2].length(); i++) { // checking for correct price format
	            if (splitInput[2].charAt(i) == '.') {
	                return false;
	            }
	        }
	        serviceTest.setNameOfService(splitInput[1]); //Will throw error if not valid enum
	        return true;
	    }
	    */
}
