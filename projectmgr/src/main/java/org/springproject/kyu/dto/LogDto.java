package org.springproject.kyu.dto;


public class LogDto {

	private int id;
	private String createDate;
	private String type;
	private String description;
	private String visiterEmail;
	private String clientIp;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVisiterEmail() {
		return visiterEmail;
	}
	public void setVisiterEmail(String visiterEmail) {
		this.visiterEmail = visiterEmail;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	@Override
	public String toString() {
		return "LogDto [id=" + id + ", createDate=" + createDate + ", type=" + type + ", description=" + description
				+ ", visiterEmail=" + visiterEmail + ", clientIp=" + clientIp + "]";
	}
	
	
	
	
	
}
