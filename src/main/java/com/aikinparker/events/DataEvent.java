package com.aikinparker.events;

public class DataEvent<T> extends Event {

	
	protected T data;
	
	public DataEvent(String type, T data) {
		super(type);
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	
	public DataEventListener<T> createListener(Class<? extends DataEventListener<T>> listenerClass) {
		try {
			return listenerClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
