package org.springproject.kyu.dto;



public class ProjectDto {

	private int id;
	private String name;
	private String url;
	private String description;
	private String subDescription;
	private int fileId;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSubDescription() {
		return subDescription;
	}
	public void setSubDescription(String subDescription) {
		this.subDescription = subDescription;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	@Override
	public String toString() {
		return "ProjectDto [id=" + id + ", name=" + name + ", url=" + url + ", description=" + description
				+ ", subDescription=" + subDescription + ", fileId=" + fileId + "]";
	}

	
	
}
