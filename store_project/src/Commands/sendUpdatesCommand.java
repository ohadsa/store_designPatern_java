package Commands;

import Model.ModelManager;

public class sendUpdatesCommand implements Command {

	
	private ModelManager model;
	private String updateMsg;
	public sendUpdatesCommand(ModelManager model , String updateMsg) {
		this.model = model;
		this.updateMsg = updateMsg;
	}
	@Override
	public void execute() throws Exception {
		model.sendUpdate(updateMsg);
	}

}
