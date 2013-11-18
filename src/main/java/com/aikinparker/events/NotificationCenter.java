package com.aikinparker.events;

/**
 * A simple singleton wrapper for DefaultEventDispatcher. Made to imitate the behavior of the iOS NSNotificationCenter.
 */
public class NotificationCenter extends DefaultEventDispatcher {

	private static NotificationCenter instance;
	
	public static NotificationCenter defaultCenter(){
		if(null == instance) {
			instance =  new NotificationCenter();
		}
		return instance;
	}
	

}
