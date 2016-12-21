package project.jonneys.com.jonneyschao_project.bean;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable{

	private String stat;
	private List<Data> data;
	public String getstat() {
		return stat;
	}
	public void setstat(String stat) {
		this.stat = stat;
	}
	public List<Data> getData() {
		return data;
	}
	public void setData(List<Data> data) {
		this.data = data;
	}
	
	
}
