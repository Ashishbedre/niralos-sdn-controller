package com.other.app.NiralosFiveGCore.Dto.UeStacDto.Frontend;

public class StatusCountDTO {

	  private long totalActive;
	    private long totalInactive;
	 
	    // Constructors
	    public StatusCountDTO() {}
	 
	    public StatusCountDTO(long totalActive, long totalInactive) {
	        this.totalActive = totalActive;
	        this.totalInactive = totalInactive;
	    }
	 
	    // Getters and setters
	    public long getTotalActive() {
	        return totalActive;
	    }
	 
	    public void setTotalActive(long totalActive) {
	        this.totalActive = totalActive;
	    }
	 
	    public long getTotalInactive() {
	        return totalInactive;
	    }
	 
	    public void setTotalInactive(long totalInactive) {
	        this.totalInactive = totalInactive;
	    }
}
