package fileInputOutPackage;
import java.io.*;
public class Runner {

	public static void main(String[] args) {
		EnterSales SalesPerson = new EnterSales();
		File inputFile = SalesPerson.inputFile();
		SalesPerson.addAllServicesToFile(inputFile);
		SalesPerson.DisplayList(SalesPerson.ServicesList);
	}
}
