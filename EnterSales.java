package fileInputOutPackage;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner; 
import javax.swing.*; 
public class EnterSales {
	
	ArrayList<Service> ServicesList = new ArrayList<Service>();
	public void Input() {
	JFileChooser jFileChooser = new JFileChooser(); 
	try {
		if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			//assume sales.txt exists already in file directory
			String outname = jFileChooser.getSelectedFile().getAbsolutePath();
			File validFile = new File (outname); //input file path as chosen using JFileChooser 
			Scanner textFile = new Scanner(validFile);
			System.out.println("Regular File Found!");
			}
		}
	catch(FileNotFoundException exception) { //will be thrown if File is not found 
		System.out.println("Invalid File!");
		}

	}
}