package Commands;

import Model.ModelManager;

public class GetAllProductsProfitStrCommand implements Command{

	private ModelManager model;
	private String profitStr;

	public GetAllProductsProfitStrCommand(ModelManager model ) {
		this.model = model;
	}
	
	@Override
	public void execute() throws Exception {
		this.profitStr = model.getAllProfitStr();	
	}

	public String getAllProduct() {
		return profitStr;
	}

}
