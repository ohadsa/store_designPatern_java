package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class View {

	private Stage stage;
	private ChooseSortView cb;

	private Menu mProduct;
	private Menu mDelete;
	private Menu mProfit;
	private Menu mUpdates;

	private MenuItem mAddProduct;
	private MenuItem mShowProd;
	private MenuItem mshowProductBiBarCode;
	private MenuItem mDelateByBarCode;
	private MenuItem mDeleteLast;
	private MenuItem mDeleteAll;
	private MenuItem mShowProfitByBarCode;
	private MenuItem mProfitFromAllProd;
	private MenuItem mUpdateOp;
	private MenuItem mClientRespons;
	private MenuBar mb;
	private VBox vb;
	private ComboBox<String> cmboBarcodes;
	private Button btnOneProductStr;
	private Scene sc ;

	// Adding Product details
	private Label lblProductName, lblbarCode, lblCost, lblsellPrice, lblPhone;
	private TextField tfbarCode, tfProductName, tfCost, tfCostumerPrice;
	private CheckBox chbWantUpdate;
	private Button btnAddProduct;
	private Label lblCostumerName;
	private TextField tfcName;
	private TextField tfPhone;
	private Button btnOneProductDelete;
	private Button btnDeleteLast;
	private Button btnDeleteAll;
	private Button btnOneProductProfit;
	private Button btnSendUpdate;
	private TextArea tfUpdateMsg;
	private ObsView obsView;

	public View(Stage stage) {
		this.stage = stage;
	}
	public void loadChoosingSortView() {
		cb = new ChooseSortView(stage);
	}
	public void loadStoreView() {
		stage.setTitle("--- STORE SYSTEM ----");

		mProduct = new Menu("Product options");
		mDelete = new Menu("Delete options");
		mProfit = new Menu("Profit details");
		mUpdates = new Menu("Updates options");
		mAddProduct = new MenuItem("Add Product");
		mShowProd = new MenuItem("Show all products");
		mshowProductBiBarCode = new MenuItem("Show By products barCode");
		mDelateByBarCode = new MenuItem("Delete By Barcode");
		mDeleteLast = new MenuItem("Delete last ( undo )");
		mDeleteAll = new MenuItem("Delete all products");
		mShowProfitByBarCode = new MenuItem("Show profit By barCode");
		mProfitFromAllProd = new MenuItem("Show profit from all products");
		mUpdateOp = new MenuItem("Send update to all costumers");
		mClientRespons = new MenuItem("get all respons from clients");

		mProduct.getItems().addAll(mAddProduct, mShowProd, mshowProductBiBarCode);
		mDelete.getItems().addAll(mDelateByBarCode, mDeleteLast, mDeleteAll);
		mProfit.getItems().addAll(mShowProfitByBarCode, mProfitFromAllProd);
		mUpdates.getItems().addAll(mUpdateOp, mClientRespons);

		mb = new MenuBar();
		mb.getMenus().addAll(mProduct, mDelete, mProfit, mUpdates);
		vb = new VBox(mb);
		sc = new Scene(vb, 700, 260);
		stage.setScene(sc);
		stage.show();
	}

	public void addMouseClickedEventToMenu(EventHandler<ActionEvent> mouseClickedEvent) {

		mAddProduct.setOnAction(mouseClickedEvent);
		mShowProd.setOnAction(mouseClickedEvent);
		mshowProductBiBarCode.setOnAction(mouseClickedEvent);
		mDelateByBarCode.setOnAction(mouseClickedEvent);
		mDeleteLast.setOnAction(mouseClickedEvent);
		mDeleteAll.setOnAction(mouseClickedEvent);
		mShowProfitByBarCode.setOnAction(mouseClickedEvent);
		mProfitFromAllProd.setOnAction(mouseClickedEvent);
		mUpdateOp.setOnAction(mouseClickedEvent);
		mClientRespons.setOnAction(mouseClickedEvent);
	}

	public int getSortType() {
		return cb.getTgChoose();
	}

	public void addEventToSortChoseView(EventHandler<ActionEvent> btnEvent) {
		cb.addButtonEvent(btnEvent);

	}

	public void closeChoosingView() {
		cb.close();

	}

	public void showAddingMenu() {
		GridPane gp = new GridPane();
		lblbarCode = new Label("BARCODE:\n(letters & digits)");
		lblProductName = new Label("Product name:\n(letters)");
		lblCost = new Label("cost :\n(number)");
		lblsellPrice = new Label("sell price:\n(number)");
		lblCostumerName = new Label("Costumer name:\n(letters)");
		lblPhone = new Label("Phone number:\n(format: xxx-xxxxxxx )");
		tfbarCode = new TextField();
		tfProductName = new TextField();
		tfCost = new TextField();
		tfCostumerPrice = new TextField();
		tfcName = new TextField();
		tfPhone = new TextField();

		chbWantUpdate = new CheckBox("Want updates");
		btnAddProduct = new Button("Press to add details");

		gp.add(lblbarCode, 0, 2);
		gp.add(tfbarCode, 1, 2);
		gp.add(lblProductName, 2, 2);
		gp.add(tfProductName, 3, 2);
		gp.add(lblCost, 0, 3);
		gp.add(tfCost, 1, 3);
		gp.add(lblsellPrice, 2, 3);
		gp.add(tfCostumerPrice, 3, 3);
		gp.add(chbWantUpdate, 4, 4);
		gp.add(lblCostumerName, 0, 4);
		gp.add(tfcName, 1, 4);
		gp.add(lblPhone, 2, 4);
		gp.add(tfPhone, 3, 4);
		gp.add(btnAddProduct, 1, 6);

		gp.setAlignment(Pos.BASELINE_CENTER);
		gp.setHgap(15);
		gp.setVgap(15);
		vb.getChildren().add(gp);

	}
	
	public void ShowString(String details) {
		ScrollPane sp = new ScrollPane();
		Label txt = new Label(details);
		txt.setFont(Font.font(14));
		sp.setContent(txt);
		sp.setMinWidth(350);
		sp.setMinHeight(232);
		vb.getChildren().add(sp);
	}

	public void clear() {
		vb.getChildren().clear();
		vb.getChildren().add(mb);
	}

	public void addEventaddingDetailsBybtn(EventHandler<ActionEvent> addingDetailsBybtn) {
		btnAddProduct.setOnAction(addingDetailsBybtn);

	}

	public String getBarcode() throws Exception {
		if (tfbarCode.getText().equals(""))
			throw new Exception("barcode can not be Empty");
		return tfbarCode.getText();
	}

	public String getProductName() {

		try {
			return tfProductName.getText();
		} catch (Exception e) {
			return "";
		}
	}

	public String getCostumerName() {
		try {
			return tfcName.getText();
		} catch (Exception e) {
			return "";
		}
	}

	public String getPhone() {

		try {
		return tfPhone.getText();
		}
		catch(Exception e)
		{
			return "";
		}
	}

	public int getCost() throws Exception {
		
		try {
			if(tfCost.getText().equals(""))
				return 0;
			return Integer.parseInt(tfCost.getText());
		} catch (Exception e) {
			throw new Exception("cost need to be integer !");
		}
	}

	public int getCostumerPrice() throws Exception {
		try {
			if(tfCostumerPrice.getText().equals(""))
				return 0;
			return Integer.parseInt(tfCostumerPrice.getText());
		} catch (Exception e) {
			throw new Exception("sell price need to be integer !");
		}
		
		
	}

	public boolean getWantUpdates() {
		return chbWantUpdate.isSelected();
	}

	public void showErrorAlert(String msg) {
		Alert a = new Alert(Alert.AlertType.ERROR);
		a.setContentText(msg);
		a.show();
	}
	
	public void showSuccsessAlert(String msg) {
		Alert a = new Alert(Alert.AlertType.INFORMATION);
		a.setContentText(msg);
		a.show();
	}

	public void showSearchForProductWindow(String[] barcodes) {
		cmboBarcodes = new ComboBox<>();
		
		Label lblChoose = new Label("choose barcode :");
		cmboBarcodes.getItems().addAll(barcodes);
		if(barcodes.length > 0)
			cmboBarcodes.setValue(barcodes[0]);
		btnOneProductStr = new Button("Press to search");
		GridPane grid = new GridPane();
		grid.add(lblChoose , 0 , 4 );
		grid.add(cmboBarcodes, 1, 4);
		grid.add(btnOneProductStr, 2, 4);
		grid.setAlignment(Pos.BASELINE_CENTER);
		grid.setHgap(15);
		grid.setVgap(15);
		vb.getChildren().add(grid);
	}

	public String getBarCodeFromComboBox() {
		return cmboBarcodes.getValue();
	}

	public void addEventGetOneProductstr(EventHandler<ActionEvent> searchProductByBarCode) {
		btnOneProductStr.setOnAction(searchProductByBarCode);
	}
	public void showDeleteByBarcodeView(String[] barcodes) {
		cmboBarcodes = new ComboBox<>();
		Label lblChoose = new Label("choose barcode :");
		cmboBarcodes.getItems().addAll(barcodes);
		if(barcodes.length > 0)
			cmboBarcodes.setValue(barcodes[0]);
		btnOneProductDelete = new Button("Press to delete");
		GridPane grid = new GridPane();
		grid.add(lblChoose , 0 , 4 );
		grid.add(cmboBarcodes, 1, 4);
		grid.add(btnOneProductDelete, 2, 4);
		grid.setAlignment(Pos.BASELINE_CENTER);
		grid.setHgap(15);
		grid.setVgap(15);
		vb.getChildren().add(grid);
	}
	public void closeStoreView() {
		sc.getWindow().hide(); 
	}
	public void DeleteProductEvent(EventHandler<ActionEvent> deleteProductByBarCode) {
		btnOneProductDelete.setOnAction(deleteProductByBarCode);
		
	}
	public void showDeleteLastView() {
		GridPane grid = new GridPane();
		btnDeleteLast = new Button("confirm delete Last");
		grid.add(btnDeleteLast , 1,7);
		grid.setAlignment(Pos.BASELINE_CENTER);
		grid.setHgap(15);
		grid.setVgap(15);
		vb.getChildren().add(grid);
		
	}
	public void DeleteLastProductEvent(EventHandler<ActionEvent> deleteLastEvent) {
		btnDeleteLast.setOnAction(deleteLastEvent);
	}
	public void DeleteAllProductEvent(EventHandler<ActionEvent> deleteAllEvent) {
		btnDeleteAll.setOnAction(deleteAllEvent);
		
	}
	public void showDeleteAllView() {
		GridPane grid = new GridPane();
		btnDeleteAll = new Button("confirm delete all");
		grid.add(btnDeleteAll , 1,7);
		grid.setAlignment(Pos.BASELINE_CENTER);
		grid.setHgap(15);
		grid.setVgap(15);
		vb.getChildren().add(grid);
	}
	public void showOneProductProfit(String[] allBarcodes) {
		cmboBarcodes = new ComboBox<>();	
		Label lblChoose = new Label("choose barcode :");
		cmboBarcodes.getItems().addAll(allBarcodes);
		if(allBarcodes.length > 0)
			cmboBarcodes.setValue(allBarcodes[0]);
		btnOneProductProfit = new Button("Press to show profit");
		GridPane grid = new GridPane();
		grid.add(lblChoose , 0 , 4 );
		grid.add(cmboBarcodes, 1, 4);
		grid.add(btnOneProductProfit, 2, 4);
		grid.setAlignment(Pos.BASELINE_CENTER);
		grid.setHgap(15);
		grid.setVgap(15);
		vb.getChildren().add(grid);
		
	}
	public void addEventGetOneProductProfit(EventHandler<ActionEvent> showOneProductProfitByBarCode) {
		btnOneProductProfit.setOnAction(showOneProductProfitByBarCode);
	}
	public void showSendUpdateWindow() {
		
		Label lblMsg = new Label("write Update: ");
		btnSendUpdate = new Button("Press to send update");
		tfUpdateMsg = new TextArea();
		tfUpdateMsg.setPrefHeight(100); 
		tfUpdateMsg.setPrefWidth(250);   
		GridPane grid = new GridPane();
		grid.add(lblMsg , 1 , 1 );
		grid.add(tfUpdateMsg, 1, 2);
		grid.add(btnSendUpdate, 1, 4);
		grid.setAlignment(Pos.BASELINE_CENTER);
		grid.setHgap(15);
		grid.setVgap(15);
		vb.getChildren().add(grid);
	}
	public String getUpdateMsg() {
		
		return tfUpdateMsg.getText();
	}
	public void addUpdateEvent(EventHandler<ActionEvent> sendUpdateEvent) {
		btnSendUpdate.setOnAction(sendUpdateEvent);
		
	}
	public void showObserversWindow(String msg) throws Exception {
		
		obsView = new ObsView(msg);
		
	}
	public void setObsText(String name) {
		obsView.setText(name);
	}
	public void setVasibleFinishPrint() {
		obsView.setPrntFinished();
		
	}
	public void closeObsView() {
		obsView.closeView();
		
	}
}
