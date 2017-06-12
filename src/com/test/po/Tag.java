package com.test.po;

import java.io.Serializable;

import com.fasterxml.jackson.core.SerializableString;

public class Tag implements Serializable{
	
	private Integer tagid;
	private String tagname;
	private Integer tagstate;
	public Integer getTagid() {
		return tagid;
	}
	public void setTagid(Integer tagid) {
		this.tagid = tagid;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public Integer getTagstate() {
		return tagstate;
	}
	public void setTagstate(Integer tagstate) {
		this.tagstate = tagstate;
	}
	public Tag(Integer tagid, String tagname, Integer tagstate) {
		super();
		this.tagid = tagid;
		this.tagname = tagname;
		this.tagstate = tagstate;
	}
	public Tag() {
		super();
	}
	
	
}
