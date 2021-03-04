package Controller;

import java.util.ArrayList;

import Commands.AddProductCommand;
import Commands.GetAllCastumerCRespondCommand;
import Commands.GetAllProductsProfitStrCommand;
import Commands.GetAllProductsStrCommand;
import Commands.GetOneProductProfitCommand;
import Commands.GetOneProductStrCommand;
import Commands.GetSortTypeCommand;
import Commands.SaveStatusCommand;
import Commands.deleteAllProductCommand;
import Commands.deleteLastProductCommand;
import Commands.deleteOneProductCommand;
import Commands.deleteSortTypeCommand;
import Commands.getAllBarCodesCommand;
import Commands.isReadenFromFileCommand;
import Commands.sendUpdatesCommand;
import Model.ModelManager;
import View.View;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class Controller {
	private ModelManager model;
	private View view;

	public Controller(ModelManager theModel, View theView) {
		this.model = theModel;
		this.view = theView;
		try {

			isReadenFromFileCommand cmd = new isReadenFromFileCommand(model);
			cmd.execute();

			if (!cmd.getIfreadFromFile()) {
				handleChooseSortEvent();

			} else {
				
				view.loadStoreView();
				view.showSuccsessAlert("data was loaded from file");
				handleViewEvents();
			}
		} catch (Exception e) {
			view.showErrorAlert(e.getMessage());
		}
	}

	private void handleViewEvents() {
		EventHandler<ActionEvent> mouseClickedEvent = new EventHandler<ActionEvent>() {

			private Thread thread;

			@Override
			public void handle(ActionEvent event) {
				view.clear();
				String opt = ((MenuItem) event.getSource()).getText();
				switch (opt) {
				case "Add Product":
					addProductControler();
					break;

				case "Show all products":
					showAllControler();
					break;

				case "Show By products barCode":
					showOneProductControler();
					break;

				case "Delete By Barcode":
					deleteOneProductControler();
					break;

				case "Delete last ( undo )":
					undoControler();

					break;

				case "Delete all products":
					deleteAllControler();

					break;
				case "Show profit By barCode":
					ShowOneProfitControler();

					break;

				case "Show profit from all products":
					showAllProfitControler();
					break;

				case "Send update to all costumers":
					sendUpdateControler();
					break;

				case "get all respons from clients":
					getAllResponsControler();
					break;
				}
			}

			
			private void getAllResponsControler() {
				
				try {
					GetAllCastumerCRespondCommand cmd = new GetAllCastumerCRespondCommand(model);
					cmd.execute();
					ArrayList<String> names = cmd.getallOservers();
					StringBuffer sb = new StringBuffer(""); 
					view.showObserversWindow(cmd.getUpdateMsg());
					thread = new Thread(()->{
						try {
							for (String s : names) {
								Thread.sleep(2000);
								Platform.runLater(() -> {
									sb.append(s.toString()+"\n");
									view.setObsText(sb.toString());
								});
							}
							view.setVasibleFinishPrint();
						} catch (InterruptedException e) {
							view.closeObsView();
						}
					});
					thread.start();
				
				} catch (Exception e) {
					view.showErrorAlert(e.getMessage());
				}
				
			}

			private void sendUpdateControler() {

				view.showSendUpdateWindow();
				EventHandler<ActionEvent> sendUpdateEvent = new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						try {
							String msg = view.getUpdateMsg();
							sendUpdatesCommand comd = new sendUpdatesCommand(model, msg);
							comd.execute();
							view.clear();
							view.showSuccsessAlert("updete sent !");
						} catch (Exception e) {
							view.clear();
							view.showErrorAlert(e.getMessage());
						}
					}
				};
				view.addUpdateEvent(sendUpdateEvent);

			}

			private void showAllProfitControler() {
				try {
					GetAllProductsProfitStrCommand comd = new GetAllProductsProfitStrCommand(model);
					comd.execute();
					view.ShowString(comd.getAllProduct());
				} catch (Exception e) {
					view.clear();
					view.showErrorAlert(e.getMessage());
				}
			}

			private void ShowOneProfitControler() {
				try {
					getAllBarCodesCommand cmd = new getAllBarCodesCommand(model);
					cmd.execute();
					view.showOneProductProfit(cmd.getAllbarCodes());
					EventHandler<ActionEvent> showOneProductProfitByBarCode = new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							try {
								view.clear();
								String barCode = view.getBarCodeFromComboBox();
								GetOneProductProfitCommand cmd = new GetOneProductProfitCommand(model, barCode);
								cmd.execute();
								view.ShowString(cmd.getProductProfit());

							} catch (Exception e) {
								view.clear();
								view.showErrorAlert(e.getMessage());

							}
						}
					};
					view.addEventGetOneProductProfit(showOneProductProfitByBarCode);
				} catch (Exception e) {
					view.clear();
					view.showErrorAlert(e.getMessage());
				}

			}

			private void deleteAllControler() {
				view.showDeleteAllView();
				EventHandler<ActionEvent> deleteAllEvent = new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						try {
							deleteAllProductCommand comd = new deleteAllProductCommand(model);
							comd.DeleteAllProduct();
							view.closeStoreView();
							handleChooseSortEvent();
							deleteSortTypeCommand sCmd = new deleteSortTypeCommand(model);
							sCmd.execute();
							view.showSuccsessAlert("all products deleted");

						} catch (Exception e) {
							view.clear();
							view.showErrorAlert(e.getMessage());
						}
					}
				};
				view.DeleteAllProductEvent(deleteAllEvent);

			}

			private void undoControler() {
				view.showDeleteLastView();
				EventHandler<ActionEvent> deleteLastEvent = new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						try {
							deleteLastProductCommand cmmd = new deleteLastProductCommand(model);
							cmmd.execute();
							cmmd.restorePrevStatus();
							if (cmmd.getNumberOfProducts() == 0) {
								view.closeStoreView();
								handleChooseSortEvent();
								view.showSuccsessAlert("all products deleted");
								deleteSortTypeCommand sCmd = new deleteSortTypeCommand(model);
								sCmd.execute();

							} else {
								view.showSuccsessAlert("Product delted !");
								view.clear();

							}
						} catch (Exception e) {
							view.clear();
							view.showErrorAlert(e.getMessage());
						}

					}
				};
				view.DeleteLastProductEvent(deleteLastEvent);
			}

			private void deleteOneProductControler() {
				try {
					getAllBarCodesCommand cmd = new getAllBarCodesCommand(model);
					cmd.execute();
					view.showDeleteByBarcodeView(cmd.getAllbarCodes());
					EventHandler<ActionEvent> deleteProductByBarCode = new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							try {
								String barCode = view.getBarCodeFromComboBox();
								deleteOneProductCommand cmd = new deleteOneProductCommand(model, barCode);
								cmd.execute();
								view.clear();
								if (cmd.getNumberOfProducts() == 0) {
									view.closeStoreView();
									handleChooseSortEvent();
									view.showSuccsessAlert("all products deleted");
									deleteSortTypeCommand sCmd = new deleteSortTypeCommand(model);
									sCmd.execute();

								} else {
									view.showSuccsessAlert("Product delted !");
								}
							} catch (Exception e) {
								view.clear();
								view.showErrorAlert(e.getMessage());
							}
						}

					};
					view.DeleteProductEvent(deleteProductByBarCode);
				} catch (Exception e) {
					view.clear();
					view.showErrorAlert(e.getMessage());
				}
			}

			private void showOneProductControler() {
				try {
					getAllBarCodesCommand cmd = new getAllBarCodesCommand(model);
					cmd.execute();
					view.showSearchForProductWindow(cmd.getAllbarCodes());
					EventHandler<ActionEvent> searchProductByBarCode = new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent arg0) {
							try {
								view.clear();
								String barCode = view.getBarCodeFromComboBox();
								GetOneProductStrCommand cmd = new GetOneProductStrCommand(model, barCode);
								cmd.execute();
								view.ShowString(cmd.getProduct());

							} catch (Exception e) {
								view.clear();
								view.showErrorAlert(e.getMessage());
							}

						}

					};
					view.addEventGetOneProductstr(searchProductByBarCode);
				} catch (Exception e) {
					view.clear();
					view.showErrorAlert(e.getMessage());
				}

			}

			private void showAllControler() {
				try {
					GetAllProductsStrCommand cmd = new GetAllProductsStrCommand(model);
					cmd.execute();
					view.ShowString(cmd.getAllProduct());
				} catch (Exception e) {
					view.clear();
					view.showErrorAlert(e.getMessage());
				}
			}

			private void addProductControler() {
				view.showAddingMenu();
				EventHandler<ActionEvent> addingDetailsBybtn = new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						String barCode, pName, cName, cPhone;
						int cost, cPrice;
						boolean wantUpdates;
						try {
							
							barCode = view.getBarcode();
							pName = view.getProductName();
							cName = view.getCostumerName();
							cPhone = view.getPhone();
							cost = view.getCost();
							cPrice = view.getCostumerPrice();
							wantUpdates = view.getWantUpdates();

							AddProductCommand productCmd = new AddProductCommand(model);
							productCmd.getAllProductFields(barCode, pName, cName, cPhone, cost, cPrice, wantUpdates);
							productCmd.execute();
							productCmd.getIfAddedOrUpdated();
							view.showSuccsessAlert(
									(productCmd.getIfAddedOrUpdated() == 1) ? "new Value added" : "Existing value Updated");
							SaveStatusCommand statusCmd = new SaveStatusCommand(model);
							statusCmd.execute();

						} catch (Exception e) {
							view.showErrorAlert(e.getMessage() + "\ntry again");
							view.clear();

						}
						view.clear();
					}

				};
				view.addEventaddingDetailsBybtn(addingDetailsBybtn);
			}
		};
		view.addMouseClickedEventToMenu(mouseClickedEvent);
	}

	private void handleChooseSortEvent() {
		view.loadChoosingSortView();
		EventHandler<ActionEvent> chooseSort = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				GetSortTypeCommand stCmd = new GetSortTypeCommand(model);
				stCmd.setSortType(view.getSortType());
				stCmd.execute();
				view.closeChoosingView();
				view.loadStoreView();
				handleViewEvents();
			}
		};
		view.addEventToSortChoseView(chooseSort);
	}
}
