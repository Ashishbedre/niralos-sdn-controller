package com.other.app.niralos_edge.dto.HardwaraVM;

import java.time.Instant;

public class TokenDetails {
    private final String ticket;
    private final String csrfToken;
    private final Instant expirationTime;

    public TokenDetails(String ticket, String csrfToken, Instant expirationTime) {
        this.ticket = ticket;
        this.csrfToken = csrfToken;
        this.expirationTime = expirationTime;
    }

    public String getTicket() {
        return ticket;
    }

    public String getCsrfToken() {
        return csrfToken;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expirationTime);
    }
}
