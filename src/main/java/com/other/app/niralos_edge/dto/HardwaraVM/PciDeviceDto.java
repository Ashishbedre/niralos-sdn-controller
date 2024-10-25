package com.other.app.niralos_edge.dto.HardwaraVM;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PciDeviceDto {
    private String id;
    private String vendor_name;
    private String vendor;
    private String subsystem_device;
    private String subsystem_vendor_name;

    private String device;
    private String subsystem_vendor;

    @JsonProperty("class")
    private String classs;
    private String iommugroup;

    private String device_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getSubsystem_device() {
        return subsystem_device;
    }

    public void setSubsystem_device(String subsystem_device) {
        this.subsystem_device = subsystem_device;
    }

    public String getSubsystem_vendor_name() {
        return subsystem_vendor_name;
    }

    public void setSubsystem_vendor_name(String subsystem_vendor_name) {
        this.subsystem_vendor_name = subsystem_vendor_name;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getSubsystem_vendor() {
        return subsystem_vendor;
    }

    public void setSubsystem_vendor(String subsystem_vendor) {
        this.subsystem_vendor = subsystem_vendor;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public String getIommugroup() {
        return iommugroup;
    }

    public void setIommugroup(String iommugroup) {
        this.iommugroup = iommugroup;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }
}