package com.other.app.niralos_edge.Service.EdgeHardware;

//import com.other.app.niralos_edge.dto.VMUpdateRequest;
import reactor.core.publisher.Mono;

import javax.net.ssl.SSLException;
import java.util.Map;

public interface EdgeVMHardwaraService {

    public Mono<Void> updateVMConfig(Map<String, Object> request, Long vmId, String edgeClientId) throws SSLException;
}
