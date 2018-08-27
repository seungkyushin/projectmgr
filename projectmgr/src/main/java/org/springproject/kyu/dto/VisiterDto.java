package org.springproject.kyu.dto;

public class VisiterDto {
	
	private int id;
	private String name;
	private String password;
	private String email;
	private String organization;
	private int fileId;
	private String createDate;
	private String lastLoginDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	@Override
	public String toString() {
		return "VisiterDto [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", organization=" + organization + ", fileId=" + fileId + ", createDate=" + createDate
				+ ", lastLoginDate=" + lastLoginDate + "]";
	}

	
	
	

}
