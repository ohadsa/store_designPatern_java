package Model;

import Model.Store.StoreMemento;

public class StoreCareTaker {
	private StoreMemento memento;
	
	public StoreCareTaker() {
		super();
	}
	public StoreCareTaker(StoreMemento memento) {
		this.memento = memento;
	}
	
	public void update(StoreMemento memento) {
		this.memento = memento;
	}
	public StoreMemento getMemento() {
		return memento;
	}
}
