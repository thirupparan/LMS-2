package com.invicta.lms.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import com.invicta.lms.dto.NotificationDtoResponse;
import com.invicta.lms.dto.UserDtoResponse;
import com.invicta.lms.entity.Notification;

public class NotificationResponseDtoMapper {
	public static NotificationDtoResponse mapNotificationToNotificationDtoResponse(Notification notification) {
		NotificationDtoResponse notificationDtoResponse = new NotificationDtoResponse();
		notificationDtoResponse.setMessage(notification.getMessage());
		notificationDtoResponse.setCreatedAt(notification.getCreatedAt());
		notificationDtoResponse.setUpdatedAt(notification.getUpdatedAt());

		UserDtoResponse user = new UserDtoResponse();
		user.setUserName(notification.getUser().getUserName());

		notificationDtoResponse.setUserName(user.getUserName());
		if (notification.isRead()) {
			notificationDtoResponse.setIsRead(true);
		} else {
			notificationDtoResponse.setIsRead(false);
		}

		return notificationDtoResponse;

	}

	public static List<NotificationDtoResponse> mapNotificationListToNotificationDtoList(
			List<Notification> notificationList) {

		List<NotificationDtoResponse> notificationDtoList = new ArrayList<NotificationDtoResponse>();
		if (notificationList != null) {
			for (Notification notification : notificationList) {
				notificationDtoList.add(mapNotificationToNotificationDtoResponse(notification));
			}
		}

		return notificationDtoList;

	}

}
