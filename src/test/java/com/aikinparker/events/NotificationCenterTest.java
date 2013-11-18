package com.aikinparker.events;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple NotificationCenter.
 */
public class NotificationCenterTest extends TestCase {

	public NotificationCenterTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(NotificationCenterTest.class);
	}

	
	public void testNotificationCenter() {
		// Create a reference to the single NotificationCenter instance.
		NotificationCenter notificationCenter = NotificationCenter.defaultCenter();
		
		//Create an arbitrary DataEvent object, using a mock list for it's type parameter.
		DataEvent<List<Image>> event = new DataEvent<List<Image>>(Event.COMPLETE, mockList());
		
		//Create a listener.
		DataEventListener<List<Image>> listener = event.createListener(DataListener.class);
		
		//Add the listener to the local reference.
		notificationCenter.addEventListener(listener, event);
		
		//Dispatch the event.
		notificationCenter.dispatchEvent(event);
		
		//Remove the listener from the static instance.
		NotificationCenter.defaultCenter().removeEventListener(listener, Event.COMPLETE);
		
		// Prove that our member reference is the same as the static reference.
		boolean removed = notificationCenter.hasEventListener(Event.COMPLETE) == false;
		assertTrue(removed);
	}
	
	private List<Image> mockList() {
		byte[] bytes = new byte[]{
				(byte) 0x774488, (byte) 0x1234567
		};
		List<Image> imageList = new ArrayList<Image>();
		Image image1 = new Image("someUrl", bytes);
		Image image2 = new Image("someOtherUrl", bytes);
		imageList.add(image1);
		imageList.add(image2);
		return imageList;
	}
	
	

	/**
	 * Example implementation of DataEventListener.
	 *
	 */
	public static class DataListener extends DataEventListener<List<Image>> {
		List<Image> receivedData = new ArrayList<Image>();	
		@Override
		protected <S> void coerceEventReceived(DataEvent<S> event) {
			List<?> list = (List<?>)event.getData();
			for(Object elem:list) {
				if(Image.class.equals(elem.getClass())) {
					receivedData.add((Image) elem);
				}
			}
			assertTrue(receivedData.size() == list.size());
		}
	}
	
	
	
	public static class Image {
		String url;
		byte[] bytes;
		public Image(String url, byte[] bytes) {
			super();
			this.url = url;
			this.bytes = bytes;
		}
		public String getUrl() {
			return url;
		}
		public byte[] getBytes() {
			return bytes;
		}
	}
}
