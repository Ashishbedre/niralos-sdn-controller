package com.other.app.NiralosFiveGCore.Dto.InternalData;

public class SiteInformationDto {
	private Boolean provisionStatus;
	private String siteName;
	private String tenantName;
	private String streetName;
	private String city;
	private String pinCode;
	private String state;
	private String country;
	private String fullName;
	private String contact;
	private String email;
	private String deploymentId;
	private String localSdnVersion;
	private String fiveGcoreVersion;
	private String kafkaPartition;
	public String globalControllerIp;
    public String globalControllerPort;
    

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getLocalSdnVersion() {
		return localSdnVersion;
	}

	public void setLocalSdnVersion(String localSdnVersion) {
		this.localSdnVersion = localSdnVersion;
	}

	public String getFiveGcoreVersion() {
		return fiveGcoreVersion;
	}

	public void setFiveGcoreVersion(String fiveGcoreVersion) {
		this.fiveGcoreVersion = fiveGcoreVersion;
	}

	public String getKafkaPartition() {
		return kafkaPartition;
	}

	public void setKafkaPartition(String kafkaPartition) {
		this.kafkaPartition = kafkaPartition;
	}

	public Boolean getProvisionStatus() {
		return provisionStatus;
	}

	public void setProvisionStatus(Boolean provisionStatus) {
		this.provisionStatus = provisionStatus;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}



	public String getGlobalControllerIp() {
		return globalControllerIp;
	}

	public void setGlobalControllerIp(String globalControllerIp) {
		this.globalControllerIp = globalControllerIp;
	}

	public String getGlobalControllerPort() {
		return globalControllerPort;
	}

	public void setGlobalControllerPort(String globalControllerPort) {
		this.globalControllerPort = globalControllerPort;
	}




}
