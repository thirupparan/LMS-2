package com.invicta.lms.converter.dto;

public class SubMenuDto {
	private String subMenuName;
	private String subMenuLink;
	

	public String getSubMenuName() {
		return subMenuName;
	}

	public void setSubMenuName(String subMenuName) {
		this.subMenuName = subMenuName;
	}

	public String getSubMenuLink() {
		return subMenuLink;
	}

	public void setSubMenuLink(String subMenuLink) {
		this.subMenuLink = subMenuLink;
	}

	public SubMenuDto(String subMenuName, String subMenuLink) {
		this.subMenuName = subMenuName;
		this.subMenuLink = subMenuLink;
	}

	public SubMenuDto() {

	}

}
