package com.invicta.lms.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.invicta.lms.entity.Notification;
import com.invicta.lms.entity.User;
import com.invicta.lms.entity.mapper.NotificationResponseDtoMapper;
import com.invicta.lms.repository.NotificationRepository;
import com.invicta.lms.service.NotificationService;

@Service
@EnableScheduling
public class NotificationServiceImpl implements NotificationService {
	private Long userId;
	private Integer previousCount;
	private Integer currentCount;

	@Override
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setNotificationRepository(NotificationRepository notificationRepository) {
		this.notificationRepository = notificationRepository;
	}

	// final DateFormat DATE_FORMATTER = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss
	// a");
	final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
	@Autowired
	private NotificationRepository notificationRepository;

	@Override
	public void addEmitter(final SseEmitter emitter) {
		emitters.add(emitter);
	}

	@Override
	public void removeEmitter(final SseEmitter emitter) {
		emitters.remove(emitter);
	}
//	@Async
//	@Scheduled(fixedRate = 2000)
//	public void doNotify() throws IOException {
//		List<SseEmitter> deadEmitters = new ArrayList<>();
//		emitters.forEach(emitter -> {
//			try {
//				emitter.send(SseEmitter.event()
//						.data(DATE_FORMATTER.format(new Date()) + " : " + UUID.randomUUID().toString()));
//			} catch (Exception e) {
//				deadEmitters.add(emitter);
//			}
//		});
//		emitters.removeAll(deadEmitters);
//	}

	@Async
	@Scheduled(fixedRate = 1500)
	public void setPreviousCount() {
		this.previousCount = this.notificationRepository.userNotificationCount(this.userId);
		System.out.println("Previouscount : " + this.previousCount);
	}

	@Async
	@Scheduled(fixedRate = 200)
	public void setCurrentCount() {
		this.currentCount = this.notificationRepository.userNotificationCount(this.userId);
		System.out.println("Currentcount : " + this.currentCount);
	}

	@Async
	@Override
	@Scheduled(fixedRate = 2000)
	public ArrayList<Notification> getAll() {
		List<SseEmitter> deadEmitters = new ArrayList<>();
		if (this.currentCount - this.previousCount != 0) {
			System.out.println("Event Fired");
			emitters.forEach(emitter -> {
				try {
					emitter.send(SseEmitter.event().data(NotificationResponseDtoMapper.mapNotificationListToNotificationDtoList(this.notificationRepository.userNotification(this.userId))));
				} catch (Exception e) {
					deadEmitters.add(emitter);
				}
			});
		} else {
			System.out.println("Not Fired");
		}
		// TODO Auto-generated method stub
		emitters.removeAll(deadEmitters);
		return null;
	}

	@Override
	public Notification get(Long id) throws EntityNotFoundException {
		Optional<Notification> notification = this.notificationRepository.findById(id);
		if (notification.isPresent()) {
			return notification.get();
		} else {
			throw new EntityNotFoundException();
		}
	}

//	@Async
//	@Override
//	@Scheduled(fixedRate = 2000)
//	public List<Notification> findByUser(User user, Integer limit) {
//		List<SseEmitter> deadEmitters = new ArrayList<>();
//		emitters.forEach(emitter -> {
//			try {
//				emitter.send(SseEmitter.event().data(this.notificationRepository.userNotification(user.getId(), new PageRequest(0, limit))));
//			} catch (Exception e) {
//				deadEmitters.add(emitter);
//			}
//		});
//		emitters.removeAll(deadEmitters);
//		// TODO Auto-generated method stub
//		return null;
//	}
	@Override
	public Notification addNotification(Notification notification, User user) {
		if (notification != null) {
			notification.setRead(false);
			notification.setUser(user);
			//notification.setMessage("leave Request");
			return notificationRepository.save(notification);
		}
		return null;
	}

}
