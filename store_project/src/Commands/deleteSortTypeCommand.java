package Commands;

import Model.ModelManager;

public class deleteSortTypeCommand implements Command{
	
	ModelManager model;
	
	public deleteSortTypeCommand(ModelManager model) {
		this.model = model;
	}

	@Override
	public void execute() {
		model.deleteSortType();
		
	}

}
