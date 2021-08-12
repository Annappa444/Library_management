package com.example.demo.model;

public class TitleCreateRequest {
	
	private  String tittleName;
    private  String tittleType;
    private int totalcopiestobeAdded;
	public String getTittleName() {
		return tittleName;
	}
	public void setTittleName(String tittleName) {
		this.tittleName = tittleName;
	}
	public String getTittleType() {
		return tittleType;
	}
	public void setTittleType(String tittleType) {
		this.tittleType = tittleType;
	}
	public int getTotalcopiestobeAdded() {
		return totalcopiestobeAdded;
	}
	public void setTotalcopiestobeAdded(int totalcopiestobeAdded) {
		this.totalcopiestobeAdded = totalcopiestobeAdded;
	}
	@Override
	public String toString() {
		return "TitleCreateRequest [tittleName=" + tittleName + ", tittleType=" + tittleType + ", totalcopiestobeAdded="
				+ totalcopiestobeAdded + "]";
	}
	
    
    

}
