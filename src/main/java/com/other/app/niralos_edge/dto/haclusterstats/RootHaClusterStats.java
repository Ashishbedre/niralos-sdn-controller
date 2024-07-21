package com.other.app.niralos_edge.dto.haclusterstats;

import java.util.ArrayList;
import java.util.List;

public class RootHaClusterStats {
	
	private List<Data> data = new ArrayList<Data>();
    public List<Data> getData() {
        return data;
    }
    public void setData(List<Data> data) {
        this.data = data;
    }

}
