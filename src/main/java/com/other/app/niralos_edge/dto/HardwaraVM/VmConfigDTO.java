package com.other.app.niralos_edge.dto.HardwaraVM;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VmConfigDTO {

    @JsonProperty("data")
    private VmDataDTO data;

    // Getters and Setters
    public VmDataDTO getData() {
        return data;
    }

    public void setData(VmDataDTO data) {
        this.data = data;
    }

}
