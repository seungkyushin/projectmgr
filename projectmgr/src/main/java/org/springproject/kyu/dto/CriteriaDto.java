package org.springproject.kyu.dto;

public class CriteriaDto {

	private int id;
	private int start;
	private int offset;
	private int end;
	
	public CriteriaDto(int id, int start, int offset, int end) {
		this.id = id;
		this.start = start;
		this.offset = offset;
		this.end = end;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "Criteria [id=" + id + ", start=" + start + ", offset=" + offset + ", end=" + end + "]";
	}

}
