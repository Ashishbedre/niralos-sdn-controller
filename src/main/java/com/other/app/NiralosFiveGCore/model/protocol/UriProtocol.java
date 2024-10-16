package com.other.app.NiralosFiveGCore.model.protocol;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UriProtocol {
	@Value("${fivegcoreProtocol}")
	private String fivegcoreProtocol;
    @Value("${fivegcorePfcpUri}")
    private String fivegcorePfcpUri;
    @Value("${fivegcoreNrfInfoUri}")
    private String fivegcoreNrfInfoUri;
    @Value("${fivegcoreUpfInfoUri}")
    private String fivegcoreUpfInfoUri;
    //Ue-Session and Ue-History Common end points
    @Value("${fivegcoreUeHistoryandsessionfileListUri}")
    private String fivegcoreUeHistoryandsessionfileListUri;
    @Value("${fivegcoreSessionfileJsonUri}")
    private String fivegcoreSessionfileJsonUri;
    @Value("${fivegcoreUeHistoryUri}")
    private String fivegcoreUeHistoryUri;
    @Value("${deletestatfilejson}")
    private String deletestatfilejson;
    @Value("${fivegcoreUePduCauseInfoUri}")
    private String fivegcoreUePduCauseInfoUri;
    @Value("${fivegcoreUpgError}")
    private String fivegcoreUpgError;
    @Value("${fivegcoreUpgservice}")
    private String fivegcoreUpgservice;
    @Value("${fivegcoreAlertData}")
    private String fivegcoreAlertData;
    @Value("${fivegcoreAlarmDelete}")
    private String fivegcoreAlarmDelete;
    @Value("${fivegcoreGnbStatsCollector}")
    private String fivegcoreGnbStatsCollector;
    @Value("${fivegcoreGnbUeCountLiveData}")
    private String fivegcoreGnbUeCountLiveData;
    @Value("${fivegCoreLogData}")
    private String fivegCoreLogData;
    @Value("${fivegCorePostLogLevel}")
    private String fivegCorePostLogLevel;
    @Value("${fivegcoreThroughputCollector}")
    private String fivegcoreThroughputCollector;
    @Value("${fivegcoreGnbInfo}")
    private String fivegcoreGnbInfo;
    @Value("${updater.agent.ip}")
    private String updatedAgentIp;
    
    @Value("${updatedAgentPortNo}")
    private String updatedAgentPortNo;
    public String getUpdatedAgenPortNo() {
  		return updatedAgentPortNo;
  	}
    
    public String getUpdatedAgentIp() {
		return updatedAgentIp;
	}
    public String getFivegcoreGnbInfo() {
		return fivegcoreGnbInfo;
	}
	public String getFivegcoreThroughputCollector() {
		return fivegcoreThroughputCollector;
	}
	public String getFivegCorePostLogLevel() {
		return fivegCorePostLogLevel;
	}
	public String getFivegCoreLogData() {
		return fivegCoreLogData;
	}
	public String getFivegcoreGnbUeCountLiveData() {
		return fivegcoreGnbUeCountLiveData;
	}
	public String getFivegcoreGnbStatsCollector() {
		return fivegcoreGnbStatsCollector;
	}
	public String getFivegcoreAlarmDelete() {
		return fivegcoreAlarmDelete;
	}

	public String getFivegcoreAlertData() {
		return fivegcoreAlertData;
	}

	public String getFivegcoreUpgservice() {
		return fivegcoreUpgservice;
	}
	
	public String getFivegcoreUpgError() {
		return fivegcoreUpgError;
	}
	public String getFivegcoreUePduCauseInfoUri() {
		return fivegcoreUePduCauseInfoUri;
	}

	public String getDeletedtatfilejson() {
		return deletestatfilejson;
	}
	
	public String getFivegcoreUeHistoryandsessionfileListUri() {
		return fivegcoreUeHistoryandsessionfileListUri;
	}
	public String getFivegcoreUeHistoryUri() {
		return fivegcoreUeHistoryUri;
	}
	public String getFivegcoreUpfInfoUri() {
		return fivegcoreUpfInfoUri;
	}

	public String getFivegcoreNrfInfoUri() {
		return fivegcoreNrfInfoUri;
	}

	public String getFivegcorePfcpUri() {
		return fivegcorePfcpUri;
	}

	public String getFivegcoreProtocol() {
        return fivegcoreProtocol;
    }
	public String getFivegcoreSessionfileJsonUri() {
		return fivegcoreSessionfileJsonUri;
	}
	
	
}
