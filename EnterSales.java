/**
 * A class to enter sales 
 * Homework Assignment: I/O Exceptions
 * @author William Gusmanov, Bryan Vu
 */
package fileInputOutPackage;

import java.util.ArrayList;
import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner; 
import javax.swing.*;

public class EnterSales {
	/**
	 * the main that runs the EnterSales Application
	 */
	public static void main(String[] args) {
		EnterSales Enter = new EnterSales();
		File inputFile = Enter.inputFile(); 
		Enter.addAllServicesToFile(inputFile);
	}//end of main
	/**
	 * used for debugging to print to console
	 * displays the service elements found in the input
	 * @param ServicesList the array list of available services 
	 */
	public void DisplayList(ArrayList<Service> ServicesList) {
		for (Service x : ServicesList) {
			System.out.println(x.getNameOfCustomer()+", "+x.getNameOfService()+", "+
					x.getPriceOfService()+", "+x.getDateAsString());
		} //end for loop
	}//end function definition
	/**
	 * asks user for the input file to write the service elements to
	 * @return the input file if it is valid. If there is an invalid file, return null
	 */
	public File inputFile() {
	JFileChooser jFileChooser = new JFileChooser(); 
		if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			String outname = jFileChooser.getSelectedFile().getAbsolutePath();
			File validFile = new File (outname); //input file path as chosen using JFileChooser 
			return validFile; 
		}//end if statement
		else return null; 
	}//end function definition
	
	/**
	 * Write services into the text file 
	 * prompts user to enter Customer name, the service, price, and date the service will be given  
	 * @param validFile the file the user chose to input to
	 */
	public void addAllServicesToFile(File validFile) {
		ArrayList<Service> ServicesList = new ArrayList<Service>();
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
						ServicesList.get(i).setNameOfCustomer(console.nextLine());
						System.out.println("Enter name of service ex.) Breakfast, Lunch, Dinner, Conference, Tea, Massage");
						ServicesList.get(i).setNameOfService(console.next()); 
						System.out.println("Enter price of service: ");
						ServicesList.get(i).setPriceOfService(console.nextFloat()); 
						System.out.println("Enter date of service MM/dd/uuuu: ");
						ServicesList.get(i++).inputDate(console.next());
						System.out.println("Would you like to enter another service? ");
						String answer = console.next();
						answer = answer.toLowerCase(); 
						continueInput = answer.charAt(0);
						if (continueInput != 'n' && continueInput != 'y') {
							throw new IOException("invalid input");
						} //end if statement
						console.nextLine(); //burn nextline 
					} // end while loop
					BufferedWriter writer = new BufferedWriter(fileWriter);
					for (i = 0; i < ServicesList.size(); i++) {
						Service service  = ServicesList.get(i);
						String serviceOutput = service.getNameOfCustomer()+";"+service.getNameOfService()+";"+
						service.getPriceOfService()+";"+service.getDateAsString(); 
						writer.write(serviceOutput);
						writer.newLine();
						writer.flush();
						} //end of for loop 
					writer.close(); 
					fileWriter.close();
					console.close();
				} // end of try  
				catch(IOException e) {
					System.out.println(e.getMessage());
				} //end of catch for IO Exceptions 
				catch(IllegalArgumentException e) {
					System.out.println("Enter a valid service.");
				}//end of catch for IllegalArguementException 
				catch (DateTimeException e) {
					System.out.println("Invalid date, please enter a valid date.");
					}//end of catch for DateTimeException
			} //end of while loop
		}//end of function definition 
	
	/**
	 * Nested class Service
	 * which contains enum of possible services, associated customer, and the float price of the service
	 * 
	 */
		public static class Service {
			enum nameOfService{
				Breakfast,Lunch,Dinner,Conference,Tea,Massage
			}//end of enum list
			private String nameOfCustomer;
			private nameOfService serviceName; //Breakfast, lunch, dinner, etc...
			private float priceOfService;
			
			LocalDate date;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu"); 
			private String dateAsString;
			/**
			 * Takes the date as a full string and sets the date as a string in the correct format
			 * Throws exception if incorrect format inside of formatter
			 * @param inputDate 
			 */
			public void inputDate(String inputDate) throws java.time.DateTimeException{
				String[] input = inputDate.split("/");
				int month = Integer.parseInt(input[0]);
				int day = Integer.parseInt(input[1]);
				int year = Integer.parseInt(input[2]);
				date = LocalDate.of(year, month, day); 
				dateAsString = date.format(formatter);	
			}//end of function definition
			
			/*
			 * Default constructor of service object
			 */
			public Service() {
				this.nameOfCustomer = "Customer";
				this.serviceName = nameOfService.Breakfast;
				this.priceOfService = 0.0f;
				this.date = LocalDate.now();
				this.dateAsString = this.date.format(formatter); 
				
			}//end of default constructor for service
			
			/*
			 * Overloaded constructor of service object
			 * @param nameOfCustomer name of the customer
			 * @param serviceName name of  the service
			 * @param priceOfService price of the service
			 * @param date date of the purchase
			 */
			public Service(String nameOfCustomer, String serviceName, float priceOfService, LocalDate date) {
				this.nameOfCustomer = nameOfCustomer;
				this.serviceName = nameOfService.valueOf(serviceName);
				this.priceOfService = priceOfService;
				this.date = date;
			}//end of overloaded constructor for service
			
			public String getNameOfCustomer() {
				return nameOfCustomer;
			}//end of getter method for name of customer
			
			public void setNameOfCustomer(String nameOfCustomer) {
				this.nameOfCustomer = nameOfCustomer;
			}//end of setter method for name of customer
			
			public nameOfService getNameOfService() {
				return serviceName;
			}//end of getter method for name of service
			
			public void setNameOfService(String serviceName) throws IllegalArgumentException {
				this.serviceName = nameOfService.valueOf(serviceName);
			}//end of setter method for name of service
			
			public float getPriceOfService() {
				return priceOfService;
			}//end of getter method for price of service
			
			public void setPriceOfService(float priceOfService) {
				this.priceOfService = priceOfService;
			}//end of setter method for price of service
			
			public LocalDate getDate() {
				return date;
			}//end of getter method for date
			
			public String getDateAsString() {
				return dateAsString;
			}//end of getter method for dateAsString
			@Override
			public String toString() {
				return "Person:" + nameOfCustomer + "Service: " + serviceName + ", price: " + priceOfService + ", date: "
						+ dateAsString + "]";
			}//end of toString
		} //end of service class
	} //end of class 
