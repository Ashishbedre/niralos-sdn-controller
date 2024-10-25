package com.other.app.niralos_edge.dto.HardwaraVM;

import java.util.List;

public class ProxmoxPciResponse {

    private List<PciDeviceDto> data;

    public List<PciDeviceDto> getData() {
        return data;
    }

    public void setData(List<PciDeviceDto> data) {
        this.data = data;
    }
}
