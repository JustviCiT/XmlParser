package com.xmlparser;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace="item")
public class Item {
	
	private String gId;
	private String id;
	private String title;
	private String description;
	private String link;
	private String image_link;
	private String mobile_link;
	private ArrayList<String> additional_image_link;
	private String availability;
	private String price;
	private String google_product_category;
	private String brand;
	private String mpn;
	private String identifier_exists;
	private String condition;
	private String material;
	private String size;
	private String age_group;
	private String adult;
//		<g:custom_label_0>Birth year 1949</g:custom_label_0>
	
	public Item () {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getgId() {
		return gId;
	}

	public void setgId(String gId) {
		this.gId = gId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImage_link() {
		return image_link;
	}

	public void setImage_link(String image_link) {
		this.image_link = image_link;
	}

	public String getMobile_link() {
		return mobile_link;
	}

	public void setMobile_link(String mobile_link) {
		this.mobile_link = mobile_link;
	}

	public ArrayList<String> getAdditional_image_link() {
		return additional_image_link;
	}

	public void setAdditional_image_link(ArrayList<String> additional_image_link) {
		this.additional_image_link = additional_image_link;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getGoogle_product_category() {
		return google_product_category;
	}

	public void setGoogle_product_category(String google_product_category) {
		this.google_product_category = google_product_category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMpn() {
		return mpn;
	}

	public void setMpn(String mpn) {
		this.mpn = mpn;
	}

	public String getIdentifier_exists() {
		return identifier_exists;
	}

	public void setIdentifier_exists(String identifier_exists) {
		this.identifier_exists = identifier_exists;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getAge_group() {
		return age_group;
	}

	public void setAge_group(String age_group) {
		this.age_group = age_group;
	}

	public String getAdult() {
		return adult;
	}

	public void setAdult(String adult) {
		this.adult = adult;
	}
	
	
}
