package com.other.app.NiralosFiveGCore.model.mqtt;

import java.util.Date;

public class CustomMessage {

    private String messageId;
    private String message;
    private Date messageDate;
    private String tenantId;
    private String siteId;

    public CustomMessage() {
        // Default constructor
    }

    public CustomMessage(String messageId, String message, Date messageDate) {
        this.messageId = messageId;
        this.message = message;
        this.messageDate = messageDate;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }
    public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	@Override
    public String toString() {
        return "CustomMessage{" +
                "messageId='" + messageId + '\'' +
                ", message='" + message + '\'' +
                ", messageDate=" + messageDate +
                '}';
    }
}