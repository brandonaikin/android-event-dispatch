package com.aikinparker.events;

public interface EventDispatcher {
	/**
	 * Register an instance of Event.Listener as a subscriber of the specified Event.
	 * @param listener The Event.Listener instance subscribing for the specified Event.
	 * @param event The Event to which the listener should subscribe.
	 */
	void addEventListener(Event.Listener listener, Event event);
	
	/**
	 * Unregister the specified listener for this particular event.
	 * @param listener An implementation of Event.Listener.
	 * @param eventType A string with which to identify a specific type of event.
	 */
	void removeEventListener(Event.Listener listener, String eventType);
	
	/**
	 * Dispatch an event to each Listener instance listening for the specified event.
	 * @param event The event to dispatch.
	 * 
	 */
	void dispatchEvent(Event event);
}
