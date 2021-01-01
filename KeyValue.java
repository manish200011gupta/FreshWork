package FreshWorks;

import java.io.Serializable;

import org.json.simple.JSONObject;

//import org.json.simple.JSONObject;

public class KeyValue implements Serializable {

	private static final long serialVersionUID = 1L;
	String id;
	JSONObject json = new JSONObject();

	KeyValue() {
	}

	KeyValue(String id, JSONObject json) {
		this.id = id;
		this.json = json;
	}

	void addToJson(Object key, Object value) {
		json.put(key, value);
	}

	void setKey(String id) {
		this.id = id;
	}

	String getKey() {
		return id;
	}

	JSONObject getJson() {
		return json;
	}

	@Override
	public String toString() {
		return "KeyValue [id=" + id + ", json=" + json + "]";
	}

}
