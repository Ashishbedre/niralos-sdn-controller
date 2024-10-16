package com.other.app.NiralosFiveGCore.BackendServices.Subscriber;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.other.app.NiralosFiveGCore.model.Slice.SliceConfigrationModel;
import org.springframework.http.ResponseEntity;


import com.other.app.NiralosFiveGCore.Dto.Subscriber.FrontendDto.ListofApn;
import com.other.app.NiralosFiveGCore.Dto.Subscriber.FrontendDto.SliceConfigrationDto;
import com.other.app.NiralosFiveGCore.model.Subscriber.Subscribers;

public interface SubscriberServices {

	public ResponseEntity<String> addSubscriber(Subscribers subscribers);

	public void addSubscriberBulk(Subscribers subscribers, String quantity);

	public List<Subscribers> viewSubscriber(String imsi);

	public List<Subscribers> viewAllSubscribers(int pageNumber, int pageSize);

	public void deleteSubscriber(String _id);

	public void deleteSubscriberBulk(String imsi, String quantity);

	public void deleteAllSubscriber();

	public void updateSubscriber(String id, Subscribers subscribers);

	public List<Subscribers> devicetagFilter(String devicetagfilter);

	public List<Subscribers> searchByImsiPartialData(String partialValue);

	public int getTotalPages(int pageNumber, int pageSize);

	public void addApnInfo(Map<String, String> apn);

	public List<ListofApn> returnApnInfo();

	public String returnApnDelete(Integer apn_id);

	public Map<String, Object> returnApnbyid(Integer apn_id);

	public ResponseEntity<String> patchApnInfoById(Integer apn_id, Map<String, String> apnValue);

	public ResponseEntity<String> addSlice(SliceConfigrationDto slice);

	public ResponseEntity<String> DeleteSliceById(String sliceId);

	public Optional<SliceConfigrationModel> viewSliceById(String sliceId);
	
	public List<SliceConfigrationModel> getSliceList();
	
	public List<SliceConfigrationDto> getSliceListtemp(); 
	
	public void deleteAllSlice();
	
	public void updateSliceInfo(String id, SliceConfigrationModel sliceConfiguration);
	
	
	



	// public void searchSubscriber(String imsi);


}
