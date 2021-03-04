package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ChooseSortView {

	private StackPane spRoot;
	private VBox lblTgChooseAndBtn;
	private VBox radioButtons;
	private RadioButton rbAscOrder, rbDescOrder, rbInsertOrder ;
	private ToggleGroup tglRadioSortType;
	private Button choose;
	private Label lblTitle ;
	private Scene scene ;
	
	public ChooseSortView(Stage stage ) {
		
		spRoot = new StackPane();
		spRoot.setPadding(new Insets(30));
		lblTitle = new Label("Welcome to Store\n\nchoose sort order:");
		lblTitle.setEffect(new DropShadow(10, Color.GREY));
		lblTitle.setFont(new Font("Tahoma", 20));
		lblTitle.setAlignment(Pos.CENTER);
		
		rbAscOrder = new RadioButton("Ascending ");
		rbDescOrder = new RadioButton("Descending ");
		rbInsertOrder = new RadioButton("Insertion ");
		
		tglRadioSortType = new ToggleGroup();
		rbAscOrder.setToggleGroup(tglRadioSortType);
		rbDescOrder.setToggleGroup(tglRadioSortType);
		rbInsertOrder.setToggleGroup(tglRadioSortType);
		rbAscOrder.setSelected(true);
		radioButtons = new VBox(10 , rbAscOrder);
		radioButtons.getChildren().addAll(rbDescOrder , rbInsertOrder);
		choose = new Button("   choose !   ");
		lblTgChooseAndBtn = new VBox(40 , lblTitle);
		lblTgChooseAndBtn.setPadding(new Insets(25));
		lblTgChooseAndBtn.setAlignment(Pos.CENTER);
		lblTgChooseAndBtn.getChildren().add(radioButtons);
		lblTgChooseAndBtn.getChildren().add(choose);
		spRoot.getChildren().add(lblTgChooseAndBtn);
		scene = new Scene(spRoot,350, 420);
		stage.setScene(scene);
		stage.show();
		
	}

	public int getTgChoose() {
		if(tglRadioSortType.getSelectedToggle() == rbAscOrder)
			return 0;
		else if (tglRadioSortType.getSelectedToggle() == rbDescOrder)
			return 1; 
		else 
			return 2;
	}

	public void addButtonEvent(EventHandler<ActionEvent> btnEvent) {
		choose.setOnAction(btnEvent);
		
	}

	public void close() {
		this.scene.getWindow().hide();
	}
	
	

}
