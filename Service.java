package fileInputOutPackage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class Service {
	
	enum nameOfService{
		Breakfast,Lunch,Dinner,Conference,Tea,Massage
	}
	private String nameOfCustomer;
	private String nameOfService; //Breakfast, lunch, dinner, etc...
	private float priceOfService;
	
	LocalDate date;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu"); 
	private String dateAsString;
	// "MM/dd/uuuu"
	//String[] months = {"January","February","March","April",
	//		"May","June","July","August","September","October","November","December"};
	public void inputDate(){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Date: 'MM/dd/yyyy'");
		String inputDate = in.next();
		String[] input = inputDate.split("/");
		int month = Integer.parseInt(input[0]);
		int day = Integer.parseInt(input[1]);
		int year = Integer.parseInt(input[2]);
		date = LocalDate.of(year, month, day); 
		dateAsString = date.format(formatter);	
		in.close();
	}
	//default constructor
	public Service() {
		this.nameOfCustomer = "Customer";
		this.nameOfService = "NameOfService";
		this.priceOfService = 0.0f;
		this.date = LocalDate.now();
		this.dateAsString = this.date.format(formatter); 
		
	}
	//second constructor
	public Service(String nameOfCustomer, String nameOfService, float priceOfService, LocalDate date) {
		this.nameOfCustomer = nameOfCustomer;
		this.nameOfService = nameOfService;
		this.priceOfService = priceOfService;
		this.date = date;
	}
	public String getNameOfCustomer() {
		return nameOfCustomer;
	}
	public void setNameOfCustomer(String nameOfCustomer) {
		this.nameOfCustomer = nameOfCustomer;
	}
	public String getNameOfService() {
		return nameOfService;
	}
	public void setNameOfService(String nameOfService) {
		this.nameOfService = nameOfService;
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
		return "Person:" + nameOfCustomer + "Service: " + nameOfService + ", price: " + priceOfService + ", date: "
				+ dateAsString + "]";
	}
	/**
	 * returns the string name of the month given the months number
	 * @param month
	 * @return month name
	 */
//	private String findMonth(int month) {
//		return months[month - 1];
//	}
//	
}
