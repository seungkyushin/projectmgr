package org.springproject.kyu.dto;

public class SearchDto {
	
	private String type;
	private String keyWord;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getkeyWord() {
		return keyWord;
	}
	public void setkeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	@Override
	public String toString() {
		return "SearchDto [type=" + type + ", keyWord=" + keyWord + "]";
	}
	
	

}
