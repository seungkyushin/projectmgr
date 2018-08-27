package org.springproject.kyu.dto;

public class FileInfoDto {
	
	private int id;
	private String savePath;
	private String urlPath;
	private String type;
	private String name;
	private String createDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getUrlPath() {
		return urlPath;
	}
	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "FileInfoDto [id=" + id + ", savePath=" + savePath + ", urlPath=" + urlPath + ", type=" + type
				+ ", name=" + name + ", createDate=" + createDate + "]";
	}

	

}
