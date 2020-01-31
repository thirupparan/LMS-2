package com.invicta.lms.dto.mapper;

import com.invicta.lms.dto.NotificationDtoRequest;
import com.invicta.lms.dto.UserDtoRequest;
import com.invicta.lms.entity.Notification;

public class NotificationRequestDtoMapper {
	public static Notification mapNotificationRequestDtoToNotification(NotificationDtoRequest notificationDtoRequest) {
		Notification notification = new Notification();
		notification.setMessage(notificationDtoRequest.getMessage());
		UserDtoRequest user = new UserDtoRequest();
		user.setId(notificationDtoRequest.getUser());
		return notification;

	}

}
