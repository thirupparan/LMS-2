package com.invicta.lms.dto;

import java.util.List;

import com.invicta.lms.converter.dto.SubMenuDto;

public class MenuResponseDto {

	private String menuName;
	private String menuLink;
	private String icon;
	private List<SubMenuDto> subMenu;
	
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuLink() {
		return menuLink;
	}
	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<SubMenuDto> getSubMenu() {
		return subMenu;
	}
	public void setSubMenu(List<SubMenuDto> subMenu) {
		this.subMenu = subMenu;
	}
	
}
