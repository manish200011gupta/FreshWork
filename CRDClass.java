package FreshWorks;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.json.simple.JSONObject;

public class CRDClass {
	KeyValue kv;
	String filePath;

	CRDClass() {
		filePath = "f1.txt";
		File file = new File(filePath);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	CRDClass(String filePath) {
		this.filePath = filePath;
		File file = new File(filePath);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	synchronized boolean Create(String key, JSONObject json) throws Exception {
		if (key.length() > 32)
			throw new Exception("Key must be less than or equal 32 characters");
		kv = new KeyValue(key, json);
		ArrayList<KeyValue> arr = new ArrayList<KeyValue>();

		try {
			FileInputStream fin = new FileInputStream(filePath);
			try {
				ObjectInputStream in = new ObjectInputStream(fin);
				arr = (ArrayList<KeyValue>) in.readObject();

			} catch (EOFException e) {
				arr.add(kv);
				FileOutputStream fout = new FileOutputStream(filePath);
				ObjectOutputStream out = new ObjectOutputStream(fout);
				out.writeObject(arr);
				return true;
			}
			for (int i = 0; i < arr.size(); i++) {
				if (arr.get(i).getKey().equals(key)) {
					throw new Exception("Key already exists");
				}
			}
			arr.add(kv);
			FileOutputStream fout = new FileOutputStream(filePath);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(arr);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return true;
	}

	synchronized JSONObject read(String key) throws Exception {
		ArrayList<KeyValue> arr = new ArrayList<KeyValue>();
		int flag = 0;
		try {
			FileInputStream fin = new FileInputStream(filePath);
			try {
				ObjectInputStream in = new ObjectInputStream(fin);
				arr = (ArrayList<KeyValue>) in.readObject();
				for (int i = 0; i < arr.size(); i++) {
					if (arr.get(i).getKey().equals(key)) {
						flag = 1;
						return arr.get(i).getJson();
					}
				}
			} catch (IOException e) {
				throw new Exception("File is Empty");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		if (flag == 0) {
			throw new Exception("No value associated with key : " + key);
		}
		return new JSONObject();
	}

	synchronized boolean delete(String key) throws Exception {
		ArrayList<KeyValue> arr = new ArrayList<KeyValue>();
		int flag = 0;
		try {
			FileInputStream fin = new FileInputStream(filePath);

			try {
				ObjectInputStream in = new ObjectInputStream(fin);
				arr = (ArrayList<KeyValue>) in.readObject();

			} catch (EOFException e) {
				throw new Exception("File is Empty");
			}

			for (int i = 0; i < arr.size(); i++) {
				if (arr.get(i).getKey().equals(key)) {
					arr.remove(i);
					FileOutputStream fout = new FileOutputStream(filePath);
					ObjectOutputStream out = new ObjectOutputStream(fout);
					out.writeObject(arr);
					return true;
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		if (flag == 0) {
			throw new Exception("No value associated with key : " + key);
		}

		return false;
	}
}
