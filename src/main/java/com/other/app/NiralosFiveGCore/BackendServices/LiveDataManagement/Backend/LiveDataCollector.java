package com.other.app.NiralosFiveGCore.BackendServices.LiveDataManagement.Backend;

import reactor.core.publisher.Mono;

//Interface
public interface LiveDataCollector {
	
	public Mono<Object> liveDataFetcher();

}
