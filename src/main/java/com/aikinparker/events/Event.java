package com.aikinparker.events;



public class Event {
	public static final String COMPLETE = "Event.COMPLETE";
	protected String type;
	protected EventDispatcher target;
	
	public static interface Listener {
		void eventReceived(Event event);
	}
	
	public Event(String type) {
		super();
		this.type = type;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Event [ target=" + target + ", type=" + type
				+ ", hashCode()=" + hashCode() + "]";
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	public EventDispatcher getTarget() {
		return target;
	}
	/**
	 * @param target the target to set
	 */
	public void setTarget(EventDispatcher target) {
		this.target = target;
	}
}
