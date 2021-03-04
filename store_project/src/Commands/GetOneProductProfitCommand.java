package Commands;

import Model.ModelManager;

public class GetOneProductProfitCommand implements Command{

	private ModelManager model;
	private String barCode;
	private String profitStr;

	public GetOneProductProfitCommand(ModelManager model, String barCode) {
		this.model = model;
		this.barCode = barCode;
		
	}

	@Override
	public void execute() throws Exception {
		
		profitStr = model.getOneProductProfit(barCode);
	}

	public String getProductProfit() {
		return profitStr;
	}

}
