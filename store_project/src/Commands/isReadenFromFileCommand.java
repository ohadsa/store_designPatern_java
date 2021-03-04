package Commands;

import Model.ModelManager;

public class isReadenFromFileCommand implements Command{

	private ModelManager model ;
	private boolean isReadenFromFile ;
	
	public isReadenFromFileCommand(ModelManager model ) {
		this.model = model ;
	}
	
	@Override
	public void execute() throws Exception {
		isReadenFromFile = model.isloadedDataFromFile();
	}
	
	public boolean getIfreadFromFile() {
		return isReadenFromFile;
	}

}
