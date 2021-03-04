package Commands;

import java.util.ArrayList;

import Model.ModelManager;

public class GetAllCastumerCRespondCommand implements Command {

	private ModelManager model;
	private ArrayList<String> allOservers;
	private String msg;



	public GetAllCastumerCRespondCommand(ModelManager model) {
		this.model = model;
	}
	
	

	@Override
	public void execute() throws Exception {
		allOservers = model.getObserverList();
		msg = model.getUpdateMsg();
	}
	
	public ArrayList<String> getallOservers(){
		return allOservers;
	}
	public String getUpdateMsg() {
		return msg;
	}

}
