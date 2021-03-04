package Model;


public interface Observer {

	void update(Observable o, String updateMsg);
	boolean wantUpdates();

	
}
