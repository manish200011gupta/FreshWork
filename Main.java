package FreshWorks;

import org.json.simple.JSONObject;

public class Main {
	public static void main(String[] args) {

		JSONObject json = new JSONObject();
		json.put("name", "demo");
		json.put("age", 20);
		json.put("salary", 1000000);

		JSONObject json1 = new JSONObject();
		json1.put("name", "demo1");
		json1.put("age", 21);

		JSONObject json2 = new JSONObject();
		json2.put("name", "demo2");
		json2.put("age", 22);

		CRDClass obj = new CRDClass("newdemo1.txt");
		try {
			obj.Create("emp1", json);
			System.out.println(obj.read("emp1"));
		} catch (Exception e) {
			System.out.println(e);
		}

		// trying to create new key value where key already exist
		try {
			obj.Create("emp1", json);
		} catch (Exception e) {
			System.out.println(e);
		}

		// trying to delete key value where key not exist
		try {
			obj.delete("emp2");
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			obj.Create("emp2", json);
			System.out.println(obj.read("emp2"));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
