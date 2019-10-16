package fileInputOutPackage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Service {
	
	enum nameOfService{
		Breakfast,Lunch,Dinner,Conference,Tea,Massage
	}
	private String nameOfCustomer;
	private nameOfService serviceName; //Breakfast, lunch, dinner, etc...
	private float priceOfService;
	
	LocalDate date;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu"); 
	private String dateAsString;
	// "MM/dd/uuuu"
	//String[] months = {"January","February","March","April",
	//		"May","June","July","August","September","October","November","December"};
	public void inputDate(String inputDate){
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
	public void setNameOfService(String serviceName) {
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
