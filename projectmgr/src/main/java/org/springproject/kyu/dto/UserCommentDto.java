package org.springproject.kyu.dto;


public class UserCommentDto {
private int id;
private String type;
private String content;
private int score;
private String showCheck;
private int projectId;
private int visiterId;
private String createDate;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public int getScore() {
	return score;
}
public void setScore(int score) {
	this.score = score;
}
public String getShowCheck() {
	return showCheck;
}
public void setShowCheck(String showCheck) {
	this.showCheck = showCheck;
}
public int getProjectId() {
	return projectId;
}
public void setProjectId(int projectId) {
	this.projectId = projectId;
}
public int getVisiterId() {
	return visiterId;
}
public void setVisiterId(int visiterId) {
	this.visiterId = visiterId;
}
public String getCreateDate() {
	return createDate;
}
public void setCreateDate(String createDate) {
	this.createDate = createDate;
}
@Override
public String toString() {
	return "UserCommentDto [id=" + id + ", type=" + type + ", content=" + content + ", score=" + score + ", showCheck="
			+ showCheck + ", projectId=" + projectId + ", visiterId=" + visiterId + ", createDate=" + createDate + "]";
}




}
