package com.other.app.NiralosFiveGCore.BackendServices.Subscriber;

import java.util.List;

import com.other.app.NiralosFiveGCore.model.Subscriber.Subscribers;

public interface SubscriberServices {

	public String addSubscriber(Subscribers subscribers);

	public void addSubscriberBulk(Subscribers subscribers, String quantity);

	public List<Subscribers> viewSubscriber(String imsi);

	public List<Subscribers> viewAllSubscribers(int pageNumber, int pageSize);

	public void deleteSubscriber(String _id);
	
	public void deleteSubscriberBulk(String imsi, String quantity);

	public void deleteAllSubscriber();

	public void updateSubscriber(String id,Subscribers subscribers);

	public List<Subscribers> devicetagFilter(String devicetagfilter);
	public List<Subscribers> searchByImsiPartialData(String partialValue);
	public int getTotalPages(int pageNumber, int pageSize);
	
//	public void searchSubscriber(String imsi);

}
