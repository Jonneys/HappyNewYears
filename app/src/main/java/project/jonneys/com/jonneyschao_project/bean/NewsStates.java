package project.jonneys.com.jonneyschao_project.bean;

import java.io.Serializable;

public class NewsStates implements Serializable{

	private String reason;
	private Result result;
	private String error_code;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	
	
}
