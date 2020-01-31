package com.invicta.lms.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.invicta.lms.converter.PermissionMapConverter;
import com.invicta.lms.converter.SubMenuMapConverter;
import com.invicta.lms.converter.dto.SubMenuDto;

@Entity
@Table(schema = "leave_system", name = "permission")
public class Permission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String menuName;
	private String menuLink;
	private String icon;

	/* This converter does the trick */
	@Convert(converter = PermissionMapConverter.class)
	private List<String> permission;

	@Convert(converter = SubMenuMapConverter.class)
	@Column(length=1000)
	private List<SubMenuDto> subMenu;

	public Permission(Long id, String menuName, String menuLink, String icon, List<String> permission,
			List<SubMenuDto> subMenu) {
		this.id = id;
		this.menuName = menuName;
		this.menuLink = menuLink;
		this.icon = icon;
		this.permission = permission;
		this.subMenu = subMenu;
	}
	public Permission() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<String> getPermission() {
		return permission;
	}

	public void setPermission(List<String> permission) {
		this.permission = permission;
	}

	public List<SubMenuDto> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<SubMenuDto> subMenu) {
		this.subMenu = subMenu;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", menuName=" + menuName + ", menuLink=" + menuLink + ", icon=" + icon
				+ ", permission=" + permission + ", subMenu=" + subMenu + "]";
	}

	
	
}
