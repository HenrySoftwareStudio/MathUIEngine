package prgm.events.senders;

import java.util.ArrayList;

import prgm.events.subscribers.OnTextSizeChange;

public class OnTextSizeChangeSender {
	private static ArrayList<OnTextSizeChange> Subs = new ArrayList<>();
	
	public static void subscribe(OnTextSizeChange onTextSizeChange) {
		if (onTextSizeChange != null) {
			Subs.add(onTextSizeChange);
		} else {
			
		}
	}
	
	public static void send(int value) {
		for (int i = 0; i < Subs.size(); i++) {
			Subs.get(i).onTSCEvent(value);
		}
	}
}
