package prgm.events.senders;

import java.util.ArrayList;

import prgm.events.subscribers.OnStartUpEvent;

public class OnStartUpEventSendder {
	private static ArrayList<OnStartUpEvent> Subs = new ArrayList<>();
	
	public static void subscribe(OnStartUpEvent onStartUpEvent) {
		if (onStartUpEvent != null) {
			Subs.add(onStartUpEvent);
		} else {
			throw new IllegalArgumentException("Input is null");
		}
	}
	
	public static void send() {
		for (int i = 0; i < Subs.size(); i++) {
			Subs.get(i).OnSUEvent();
		}
	}
}
