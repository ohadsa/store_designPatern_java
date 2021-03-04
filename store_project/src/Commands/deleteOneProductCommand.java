package Commands;

import Model.ModelManager;

public class deleteOneProductCommand implements Command{

	
	ModelManager model ;
	String productBarCode;
	
	public deleteOneProductCommand(ModelManager model , String barCode) {
		this.model = model;
		this.productBarCode = barCode;
	}
	
	@Override
	public void execute() throws Exception {
		model.deleteProduct(productBarCode);
	}
	
	public int getNumberOfProducts() {
		
		return model.getNumberOfProducts();
	}
}
