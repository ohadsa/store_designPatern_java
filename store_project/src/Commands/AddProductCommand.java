package Commands;

import Model.ModelManager;

public class AddProductCommand implements Command{

	private String barCode , pName , cName ,cPhone ;
	private int cost , cPrice ;
	private boolean wantUpdates ;
	private ModelManager model;
	private int addOrUpdate ;
	
	public AddProductCommand(ModelManager model ) {
		this.model = model ;
	}
	
	@Override
	public void execute() throws Exception {
		addOrUpdate = model.addProduct(barCode ,pName ,cName ,cPhone , cost , cPrice , wantUpdates);
	}
	public void getAllProductFields(String barCode ,String pName ,String cName ,String cPhone , int cost , int cPrice , boolean wantUpdates) {
		this.barCode = barCode; 
		this.pName = pName;
		this.cName = cName;
		this.cPhone = cPhone;
		this.cost = cost;
		this.cPrice = cPrice;
		this.wantUpdates = wantUpdates;
		
	}

	public int getIfAddedOrUpdated() {
		return addOrUpdate;
		
	}
}
