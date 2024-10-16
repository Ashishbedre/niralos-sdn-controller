package com.other.app.niralos_edge.dto.hypervisorlogdto;

import java.util.ArrayList;
import java.util.List;

public class HypervisorLogRootDto {
	
	private Long total;
	
    private List<Data> data = new ArrayList<Data>();

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}
    
    

}
