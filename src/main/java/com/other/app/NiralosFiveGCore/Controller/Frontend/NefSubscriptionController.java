//package com.other.app.NiralosFiveGCore.Controller.Frontend;
//
//import com.other.app.NiralosFiveGCore.BackendServices.Nef.NefSubscriptionService;
//import org.bson.Document;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/v2/nef")
//@CrossOrigin
//public class NefSubscriptionController {
//
//    @Autowired
//    NefSubscriptionService Nefsubscriptionservice;
//
//    @GetMapping("/addNefSubscription")
//    public ResponseEntity<String> addSubscription() {
//        return Nefsubscriptionservice.addNefSubscription();
//    }
//
//
//    @PostMapping("/datacollection/amf-contexts/registration-accept")
//    public void addEventNotifyUri(@RequestBody Map<String, Object> payload) {
//        Nefsubscriptionservice.createEventNotifyUri(payload);
//    }
//    @GetMapping("/NefUnsubscription")
//    public ResponseEntity<String> UnSubscription() {
//        return Nefsubscriptionservice.NefUnSubscription();
//    }
//    @GetMapping("/listofNefSubscriptionData")
//    public List<Document> getSubscriptionsData(){
//        return Nefsubscriptionservice.getLastFiveSubscriptions();
//    }
//    
//    
//    @GetMapping("/Nefsubscription/status")
//    public ResponseEntity<Integer> getSubscriptionstatus() {
//        return Nefsubscriptionservice.NefSubscriptionstatus();
//    }
//
//
//}
