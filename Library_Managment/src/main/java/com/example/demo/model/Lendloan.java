package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.Objects;



public class Lendloan {
	
	private  LocalDateTime takenOut ;
	
    private  LocalDateTime dueBack ;
    private int LoanReturnId;
    private  String borrowerName;
    private  String tittleName;
    private  String tittleType;
    private boolean isAvailable;
    private boolean isOverdue;
    private int totalcopieslended;
    private int tittleId;
	public LocalDateTime getTakenOut() {
		return takenOut;
	}
	public void setTakenOut(LocalDateTime takenOut) {
		this.takenOut = takenOut;
	}
	public LocalDateTime getDueBack() {
		return dueBack;
	}
	public void setDueBack(LocalDateTime dueBack) {
		this.dueBack = dueBack;
	}
	public int getLoanReturnId() {
		return LoanReturnId;
	}
	public void setLoanReturnId(int loanReturnId) {
		LoanReturnId = loanReturnId;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
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
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public boolean isOverdue() {
		return isOverdue;
	}
	public void setOverdue(boolean isOverdue) {
		this.isOverdue = isOverdue;
	}
	public int getTotalcopieslended() {
		return totalcopieslended;
	}
	public void setTotalcopieslended(int totalcopieslended) {
		this.totalcopieslended = totalcopieslended;
	}
	public int getTittleId() {
		return tittleId;
	}
	public void setTittleId(int tittleId) {
		this.tittleId = tittleId;
	}
	@Override
	public String toString() {
		return "Lendloan [takenOut=" + takenOut + ", dueBack=" + dueBack + ", LoanReturnId=" + LoanReturnId
				+ ", borrowerName=" + borrowerName + ", tittleName=" + tittleName + ", tittleType=" + tittleType
				+ ", isAvailable=" + isAvailable + ", isOverdue=" + isOverdue + ", totalcopieslended="
				+ totalcopieslended + ", tittleId=" + tittleId + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(LoanReturnId, borrowerName, dueBack, isAvailable, isOverdue, takenOut, tittleId, tittleName,
				tittleType, totalcopieslended);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lendloan other = (Lendloan) obj;
		return LoanReturnId == other.LoanReturnId && Objects.equals(borrowerName, other.borrowerName)
				&& Objects.equals(dueBack, other.dueBack) && isAvailable == other.isAvailable
				&& isOverdue == other.isOverdue && Objects.equals(takenOut, other.takenOut)
				&& tittleId == other.tittleId && Objects.equals(tittleName, other.tittleName)
				&& Objects.equals(tittleType, other.tittleType) && totalcopieslended == other.totalcopieslended;
	}
	
	
}
