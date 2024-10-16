package com.other.app.NiralosFiveGCore.Controller.Frontend;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.other.app.NiralosFiveGCore.model.Slice.SliceConfigrationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.other.app.NiralosFiveGCore.BackendServices.Subscriber.SubscriberServices;
import com.other.app.NiralosFiveGCore.Dto.Subscriber.FrontendDto.ListofApn;
import com.other.app.NiralosFiveGCore.Dto.Subscriber.FrontendDto.SliceConfigrationDto;
import com.other.app.NiralosFiveGCore.model.Subscriber.Subscribers;

@CrossOrigin
@RestController
@RequestMapping("/v2/5gcore/subscriber")
public class SubscriberController {

	@Autowired
	SubscriberServices subscriberServices;

	// Add the Subscriber
	@PostMapping("/addSubscriber")
	public ResponseEntity<String> addSubscriber(@RequestBody Subscribers subscribers) {
		return subscriberServices.addSubscriber(subscribers);
	}

	@PostMapping("/updateSubscriber/id={id}")
	public String updateSubscriber(@PathVariable("id") String id, @RequestBody Subscribers subscribers) {
		subscriberServices.updateSubscriber(id,subscribers);
		return "User Updated Successfully!";
	}

	// Add Subscriber
	@PostMapping("/addSubscriberBulk/quantity={quantity}")
	public ResponseEntity<String> addSubscriberBulk(@RequestBody Subscribers subscribers,
			@PathVariable("quantity") String quantity) {
		String imsi = subscribers.getImsi();
		try {
			subscriberServices.addSubscriberBulk(subscribers, quantity);
			String responseMessage = "Users Added Successfully from IMSI " + imsi + " to "
					+ Long.toString((Long.parseLong(imsi) + Long.parseLong(quantity) - 1));
			return ResponseEntity.ok(responseMessage);
		} catch (IllegalArgumentException e) {
			// Return a response with a BAD REQUEST status

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// Delete Subscriber by id
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

	// Delete all the subscriber
	@GetMapping("/deleteAllSubscriber")
	public String deleteSubscriber() {
		subscriberServices.deleteAllSubscriber();
		return "All Users Deleted Successfully!";
	}

	// view unique subscriber using imsi
	@GetMapping("/viewSubscriber/imsi={imsi}")
	public List<Subscribers> viewSubscriber(@PathVariable("imsi") String imsi) {
		return subscriberServices.viewSubscriber(imsi);
	}

	// View all the subscriber4
	@GetMapping("/viewAllSubscribers/pageNumber={pageNumber}")
	public List<Subscribers> viewAllSubscribers(@PathVariable("pageNumber") int pageNumber) {
		int pageSize = 20; // Set the desired page size here
		return subscriberServices.viewAllSubscribers(pageNumber, pageSize);
	}

	@GetMapping("/totalPages")
	public int getTotalPages() {
		int pageSize = 20; // Set the desired page size here
		return subscriberServices.getTotalPages(pageSize, pageSize);
	}

	@GetMapping("/devicetagfilter={devicetagfilter}")
	public List<Subscribers> searchSubscriber(@PathVariable("devicetagfilter") String devicetagfilter) {
		return subscriberServices.devicetagFilter(devicetagfilter);
	}

	@GetMapping("/searchByImsi/partialValue={partialValue}")
	public List<Subscribers> searchSubscribersByImsiPartialData(@PathVariable("partialValue") String partialValue) {
		return subscriberServices.searchByImsiPartialData(partialValue);
	}

	@PostMapping("/add/apn")
	public ResponseEntity<String> addApn(@RequestBody Map<String, String> apn) {
		try {
			subscriberServices.addApnInfo(apn);
			// If the operation is successful, return a 201 CREATED status with a success
			// message
			return ResponseEntity.status(HttpStatus.CREATED).body("APN info added successfully.");
		} catch (Exception e) {
			// If there's an error, return a 500 INTERNAL SERVER ERROR status with an error
			// message
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add APN info.");
		}

	}

	@GetMapping("/get/apn_list")
	public ResponseEntity<List<ListofApn>> getAllApnInfo() {
		List<ListofApn> apnList = subscriberServices.returnApnInfo();
		if (apnList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(apnList, HttpStatus.OK);
		}
	}

	@GetMapping("/viewApn/id={id}")
	public Map<String, Object> returnapnbyid(@PathVariable("id") int apn_id) {
		return subscriberServices.returnApnbyid(apn_id);
	}

	@PatchMapping("/update_apn/id={id}")
	public ResponseEntity<String> apnPatchbyid(@PathVariable("id") int apn_id,
			@RequestBody Map<String, String> apnValue) {
		try {
			// Call the service method and get the response
			ResponseEntity<String> response = subscriberServices.patchApnInfoById(apn_id, apnValue);

			// Return the successful response from the service
			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
		} catch (Exception e) {
			// Handle errors and return an appropriate error response
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
		}
	}

	@DeleteMapping("/delete_apn/id={id}")
	public String returnApnDeletebyid(@PathVariable("id") int apn_id) {
		return subscriberServices.returnApnDelete(apn_id);
	}

	@PostMapping("/addSlice")
	public ResponseEntity<String> addNewSlice(@RequestBody SliceConfigrationDto Slice) {
		return subscriberServices.addSlice(Slice);
	}
	@GetMapping("/viewSlice/id={id}")
	public ResponseEntity<?> viewSlice(@PathVariable("id") String SliceId) {
		Optional<SliceConfigrationModel> lists = subscriberServices.viewSliceById(SliceId);
		if (lists.isPresent()) {
			return ResponseEntity.ok(lists.get());
		} else {

			return ResponseEntity.badRequest().body("Slice with ID " + SliceId + " not found.");
		}
	}
	@DeleteMapping("/deleteSlice/id={id}")
	public ResponseEntity<String> SliceDeletebyid(@PathVariable("id") String Slice_id) {
		return subscriberServices.DeleteSliceById(Slice_id);
	}
    
	@GetMapping("/slice_list/with_mongoId")
	public List<SliceConfigrationModel> getSliceList()
	{
		return subscriberServices.getSliceList();
	}

	@GetMapping("/slice_list")
    public List<SliceConfigrationDto> getSliceListtemp()
    {
        return subscriberServices.getSliceListtemp();
    }
	@DeleteMapping("/delete_Slice_list")
	public String deleteAllSlice() {
		subscriberServices.deleteAllSlice();
		return "Delete Sucessfully all ";
	}
	
	@PutMapping("/updateslice_info/id={_id}")
	public String updateSlice(@PathVariable("_id") String id, @RequestBody SliceConfigrationModel sliceConfiguration)
	{
		subscriberServices.updateSliceInfo(id ,sliceConfiguration);
		return "Update Slice Successfully";
		
	}


	// @GetMapping("/subscriber/search={search}")
	// public void searchSubscriber(@PathVariable("search") String imsi)
	// {
	// subscriberServices.searchSubscriber(imsi);
	// }

}
