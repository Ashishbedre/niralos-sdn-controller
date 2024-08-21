package com.other.app.niralos_edge.dto.container;

public class DataDTO {
    private double cpu;
    private long disk;
    private long diskread;
    private long swap;
    private long maxswap;
    private long diskwrite;
    private long uptime;
    private String name;
    private long mem;
    private int vmid;
    private long maxdisk;
    private int cpus;
    private String type;
    private String status;
    private long maxmem;
    private HaDTO ha;
    private long netout;
    private long netin;

    public double getCpu() {
        return cpu;
    }

    public void setCpu(double cpu) {
        this.cpu = cpu;
    }

    public long getDisk() {
        return disk;
    }

    public void setDisk(long disk) {
        this.disk = disk;
    }

    public long getDiskread() {
        return diskread;
    }

    public void setDiskread(long diskread) {
        this.diskread = diskread;
    }

    public long getSwap() {
        return swap;
    }

    public void setSwap(long swap) {
        this.swap = swap;
    }

    public long getMaxswap() {
        return maxswap;
    }

    public void setMaxswap(long maxswap) {
        this.maxswap = maxswap;
    }

    public long getDiskwrite() {
        return diskwrite;
    }

    public void setDiskwrite(long diskwrite) {
        this.diskwrite = diskwrite;
    }

    public long getUptime() {
        return uptime;
    }

    public void setUptime(long uptime) {
        this.uptime = uptime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMem() {
        return mem;
    }

    public void setMem(long mem) {
        this.mem = mem;
    }

    public int getVmid() {
        return vmid;
    }

    public void setVmid(int vmid) {
        this.vmid = vmid;
    }

    public long getMaxdisk() {
        return maxdisk;
    }

    public void setMaxdisk(long maxdisk) {
        this.maxdisk = maxdisk;
    }

    public int getCpus() {
        return cpus;
    }

    public void setCpus(int cpus) {
        this.cpus = cpus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getMaxmem() {
        return maxmem;
    }

    public void setMaxmem(long maxmem) {
        this.maxmem = maxmem;
    }

    public HaDTO getHa() {
        return ha;
    }

    public void setHa(HaDTO ha) {
        this.ha = ha;
    }

    public long getNetout() {
        return netout;
    }

    public void setNetout(long netout) {
        this.netout = netout;
    }

    public long getNetin() {
        return netin;
    }

    public void setNetin(long netin) {
        this.netin = netin;
    }
}
