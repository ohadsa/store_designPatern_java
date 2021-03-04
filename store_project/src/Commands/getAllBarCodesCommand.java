package Commands;

import Model.ModelManager;

public class getAllBarCodesCommand implements Command{
	
	private ModelManager model;
	private String[] allBarCodes;

	public getAllBarCodesCommand(ModelManager model) {
		this.model = model;
	}
	
	@Override
	public void execute() throws Exception {
		this.allBarCodes = model.getAllBarcodes();
		
	}
	public String[] getAllbarCodes() {
		return allBarCodes;
	}

}
