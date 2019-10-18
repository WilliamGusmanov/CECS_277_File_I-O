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
	 * @param args
	 */
	public static void main(String[] args) {
		EnterSales Enter = new EnterSales();
		File inputFile = Enter.inputFile(); 
		Enter.addAllServicesToFile(inputFile);
	}
	/**
	 * used for debugging to print to console
	 * displays the service elements found in the input
	 * @param ServicesList
	 */
	public void DisplayList(ArrayList<Service> ServicesList) {
		for (Service x : ServicesList) {
			System.out.println(x.getNameOfCustomer()+", "+x.getNameOfService()+", "+
					x.getPriceOfService()+", "+x.getDateAsString());
		} //end for loop
	}//end function definition
	/**
	 * asks user for the input file to write the service elements to
	 * @return
	 */
	public File inputFile() {
	JFileChooser jFileChooser = new JFileChooser(); 
		if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			String outname = jFileChooser.getSelectedFile().getAbsolutePath();
			File validFile = new File (outname); //input file path as chosen using JFileChooser 
			return validFile; 
		}
		else return null; 
	}
	/**
	 * Write services into the text file 
	 * prompts user to enter Customer name, the service, price, and date the service will be given  
	 * @param validFile, the file the user chose to input to
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
						ServicesList.get(i).inputDate(console.next());
						i++; 
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
				} //end of catch 1
				catch(IllegalArgumentException e) {
					System.out.println("Enter a valid service.");
				}
				catch (DateTimeException e) {
					System.out.println("Invalid date, please enter a valid date.");
					}
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
			}
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
			} 
			//default constructor
			public Service() {
				this.nameOfCustomer = "Customer";
				this.serviceName = nameOfService.Breakfast;
				this.priceOfService = 0.0f;
				this.date = LocalDate.now();
				this.dateAsString = this.date.format(formatter); 
				
			}
			//second constructor
			public Service(String nameOfCustomer, String serviceName, float priceOfService, LocalDate date) {
				this.nameOfCustomer = nameOfCustomer;
				this.serviceName = nameOfService.valueOf(serviceName);
				this.priceOfService = priceOfService;
				this.date = date;
			}
			public String getNameOfCustomer() {
				return nameOfCustomer;
			}
			public void setNameOfCustomer(String nameOfCustomer) {
				this.nameOfCustomer = nameOfCustomer;
			}
			public nameOfService getNameOfService() {
				return serviceName;
			}
			public void setNameOfService(String serviceName) throws IllegalArgumentException {
				this.serviceName = nameOfService.valueOf(serviceName);
			}
			public float getPriceOfService() {
				return priceOfService;
			}
			public void setPriceOfService(float priceOfService) {
				this.priceOfService = priceOfService;
			}
			public LocalDate getDate() {
				return date;
			}
			public String getDateAsString() {
				return dateAsString;
			}
			@Override
			public String toString() {
				return "Person:" + nameOfCustomer + "Service: " + serviceName + ", price: " + priceOfService + ", date: "
						+ dateAsString + "]";
			}
		} //end of service class
	} //end of class 
