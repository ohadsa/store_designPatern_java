package Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Model.Store.StoreMemento;

public class ModelManager {

	private Store store;
	private StoreCareTaker careTaker;
	private boolean hasPreviusVersion;

	public ModelManager(File file) throws IOException {
		store = new Store(file);
		careTaker = new StoreCareTaker();
		hasPreviusVersion = false;
	}

	public boolean isloadedDataFromFile() {
		return store.isloadedDataFromFile();
	}

	public void setSortType(int sortType) {
		try {
			store.setSortType(sortType);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getSortType() {

		return "" + store.getSortType();

	}

	public int addProduct(String barCode, String pName, String cName, String cPhone, int cost, int cPrice,
			boolean wantUpdates) throws Exception {

		return store.addOrUpdateProduct(barCode,
				new Product(pName, cost, cPrice, cName, cPhone, wantUpdates));

	}

	public String getAllStrProducts() throws Exception {
		return store.toStringAllProduct();
	}

	public String getProductStrByBarCode(String productBarCode) throws Exception {
		return store.toStringProductByBarCode(productBarCode);

	}

	public String[] getAllBarcodes() {
		return store.getAllbarCodes();

	}

	public void deleteProduct(String productBarCode) throws Exception {
		store.deleteByBarCode(productBarCode);
	}

	public int getNumberOfProducts() {

		return store.getNumOfProducts();
	}

	public void deleteSortType() {
		store.deleteSortType();
	}

	public void SaveProductsStatus() {
		careTaker.update(store.createMemento());
		hasPreviusVersion = true;
	}

	public boolean hasPreviusVersion() {
		return hasPreviusVersion;
	}

	public StoreMemento getMemento() {

		return careTaker.getMemento();
	}

	public void restorePrevStatus(StoreMemento lastVersionMemento) throws Exception {
		store.restoreFromMemento(lastVersionMemento);
		careTaker = new StoreCareTaker();

	}

	public void deleteAllProuct() throws Exception {
		store.deleteAll();
	}

	public String getAllProfitStr() throws Exception {

		if (store.getNumOfProducts() == 0)
			throw new Exception("No product to calculate");
		return store.getAllProductProfitStr() + "\nall profit: " + store.getAllProductsProfit()
				+ "\neach product calculated once.";
	}

	public String getOneProductProfit(String barCode) throws Exception {
		return store.getOneProdPrifitByBarCode(barCode);

	}

	public void sendUpdate(String updateMsg) throws Exception {
		store.sendUpdate(updateMsg);

	}

	public String getAllObserv() throws Exception {
		return store.getAllRespones().toString();
	}

	public ArrayList<String> getObserverList() throws Exception {
		
		return store.getAllRespones();
	}

	public String getUpdateMsg() throws Exception {
		
		return store.getUpdateMassage();
	}

}
