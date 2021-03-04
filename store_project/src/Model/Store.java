package Model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import java.util.TreeMap;

public class Store {

	public enum SortType {
		ascOrder, descOrder, insertOrder, notSorted
	}

	private Map<String, Product> allProducts;
	private SortType sortType;
	private FileUtilities fileUtilities;
	private Updater updater;

	public Store(File file) throws IOException {
		updater = Updater.getUpdater();
		fileUtilities = new FileUtilities(file);
		allProducts = new TreeMap<>();
		sortType = SortType.notSorted;
		if (file.exists())
			readFromFile();
	}

	private void readFromFile() throws IOException {
		Iterator<Entry<String, Product>> it = fileUtilities.iterator();
		if (!it.hasNext())
			return;

		setSortType(SortType.valueOf(it.next().getKey()));
		while (it.hasNext()) {
			Entry<String, Product> tmp = it.next();
			allProducts.put(tmp.getKey(), tmp.getValue());
		}
		updateObserverList();

	}

	private void updateObserverList() {
		updater.deleteAllObservers();
		for (Map.Entry<String, Product> p : allProducts.entrySet()) {
			updater.addObserver(p.getValue().getCostumer());
		}

	}

	public void setSortType(int type) throws IOException {
		SortType orderBy = SortType.values()[type];
		this.setSortType(orderBy);
	}

	public void setSortType(SortType type) throws IOException {

		if (sortType != SortType.notSorted)
			return;

		allProducts = chooseCorrectmap(type);
		// fileUtilities.saveSortType(type.name());

	}

	private Map<String, Product> chooseCorrectmap(SortType type) {
		Map<String, Product> tmp = null;
		switch (type) {
		case ascOrder:
			tmp = new TreeMap<String, Product>();
			sortType = SortType.ascOrder;
			break;
		case descOrder:
			tmp = new TreeMap<String, Product>(new Comparator<String>() {
				@Override
				public int compare(String str1, String str2) {

					return (str2.toLowerCase().compareTo(str1.toLowerCase()));
				}
			});
			sortType = SortType.descOrder;
			break;
		case insertOrder:
			tmp = new LinkedHashMap<>();
			sortType = SortType.insertOrder;
			break;
		default:
			tmp = new HashMap<>();
			sortType = SortType.notSorted;
			break;
		}
		return tmp;
	}

	public int addOrUpdateProduct(String barCode, Product p) throws Exception {
		boolean isExsist = false;
		if(!IsOkBarCode(barCode))
			throw new Exception("BARCODE can contain only letters and digits");
		if (allProducts.containsKey(barCode))
			isExsist = true;
		if (isExsist) {
			allProducts.replace(barCode, p);
			updateObserverList();
			return 0; // if return 0 the exsisting product is updated
		}
		allProducts.put(barCode, p);
		fileUtilities.saveTofile(sortType.name(), allProducts);
		updateObserverList();
		return 1; // if return 1 new product added

	}

	private boolean IsOkBarCode(String barCode) {
		for(int i = 0 ; i < barCode.length() ; i++ )
			if((!Character.isAlphabetic(barCode.charAt(i))) && (!Character.isDigit(barCode.charAt(i))) )
				return false;
					
		return true;
	}

	public String toStringAllProduct() throws Exception {
		if (allProducts.isEmpty())
			throw new Exception("No product to Show");
		StringBuffer buffer = new StringBuffer();
		int index = 1;
		String orders[] = { "ascending order", "Descending order", "Insertion order" };
		buffer.append("all products by " + orders[sortType.ordinal()] + " :\n");
		for (Map.Entry<String, Product> p : allProducts.entrySet()) {
			buffer.append("(" + index++ + ") barcode: " + p.getKey() + "   " + p.getValue().toString() + "\n");
		}
		return buffer.toString();
	}

	public String toStringProductByBarCode(String barCode) throws Exception {
		if(barCode == null)
			throw new Exception("no Product to show!");
		return "(*) barcode: " + barCode + "  " + allProducts.get(barCode).toString();
	}

	public int getSortType() {
		return sortType.ordinal();
	}

	public class StoreMemento {

		SortType type;
		Map<String, Product> arr;

		private StoreMemento(SortType type) {
			this.type = type;
			arr = chooseCorrectmap(type);
			updateMemento();
		}

		private void updateMemento() {
			if (allProducts.isEmpty())
				return;
			for (Map.Entry<String, Product> p : allProducts.entrySet()) {
				arr.put(new String(p.getKey()), new Product(p.getValue()));
			}
		}

		private Map<String, Product> getProductsArr() {
			return arr;
		}

		private SortType getSortType() {
			return type;
		}

	}

	public StoreMemento createMemento() {
		return new StoreMemento(sortType);
	}

	public boolean deleteByBarCode(String barCode) throws Exception {
		if (getNumOfProducts() == 0)
			throw new Exception("No product to delete");
		Iterator<Entry<String, Product>> it = fileUtilities.iterator();
		while (it.hasNext()) {
			Entry<String, Product> tmp = it.next();
			if (tmp.getKey().equals(barCode)) {
				it.remove();
				allProducts.clear();
				readFromFile();
				return true;
			}
		}
		return false;
	}

	public void deleteAll() throws Exception {
		if (getNumOfProducts() == 0)
			throw new Exception("No product to delete");
		Iterator<Entry<String, Product>> it = fileUtilities.iterator();
		if (it.hasNext())
			it.next(); // to skip the sortType
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
		updater.deleteAllObservers();
		allProducts.clear();
	}

	public int getNumOfProducts() {
		return allProducts.size();
	}

	public void restoreFromMemento(StoreMemento memento) throws Exception {
		if (memento == null)
			throw new Exception("No previous version saved");
		allProducts.clear();
		this.sortType = memento.getSortType();
		allProducts.putAll(memento.getProductsArr());
		fileUtilities.saveTofile(sortType.name(), allProducts);
		updateObserverList();
		memento = null;
	}

	public int getProductProfit(String barCode) {
		return allProducts.get(barCode).getProfit();
	}

	public int getAllProductsProfit() {
		int res = 0;
		for (Map.Entry<String, Product> p : allProducts.entrySet()) {
			res += p.getValue().getProfit();
		}
		return res;
	}

	public void sendUpdate(String string) throws Exception {
		updater.notifyObservers(string);
	}

	public String getUpdateMassage() throws Exception {
		return updater.getCurUpdate();
	}

	public ArrayList<String> getAllRespones() throws Exception {
		return updater.getAllRespones();
	}

	public boolean isloadedDataFromFile() {
		return sortType != SortType.notSorted;
	}

	public String[] getAllbarCodes() {
		String allBarcodes[] = new String[allProducts.size()];
		int i = 0;
		for (Map.Entry<String, Product> p : allProducts.entrySet()) {
			allBarcodes[i++] = new String(p.getKey());
		}
		return allBarcodes;
	}

	public void deleteSortType() {
		fileUtilities.deleteSortType();
		this.sortType = SortType.notSorted;

	}

	public String getOneProdPrifitByBarCode(String barCode) throws Exception {

		
		if( barCode==null || !allProducts.containsKey(barCode))	
			throw new Exception("No Product to calculate");
		return barCode + " " + getNameByBarCode(barCode) + " profit " + getProductProfit(barCode);
	}

	public String getAllProductProfitStr() throws Exception {
		if (allProducts.isEmpty())
			return "";
		StringBuffer buffer = new StringBuffer();
		int index = 1;

		buffer.append("all products profit :\n");
		for (Map.Entry<String, Product> p : allProducts.entrySet()) {
			buffer.append("(" + index++ + ") " + getOneProdPrifitByBarCode(p.getKey()) +"\n");
		}
		return buffer.toString();
	}

	private String getNameByBarCode(String barCode) {
		for (Map.Entry<String, Product> p : allProducts.entrySet()) {
			if (p.getKey().equals(barCode))
				return p.getValue().getProductName();
		}
		return "";
	}
}
