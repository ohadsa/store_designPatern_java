package Commands;

import Model.ModelManager;

public class GetSortTypeCommand implements Command{
	ModelManager model;
	private int sortType;
	
	public GetSortTypeCommand(ModelManager model) {
		this.model = model;
	}
	@Override
	public void execute() {
		model.setSortType(sortType);
	}
	public void setSortType(int sortType) {
		this.sortType = sortType;

	}

	

}
