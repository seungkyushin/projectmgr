package org.springproject.kyu.dto;

public class CommentPageDto {
	private int reqPageNum;
	private int projectId;
	private int start;
	private int end;
	
	public int getReqPageNum() {
		return reqPageNum;
	}
	public void setReqPageNum(int reqPageNum) {
		this.reqPageNum = reqPageNum;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "CommentPageDto [reqPageNum=" + reqPageNum + ", projectId=" + projectId + ", start=" + start + ", end="
				+ end + "]";
	}
	

}
