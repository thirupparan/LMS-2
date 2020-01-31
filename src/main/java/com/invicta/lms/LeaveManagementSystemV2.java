package com.invicta.lms;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Component;

import com.invicta.lms.converter.dto.SubMenuDto;
import com.invicta.lms.entity.Permission;
import com.invicta.lms.repository.LeaveManagerRepository;
import com.invicta.lms.repository.PermissionRepository;

@Component
@SpringBootApplication
@EntityScan(basePackageClasses = { LeaveManagementSystemV2.class, Jsr310JpaConverters.class })
public class LeaveManagementSystemV2 implements CommandLineRunner {
	@Autowired
	public PermissionRepository permissionRepository;

	@Autowired
	LeaveManagerRepository leaveManagerRepository;

	@PostConstruct
	public void init() {
		// Setting Spring Boot SetTimeZone
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	public static void main(String[] args) {
		SpringApplication.run(LeaveManagementSystemV2.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		permissionRepository.deleteAll();
		// JSON parser object to parse read file
		JSONParser parser = new JSONParser();
		try (FileReader reader = new FileReader("permission.json")) {
			Object obj = parser.parse(reader);
			JSONArray permissiojnList = (JSONArray) obj;
			// Iterate over array
			for (Object o : permissiojnList) {
				if (o instanceof JSONObject) {
					permissionListData((JSONObject) o);
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@SuppressWarnings("unchecked")
	public void permissionListData(JSONObject permission) {
		JSONObject jsonObject = (JSONObject) permission;
		Permission permissionentity = new Permission();
		permissionentity.setMenuName((String) jsonObject.get("menuName"));
		permissionentity.setMenuLink((String) jsonObject.get("menuLink"));
		permissionentity.setIcon((String) jsonObject.get("icon"));
		permissionentity.setPermission((List<String>) jsonObject.get("permission"));
		permissionentity.setSubMenu((List<SubMenuDto>) jsonObject.get("subMenu"));
		permissionRepository.save(permissionentity);

	}
}
