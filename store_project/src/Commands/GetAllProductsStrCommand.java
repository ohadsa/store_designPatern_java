package Commands;

import Model.ModelManager;

public class GetAllProductsStrCommand implements Command{

	
	private ModelManager model ;
	private String allProducts;
	
	public GetAllProductsStrCommand(ModelManager model) {
		this.model = model;
	}

	@Override
	public void execute() throws Exception {
		allProducts = model.getAllStrProducts();
	}
	public String getAllProduct(){
		return allProducts;
	}

}
