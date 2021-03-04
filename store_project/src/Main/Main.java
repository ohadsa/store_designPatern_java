package Main;

import java.io.File;

import Controller.Controller;
import Model.ModelManager;
import View.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		File file = new File("products.txt");
		ModelManager theModel = new ModelManager(file);
		View theView = new View(stage);
		Controller controller = new Controller(theModel, theView);
	}
		
}
