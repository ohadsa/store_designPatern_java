package Commands;

import Model.ModelManager;

public class GetOneProductStrCommand implements Command{

	
	ModelManager model ;
	String productBarCode;
	String productStr;
	public GetOneProductStrCommand(ModelManager model , String barCode) {
		this.model = model;
		this.productBarCode = barCode;
	}
	@Override
	public void execute() throws Exception {
		productStr = model.getProductStrByBarCode(productBarCode);
	}
	public String getProduct() {
		
		return productStr;
	}

}
