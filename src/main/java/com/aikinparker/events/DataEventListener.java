package com.aikinparker.events;

public abstract class DataEventListener<T> implements Event.Listener{

	public void eventReceived(Event event) {
		//TODO Do some type checking validation.
		coerceEventReceived((DataEvent<?>) event);
	}
	
	/**
	 * @param event 
	 * This method is called on any subclass upon receiving an Event instance, delegating responsibility of
	 * of any further coercion necessary.
	 */
	protected abstract <S> void coerceEventReceived(DataEvent<S> event);

}
