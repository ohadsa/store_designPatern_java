package Model;


public interface Observable {
	void addObserver(Observer o);
	void deleteObserver(Observer o);
	void notifyObservers(String string) throws Exception;
	void saveResponse(String name);
	void removeReceiverArr();
}
