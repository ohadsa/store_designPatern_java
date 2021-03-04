package Main;

import java.io.File;
import Model.Store;

import Model.Product;

public class RunThisToBackToDefoultDataInFile {
	public static void main(String[] args) {
		
		
		try {
			fileTests();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	public static void fileTests() throws Exception {
		File file = new File("products.txt");
		file.delete();
		Store ms;
		ms = new Store(file);

		Product p1 = new Product("bamba", 13, 19, "Ross Geller", "052-6186160", true);
		Product p2 = new Product("bisli", 11, 20,"Monica Geller", "052-6144949", false);
		Product p3 = new Product("kotej", 15, 21, "Chandler Bing", "054-6119990", true);
		Product p4 = new Product("milk", 9, 24, "Phoebi Bufe", "052-9186160", true);
		Product p5 = new Product("battery", 7, 28, "Joey Tribiani", "052-6444130", false);
		Product p6 = new Product("Soda", 4, 6, "Rachel Green", "052-6411230", true);
		ms.setSortType(0);

		ms.addOrUpdateProduct("AAA123", p3);
		ms.addOrUpdateProduct("BBB123", p6);
		ms.addOrUpdateProduct("CCC123", p2);
		ms.addOrUpdateProduct("DDD123", p5);
		ms.addOrUpdateProduct("EEE123", p4);
		ms.addOrUpdateProduct("FFF123", p1);

	}

}