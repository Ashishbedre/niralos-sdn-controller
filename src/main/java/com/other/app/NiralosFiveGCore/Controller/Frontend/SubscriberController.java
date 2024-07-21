package com.other.app.NiralosFiveGCore.Controller.Frontend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.other.app.NiralosFiveGCore.BackendServices.Subscriber.SubscriberServices;
import com.other.app.NiralosFiveGCore.model.Subscriber.Subscribers;

@CrossOrigin
@RestController
@RequestMapping("/5gcore")
public class SubscriberController {
	
	@Autowired
	SubscriberServices subscriberServices;

	//Add the Subscriber
		@PostMapping("/addSubscriber")
		public String addSubscriber(@RequestBody Subscribers subscribers) {
			
			return subscriberServices.addSubscriber(subscribers);
		}

		@PostMapping("/updateSubscriber/id={id}")
		public String updateSubscriber(@PathVariable("id") String id, @RequestBody Subscribers subscribers) {
			subscriberServices.updateSubscriber(id,subscribers);
			return "User Updated Successfully!";
		}
		//Add Subscriber 
		@PostMapping("/addSubscriberBulk/quantity={quantity}")
		public String addSubscriberBulk(@RequestBody Subscribers subscribers, @PathVariable("quantity") String quantity) {
			String imsi = subscribers.getImsi();
			subscriberServices.addSubscriberBulk(subscribers, quantity);
			return "Users Added Successfully from IMSI "+imsi+" to "+ Long.toString((Long.parseLong(imsi) + Long.parseLong(quantity) - 1));
		}

		//Delete Subscriber by id
		@GetMapping("/deleteSubscriber/_id={_id}")
		public String deleteSubscriber(@PathVariable("_id") String _id) {
			subscriberServices.deleteSubscriber(_id);
			return "User Deleted Successfully!";
		}
		
		@GetMapping("/deleteSubscriberBulk/imsi={imsi}/quantity={quantity}")
		public String deleteSubscriberBulk(@PathVariable("imsi") String imsi, @PathVariable("quantity") String quantity) {
			subscriberServices.deleteSubscriberBulk(imsi, quantity);
			return "";
		}
		//Delete all the subscriber
		@GetMapping("/deleteAllSubscriber")
		public String deleteSubscriber() {
			subscriberServices.deleteAllSubscriber();
			return "All Users Deleted Successfully!";
		}

		//view unique subscriber using imsi 
		@GetMapping("/viewSubscriber/imsi={imsi}")
		public List<Subscribers> viewSubscriber(@PathVariable("imsi") String imsi) {
			return subscriberServices.viewSubscriber(imsi);
		}

		//View all the subscriber4
			@GetMapping("/viewAllSubscribers/pageNumber={pageNumber}")
		    public List<Subscribers> viewAllSubscribers(@PathVariable("pageNumber") int pageNumber) {
		        int pageSize = 20; // Set the desired page size here
		        return subscriberServices.viewAllSubscribers(pageNumber, pageSize);
		    }
			 @GetMapping("/subscribers/totalPages")
			    public int getTotalPages() {
			        int pageSize = 20; // Set the desired page size here
			        return subscriberServices.getTotalPages(pageSize, pageSize);
			    }
			@GetMapping("/subscriber/devicetagfilter={devicetagfilter}")
			public List<Subscribers> searchSubscriber(@PathVariable("devicetagfilter") String devicetagfilter)
			{
				return subscriberServices.devicetagFilter(devicetagfilter);
			}
			@GetMapping("/searchByImsi/partialValue={partialValue}")
		    public List<Subscribers> searchSubscribersByImsiPartialData(@PathVariable("partialValue") String partialValue) {
		        return subscriberServices.searchByImsiPartialData(partialValue);
		    }
	
	
//	@GetMapping("/subscriber/search={search}")
//	public void searchSubscriber(@PathVariable("search") String imsi)
//	{
//		subscriberServices.searchSubscriber(imsi);
//	}

}
