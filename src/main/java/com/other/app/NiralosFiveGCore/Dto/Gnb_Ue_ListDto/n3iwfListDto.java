package com.other.app.NiralosFiveGCore.Dto.Gnb_Ue_ListDto;

import java.util.ArrayList;

public class n3iwfListDto {
    private String n3iwfId;
    private String  ip;
    private  String tac;
    private  String totaln3iwfSession;
    private String activen3iwfSession;
    private ArrayList<n3iwueListDto>n3iwueList;

    public String getN3iwfId() {
        return n3iwfId;
    }

    public void setN3iwfId(String n3iwfId) {
        this.n3iwfId = n3iwfId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTac() {
        return tac;
    }

    public void setTac(String tac) {
        this.tac = tac;
    }

    public String getTotaln3iwfSession() {
        return totaln3iwfSession;
    }

    public void setTotaln3iwfSession(String totaln3iwfSession) {
        this.totaln3iwfSession = totaln3iwfSession;
    }

    public String getActiven3iwfSession() {
        return activen3iwfSession;
    }

    public void setActiven3iwfSession(String activen3iwfSession) {
        this.activen3iwfSession = activen3iwfSession;
    }

    public ArrayList<n3iwueListDto> getN3iwueList() {
        return n3iwueList;
    }

    public void setN3iwueList(ArrayList<n3iwueListDto> n3iwueList) {
        this.n3iwueList = n3iwueList;
    }
}
