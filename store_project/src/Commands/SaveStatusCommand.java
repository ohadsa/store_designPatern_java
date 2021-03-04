package Commands;

import Model.ModelManager;

public class SaveStatusCommand implements Command{

	private ModelManager model;

	public SaveStatusCommand(ModelManager model) {
		this.model = model;
	}

	@Override
	public void execute() throws Exception {
		model.SaveProductsStatus();
		
	}

}
