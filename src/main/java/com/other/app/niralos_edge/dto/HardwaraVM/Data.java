package com.other.app.niralos_edge.dto.HardwaraVM;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;



@JsonIgnoreProperties(ignoreUnknown = true)
public  class Data {
    @JsonProperty("ticket")
    private String ticket;

    @JsonProperty("CSRFPreventionToken")
    private String csrfPreventionToken;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getCsrfPreventionToken() {
        return csrfPreventionToken;
    }

    public void setCsrfPreventionToken(String csrfPreventionToken) {
        this.csrfPreventionToken = csrfPreventionToken;
    }
}
