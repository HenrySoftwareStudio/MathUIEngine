package tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChangableString {
	private ArrayList<Map<String, String>> ChangableTo;
	public ChangableString() {
		this.ChangableTo = new ArrayList<>(0); 
	}
	
	public void Addref(String Key, String Value) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put(Key, Value);
		ChangableTo.add(hm);
	}
	
	public String getref(String Key) {
		String toReturn = null;
		for (int i = 0; i < ChangableTo.size(); i++) {
			Map<String, String> obj = ChangableTo.get(i);
			if(obj.containsKey(Key)) {
				toReturn = obj.get(Key);
				break;
			}
		}
		return toReturn;
	}
	
	public ArrayList<Map<String, String>> getChangableTo() {
		return ChangableTo;
	}
	
	public void setChangableTo(ArrayList<Map<String, String>> changableTo) {
		ChangableTo = changableTo;
	}
}
