package fileInputOutPackage;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner; 
import javax.swing.*; 
public class EnterSales {
	
	ArrayList<Service> ServicesList = new ArrayList<Service>();
	
	public void DisplayList(ArrayList<Service> ServicesList) {
		for (Service x : ServicesList) {
			System.out.println(x.getNameOfCustomer()+", "+x.getNameOfService()+", "+
					x.getPriceOfService()+", "+x.getDateAsString());
		}
	}
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
	 * read list from file 
	 * @param validFile
	 */
	public void readServicesFromFile(File validFile) {
		try {
			Scanner textFile = new Scanner(validFile);
			String customerName = textFile.next();
			String serviceName = textFile.next();
			float Price = textFile.nextFloat();
			String Date = textFile.nextLine();
			
			System.out.println("Regular File Found!");	 
			textFile.close();
		}
		catch(FileNotFoundException e) {
				System.out.println("Invalid File!");
			}
		}
	/**
	 * Write services into the text file 
	 * @param validFile
	 */
	public void addAllServicesToFile(File validFile) {
			Scanner console = new Scanner(System.in);
			boolean checkingFile = true;
			int i = 0; 
			while(checkingFile) {
				try {
					PrintWriter fileWriter = new PrintWriter(validFile);
					System.out.println("Print File Found!");
					checkingFile = false;
					char continueInput = 'y';
					while (continueInput == 'y') {
						ServicesList.add(new Service()); 
						System.out.println("Enter name of customer");
						ServicesList.get(i).setNameOfCustomer(console.nextLine()); //Set name of customer 
						System.out.println("Enter name of service ex.) Breakfast, Lunch, Dinner, Conference, Tea, Massage");
						
						ServicesList.get(i).setNameOfService(console.next()); //set name of service 
						System.out.println("Enter price of service: ");
						ServicesList.get(i).setPriceOfService(console.nextFloat()); 
						System.out.println("Enter date of service MM/dd/uuuu: ");
						ServicesList.get(i).inputDate(console.next());
						i++; 
						System.out.println("Would you like to enter another service? ");
						String answer = console.next();
						answer = answer.toLowerCase(); 
						continueInput = answer.charAt(0);
						if (continueInput != 'n' && continueInput != 'y') {
							throw new IOException("invalid input");
						}
						console.nextLine(); //burn nextline 
					}
					for (i = 0; i < ServicesList.size(); i++) {
						Service service  = ServicesList.get(i);
						String serviceOutput = service.getNameOfCustomer()+", "+service.getNameOfService()+", "+
						service.getPriceOfService()+", "+service.getDateAsString(); 
						fileWriter.write(serviceOutput);
						fileWriter.flush();
					}
					fileWriter.close();
					console.close();
				} 
				catch(IOException e) {
					System.out.println(e.getMessage());
				}
				catch (FileNotFoundException e) {
					System.out.println("Invalid File!");
				} //end of catch 
			}//end of while loop
		//create a service 
		//add it to the textFile
		}
	}