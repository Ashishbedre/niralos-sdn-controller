package com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend;

import java.util.ArrayList;

import com.other.app.NiralosFiveGCore.model.LiveDataModel;
import com.other.app.NiralosFiveGCore.model.Graph.CombineGraphData;

public class OverviewPageDTO {
	
	LiveDataModel liveDataModel;
	ArrayList<CombineGraphData> combineGraphData;
	public LiveDataModel getLiveDataModel() {
		return liveDataModel;
	}
	public void setLiveDataModel(LiveDataModel liveDataModel) {
		this.liveDataModel = liveDataModel;
	}

	
	
	
//	ArrayList<GnbGraphDTO> gnbGraphDTOs;
//	ArrayList<UeGraphDTO> ueGraphDTOs;

public ArrayList<CombineGraphData> getCombineGraphData() {
		return combineGraphData;
	}
	public void setCombineGraphData(ArrayList<CombineGraphData> combineGraphData) {
		this.combineGraphData = combineGraphData;
	}
	//	public OverviewPageDTO(LiveDataModel liveDataModel, ArrayList<GnbGraphDTO> gnbGraphDTOs,
//			ArrayList<UeGraphDTO> ueGraphDTOs) {
//		super();
//		this.liveDataModel = liveDataModel;
//		this.gnbGraphDTOs = gnbGraphDTOs;
//		this.ueGraphDTOs = ueGraphDTOs;
//	}
//
	
	
	public OverviewPageDTO() {
		super();
	}
//
//	public LiveDataModel getLiveDataModel() {
//		return liveDataModel;
//	}
//
//	public void setLiveDataModel(LiveDataModel liveDataModel) {
//		this.liveDataModel = liveDataModel;
//	}
//
//	public ArrayList<GnbGraphDTO> getGnbGraphDTOs() {
//		return gnbGraphDTOs;
//	}
//
//	public void setGnbGraphDTOs(ArrayList<GnbGraphDTO> gnbGraphDTOs) {
//		this.gnbGraphDTOs = gnbGraphDTOs;
//	}
//
//	public ArrayList<UeGraphDTO> getUeGraphDTOs() {
//		return ueGraphDTOs;
//	}
//
//	public void setUeGraphDTOs(ArrayList<UeGraphDTO> ueGraphDTOs) {
//		this.ueGraphDTOs = ueGraphDTOs;
//	}
	public OverviewPageDTO(LiveDataModel liveDataModel, ArrayList<CombineGraphData> combineGraphData) {
		super();
		this.liveDataModel = liveDataModel;
		this.combineGraphData = combineGraphData;
	}


}
