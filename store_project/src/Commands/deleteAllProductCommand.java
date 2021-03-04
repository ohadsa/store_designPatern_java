package Commands;

import Model.ModelManager;

public class deleteAllProductCommand implements Command{
	
	private ModelManager model;


	public deleteAllProductCommand(ModelManager model) {
		this.model = model;
	}

	@Override
	public void execute(){
		
	}
	public void DeleteAllProduct() throws Exception {
		model.deleteAllProuct();
	}
	

}
