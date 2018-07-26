package com.s4hanaextension.dataadjustments.models;

import javax.persistence.Entity;

@Entity
public class FileRecord{
	
	private String IFilepath;
	private String Type;
	
	public FileRecord(String filepath, String type) {
		this.IFilepath = filepath;
		this.Type = type;
	}
	
	public String getIFilepath() {
		return IFilepath;
	}
	public void setIFilepath(String iFilepath) {
		IFilepath = iFilepath;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	
}