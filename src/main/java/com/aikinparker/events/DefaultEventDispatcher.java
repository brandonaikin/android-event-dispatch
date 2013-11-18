package com.aikinparker.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import com.aikinparker.events.Event.Listener;

public class DefaultEventDispatcher implements EventDispatcher {

	protected Map<String, List<Event.Listener>> listenerMap = new WeakHashMap<String, List<Event.Listener>>();
	
	/* (non-Javadoc)
	 * @see com.aikinparker.events.EventDispatcher#addEventListener(com.aikinparker.events.Event.Listener, com.aikinparker.events.Event)
	 */
	@Override
	public void addEventListener(Event.Listener listener, Event event) {
		if(listenerMap.get(event.getType()) == null) {
			listenerMap.put(event.getType(), createListenerList());
		}
		List<Event.Listener> listenerList = listenerMap.get(event.getType());
		if(!listenerList.contains(listener)) {
			listenerList.add(listener);
		}
	}

	

	@Override
	public void removeEventListener(Listener listener, String type) {
		if(hasEventListener(listener, type)) {
			listenerMap.get(type).remove(listener);
		}
	}

	/* (non-Javadoc)
	 * @see com.aikinparker.events.EventDispatcher#dispatchEvent(com.aikinparker.events.Event)
	 */
	@Override
	public synchronized void dispatchEvent(Event event) {
		List<Event.Listener>listenerList = listenerMap.get(event.getType());
		if(null == listenerList || listenerList.size() < 1) {
			return;
		}
		if(null == event.getTarget()) {
			event.setTarget(this);
		}
		for(Event.Listener listener:listenerList) {
			listener.eventReceived(event);
		}
	}

	/**
	 * @return A new List<Event.Listener> instance. Subclasses may override to specify a 
	 * a particular List implementation or a more specific Event.Listener implementation.
	 */
	protected List<Event.Listener> createListenerList() {
		return new ArrayList<Event.Listener>();
	}
	
	/**
	 * @param type
	 * @return True if the EventDispatcher has one or more subscribers for this particular event.
	 */
	public boolean hasEventListener(String type) {
		return listenerMap.get(type) != null && listenerMap.get(type).size() > 0;
	}
	
	/**
	 * @param type
	 * @return True if the EventDispatcher has a reference to this listener, listening for this particular event.
	 */
	public boolean hasEventListener(Listener listener, String type) {
		return hasEventListener(type) && listenerMap.get(type).contains(listener);
	}
}
