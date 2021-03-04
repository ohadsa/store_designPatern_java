package Model;

import java.util.ArrayList;

public class Updater implements Observable{
	private static Updater updater;
	private ArrayList<Observer> clients ;
	private ArrayList<String> receivers ;
	String curUpdate ;
	
	private Updater() {
		clients = new ArrayList<>();
		receivers = new ArrayList<>();
		curUpdate = null ;
		
	}
	
	@Override
	public void addObserver(Observer o) {
		clients.add(o);
	}

	@Override
	public void deleteObserver(Observer o) {
		clients.remove(o);
	}

	@Override
	public void notifyObservers(String string) throws Exception {
		if(clients.isEmpty())
			throw new Exception("there is no Costumer in the system yet");
		if(string.equals(""))
			throw new Exception("the massage can not by empty");
		curUpdate = string;
		for(Observer o: clients) {
			if(o.wantUpdates())
				o.update(this , string);
		}
	}

	@Override
	public void saveResponse(String name) {
		receivers.add(name);
	}
	
	public void removeReceiverArr()
	{
		receivers.clear();
	}
	
	public static Updater getUpdater() {
		if(updater == null)
			updater = new Updater();
		return updater;
	}

	public void deleteAllObservers() {
		clients.clear();
	}
	
	public ArrayList<String> getAllRespones() throws Exception {
		ArrayList<String> tmp = new ArrayList<>();
		tmp.addAll(receivers);
		if(tmp.isEmpty())
			throw new Exception("no update sended \nSend an update before requesting a response");
		receivers.clear();
		return tmp;
	}
	public String getCurUpdate() throws Exception {
		if(curUpdate == null)
			throw new Exception("there is no updates in system");
		return curUpdate;
	}

}
