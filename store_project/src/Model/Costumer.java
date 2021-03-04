package Model;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Costumer implements Observer{
	private String name ;
	private String phoneNumber ;
	private boolean wantUpdates ;
	private String updateMsg;
	
	public Costumer(String name , String phoneNumber , boolean wantUpdates) throws Exception {
		if(!phoneFormatOk(phoneNumber))
			throw new Exception("phone number format is not ok");
		if( wantUpdates && phoneNumber.equals(""))
			throw new Exception("updading costumer option is selected\nadd phone number or cancel updating option");
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.wantUpdates = wantUpdates;
	}

	private boolean phoneFormatOk(String phoneNumber) {
		if(phoneNumber.equals(""))
			return true; //phone can be empty - there is check if update bool is selected than phone cant be empty
		String[] parts = phoneNumber.split("-");
		if(parts.length != 2 || parts[0].length()!=3 || parts[1].length() != 7)
			return false;
		try {
			Integer.parseInt(parts[0] + parts[1]);
		}catch (Exception e) {
			return false;
		}
		 return true;
	}

	public Costumer(Costumer costumer) {
		this.name = new String(costumer.name);
		this.phoneNumber = new String(costumer.phoneNumber);
		this.wantUpdates = costumer.wantUpdates;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setWantUpdates(boolean wantUpdates) {
		this.wantUpdates = wantUpdates;
	}

	public void saveCustomerToFile(RandomAccessFile writer) throws IOException {
		writer.writeUTF(name);
		writer.writeUTF(phoneNumber);
		writer.writeBoolean(wantUpdates);
		
	}

	@Override
	public String toString() {
		return "\n    Costumer " + name + "   " + phoneNumber  + ( (wantUpdates == true) ?  "   want updates" :  "   dont want updates");
	}

	@Override
	public void update(Observable o, String updateMsg) {
		this.updateMsg = updateMsg;
		o.saveResponse(name);
		
		
	}

	@Override
	public boolean wantUpdates() {
		return wantUpdates;
	}
	
	public String getCurrentUpdateMsg() {
		return updateMsg;
	}
	
	

}
