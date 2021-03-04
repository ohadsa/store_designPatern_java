package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.AbstractMap.SimpleEntry;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class FileUtilities implements Iterable<Entry<String, Product>> {

	File file;

	public FileUtilities(File file) {
		this.file = file;
	}

	private class MyIterator implements Iterator<Entry<String, Product>> {
		long cur = 0;
		long last = -1;

		@Override
		public boolean hasNext() {
			try {
				RandomAccessFile raf = new RandomAccessFile(file, "rw");
				boolean res = ( raf.length() - cur ) > 0 ;
				raf.close();
				return res;

			} catch (IOException e) {
				return false;
			}
		}

		@Override
		public Entry<String, Product> next() {
			try {
				RandomAccessFile raf = new RandomAccessFile(file, "rw");
				SimpleEntry<String, Product> res;
				if (cur == 0)
				{
					res = new SimpleEntry<>(raf.readUTF(), null);
					last = cur;
					cur = raf.getFilePointer();
					
				}
				else
				{
					raf.seek(cur);
					
					res = new SimpleEntry<>(raf.readUTF(), new Product(raf.readUTF(), raf.readInt(),raf.readInt(),
									raf.readUTF(), raf.readUTF(),  raf.readBoolean()));
				
				last = cur ;
				cur = raf.getFilePointer();
				}
				raf.close();

				return res;
			}
			catch (Exception e) {
				
				return null;
			}
		}

		@Override
		public void remove() {
			if(last == -1 )
				throw new IllegalStateException();
			try {
				RandomAccessFile raf = new RandomAccessFile(file, "rw");
				byte[] buffer = new byte[(int)(raf.length() - cur)];
				raf.seek(cur);
				raf.read(buffer);
				raf.setLength(last);
				raf.seek(last);
				raf.write(buffer);
				raf.close();
				cur = last ;
				last = -1 ;
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public Iterator<Entry<String, Product>> iterator() {
		return new MyIterator();
	}

	public void saveTofile(String sortType, Map<String, Product> allProducts) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		raf.setLength(0);
		raf.writeUTF(sortType);
		for (Map.Entry<String, Product> p : allProducts.entrySet()) {
			saveProductToFile(p.getKey(), p.getValue(), raf);
		}
		raf.close();

	}

	private void saveProductToFile(String barCode, Product p, RandomAccessFile raf) throws IOException {

		raf.writeUTF(barCode);
		p.saveProductToFile(raf);

	}

	public void saveSortType(String name) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(file, "rw");
		raf.setLength(0);
		raf.writeUTF(name);
		raf.close();
		
	}

	public void deleteSortType() {
		file.delete();
	}

}
