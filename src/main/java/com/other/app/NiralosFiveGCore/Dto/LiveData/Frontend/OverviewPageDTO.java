package com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend;

import java.util.ArrayList;

import com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph.GnbGraphDTO;
import com.other.app.NiralosFiveGCore.Dto.LiveData.Frontend.graph.UeGraphDTO;
import com.other.app.NiralosFiveGCore.model.LiveDataModel;

public class OverviewPageDTO {
	
	LiveDataModel liveDataModel;
	ArrayList<GnbGraphDTO> gnbGraphDTOs;
	ArrayList<UeGraphDTO> ueGraphDTOs;

	public OverviewPageDTO(LiveDataModel liveDataModel, ArrayList<GnbGraphDTO> gnbGraphDTOs,
			ArrayList<UeGraphDTO> ueGraphDTOs) {
		super();
		this.liveDataModel = liveDataModel;
		this.gnbGraphDTOs = gnbGraphDTOs;
		this.ueGraphDTOs = ueGraphDTOs;
	}

	public OverviewPageDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LiveDataModel getLiveDataModel() {
		return liveDataModel;
	}

	public void setLiveDataModel(LiveDataModel liveDataModel) {
		this.liveDataModel = liveDataModel;
	}

	public ArrayList<GnbGraphDTO> getGnbGraphDTOs() {
		return gnbGraphDTOs;
	}

	public void setGnbGraphDTOs(ArrayList<GnbGraphDTO> gnbGraphDTOs) {
		this.gnbGraphDTOs = gnbGraphDTOs;
	}

	public ArrayList<UeGraphDTO> getUeGraphDTOs() {
		return ueGraphDTOs;
	}

	public void setUeGraphDTOs(ArrayList<UeGraphDTO> ueGraphDTOs) {
		this.ueGraphDTOs = ueGraphDTOs;
	}


}
