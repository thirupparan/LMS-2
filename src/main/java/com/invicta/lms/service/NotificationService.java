package com.invicta.lms.service;

import java.util.ArrayList;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.invicta.lms.entity.Notification;
import com.invicta.lms.entity.User;

public interface NotificationService {
//	public Map<String, Object> updateUserNotification(Notification notitfication, User user);
//
//	Notification save(Notification notification);
//
//	public Notification findByUser(User user);
//
//	public List<Notification> findByUser(User user, Integer limit);
//
//	//public Notification createNotificationObject(String message, User user);
//
//	//public Notification findByUserAndNotificationId(User user, Long notificationId);
//
//	public User getLoggedInUser();
//	
//	public void notify(Notification notification,User user);

	public ArrayList<Notification> getAll();

	public Notification get(Long id);

	public void addEmitter(final SseEmitter emitter);

	public void removeEmitter(final SseEmitter emitter);

	// public List<Notification> findByUser(User user,Integer limit);

	public void setUserId(Long userId);

	public Notification addNotification(Notification notification, User user);
}
