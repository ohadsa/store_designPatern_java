package Commands;

import Model.ModelManager;
import Model.Store.StoreMemento;

public class deleteLastProductCommand implements Command{

	private ModelManager model;
	private StoreMemento lastVersionMemento;
	public deleteLastProductCommand(ModelManager model) {
		this.model = model;
	}
	
	@Override
	public void execute() {
		lastVersionMemento = model.getMemento();
	}

	public void restorePrevStatus() throws Exception {
		model.restorePrevStatus(lastVersionMemento);
		
	}
	
	public int getNumberOfProducts() {
		return model.getNumberOfProducts();
	}
	

}
