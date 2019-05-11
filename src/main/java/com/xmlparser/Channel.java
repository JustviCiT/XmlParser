package com.xmlparser;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "channel")
public class Channel {
	private String title;
	private String link;
	private String description;
	private ArrayList<Item> item;
	private Integer Size;
   
	public Channel () {}
	
	public ArrayList<Item> getItem() {
		return item;
	}
	
	public void setItem(ArrayList<Item> item) {
		this.item = item;
		this.Size = this.item.size();
	}
	
	public Integer getSize() {
		return Size;
	}
	
	public void setSize(Integer Size) {
		this.Size = Size;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
   
}
