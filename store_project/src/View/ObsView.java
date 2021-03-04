package View;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ObsView {

	private GridPane gp;
	private VBox vb;
	private Label lblTitle;
	private Scene scene;
	private Stage stage;
	private Label lblText;
	private ScrollPane sp;
	private Label txt;
	private Label lblmsg;
	private Label lblFinished;;
	
	public ObsView(String msg) {
		this.stage = new Stage();
		gp = new GridPane();
		gp.setPadding(new Insets(30));
		lblTitle = new Label("costumer that respon to the update: ");
		lblmsg = new Label("the update that sent:\n" + msg);
		lblText = new Label("printing...");
		lblFinished = new Label("printing finished now you can close");
		lblFinished.setVisible(false);
		lblTitle.setEffect(new DropShadow(15, Color.GREEN));
		lblTitle.setFont(new Font("David", 15));
		lblTitle.setAlignment(Pos.TOP_CENTER);

		vb = new VBox(10);

		sp = new ScrollPane();
		txt = new Label();
		txt.setFont(Font.font(18));
		sp.setContent(txt);
		sp.setMaxWidth(300);
		sp.setMaxHeight(300);
		sp.setMinHeight(300);
		sp.setMinWidth(300);
		vb.getChildren().addAll(lblTitle, lblmsg, sp,lblFinished ,lblText);
		vb.setAlignment(Pos.CENTER);
		scene = new Scene(vb, 350, 500);
		stage.setScene(scene);
		stage.setTitle("new");
		stage.setX(20);
		stage.setY(20);
		stage.show();
	}
	
	
	public void setText(String txt) {
		this.txt.setText(txt);
		
	}

	


	public void closeView() {
		this.scene.getWindow().hide();
	}


	public void setPrntFinished() {
		lblText.setVisible(false);
		lblFinished.setVisible(true);
	}

}
