package com.other.app.niralos_edge.dto.HardwaraVM;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VmDataDTO {

    @JsonProperty("ide2")
    private String ide2;

    @JsonProperty("cores")
    private int cores;

    @JsonProperty("cpu")
    private String cpu;

    @JsonProperty("vmgenid")
    private String vmgenid;

    @JsonProperty("boot")
    private String boot;

    @JsonProperty("args")
    private String args;

    @JsonProperty("meta")
    private String meta;

    @JsonProperty("agent")
    private String agent;

    @JsonProperty("vga")
    private String vga;

    @JsonProperty("scsihw")
    private String scsihw;

    @JsonProperty("balloon")
    private int balloon;

    @JsonProperty("smbios1")
    private String smbios1;

    @JsonProperty("machine")
    private String machine;

    @JsonProperty("sockets")
    private int sockets;

    @JsonProperty("scsi1")
    private String scsi1;

    @JsonProperty("ostype")
    private String ostype;

    @JsonProperty("name")
    private String name;

    @JsonProperty("numa")
    private int numa;

    @JsonProperty("net0")
    private String net0;

    @JsonProperty("bios")
    private String bios;

    @JsonProperty("scsi0")
    private String scsi0;

    @JsonProperty("digest")
    private String digest;

    @JsonProperty("onboot")
    private int onboot;

    @JsonProperty("memory")
    private int memory;

    public String getIde2() {
        return ide2;
    }

    public void setIde2(String ide2) {
        this.ide2 = ide2;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getVmgenid() {
        return vmgenid;
    }

    public void setVmgenid(String vmgenid) {
        this.vmgenid = vmgenid;
    }

    public String getBoot() {
        return boot;
    }

    public void setBoot(String boot) {
        this.boot = boot;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getVga() {
        return vga;
    }

    public void setVga(String vga) {
        this.vga = vga;
    }

    public String getScsihw() {
        return scsihw;
    }

    public void setScsihw(String scsihw) {
        this.scsihw = scsihw;
    }

    public int getBalloon() {
        return balloon;
    }

    public void setBalloon(int balloon) {
        this.balloon = balloon;
    }

    public String getSmbios1() {
        return smbios1;
    }

    public void setSmbios1(String smbios1) {
        this.smbios1 = smbios1;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public int getSockets() {
        return sockets;
    }

    public void setSockets(int sockets) {
        this.sockets = sockets;
    }

    public String getScsi1() {
        return scsi1;
    }

    public void setScsi1(String scsi1) {
        this.scsi1 = scsi1;
    }

    public String getOstype() {
        return ostype;
    }

    public void setOstype(String ostype) {
        this.ostype = ostype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNuma() {
        return numa;
    }

    public void setNuma(int numa) {
        this.numa = numa;
    }

    public String getNet0() {
        return net0;
    }

    public void setNet0(String net0) {
        this.net0 = net0;
    }

    public String getBios() {
        return bios;
    }

    public void setBios(String bios) {
        this.bios = bios;
    }

    public String getScsi0() {
        return scsi0;
    }

    public void setScsi0(String scsi0) {
        this.scsi0 = scsi0;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public int getOnboot() {
        return onboot;
    }

    public void setOnboot(int onboot) {
        this.onboot = onboot;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }
}
