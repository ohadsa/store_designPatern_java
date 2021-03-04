package Model;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Product {
	
	private String name;
	private int costPrice;
	private int sellprice;
	private Costumer costumer;

	
	public Product(Product value) {
		this.name = new String(value.name);
		this.costPrice = value.costPrice;
		this.sellprice = value.sellprice;
		this.costumer = new Costumer(value.costumer);
	}

	public Product(String pName, int cost, int cPrice, String cName, String cPhone, boolean wantUpdates) throws Exception {
		if(costPrice < 0 || sellprice < 0 )
			throw new Exception("Price cant be less than 0 ");
		if(!isOnlyLetters(pName) || !isOnlyLetters(cName))
			throw new Exception("Name can be only letters ");
		this.name = pName;
		this.costPrice = cost;
		this.sellprice = cPrice;
		
		this.costumer = new Costumer(cName , cPhone , wantUpdates);
	}



	private boolean isOnlyLetters(String name) {
		for(int i = 0 ; i < name.length() ; i++ )
			if((!Character.isAlphabetic(name.charAt(i))) && name.charAt(i) != ' ' )
				return false;
					
		return true;
	}

	@Override
	public String toString() {
		return   "Name: "+ name + "   cost: " + costPrice + "   sell: " + sellprice + ""
				+ costumer +"\n";
	}



	public void saveProductToFile(RandomAccessFile writer) throws IOException {
		writer.writeUTF(name);
		writer.writeInt(costPrice);
		writer.writeInt(sellprice);
		costumer.saveCustomerToFile(writer);
	}
	public int getProfit() {
		return sellprice-costPrice;
	}



	public Observer getCostumer() {
		
		return costumer;
	}



	public String getProductName() {
		
		return name;
	}

}
