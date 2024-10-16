package com.other.app.NiralosFiveGCore.BackendServices.Nef;

import org.bson.Document;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface NefSubscriptionService {


    public ResponseEntity<String> addNefSubscription();
    public void createEventNotifyUri(Map<String, Object> payload);
    public List<Document> getLastFiveSubscriptions();
    public ResponseEntity<String> NefUnSubscription();
    public ResponseEntity<Integer> NefSubscriptionstatus();
}
