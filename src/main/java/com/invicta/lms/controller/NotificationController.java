package com.invicta.lms.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.invicta.lms.dto.NotificationDtoRequest;
import com.invicta.lms.dto.mapper.NotificationRequestDtoMapper;
import com.invicta.lms.entity.mapper.NotificationResponseDtoMapper;
import com.invicta.lms.service.NotificationService;
import com.invicta.lms.service.UserService;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	// private final List<SseEmitter> emitters = new ArrayList<>();

	final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private UserService userService;

	@GetMapping("/user/{id}")
	public ResponseEntity<SseEmitter> events(@PathVariable("id") Long id) throws InterruptedException, IOException {
		SseEmitter emitter = new SseEmitter();
		notificationService.addEmitter(emitter);
		notificationService.setUserId(id);
		notificationService.getAll();
		emitter.onCompletion(() -> {
			notificationService.removeEmitter(emitter);
		});
		emitter.onError(throwable -> {
			emitters.remove(emitter);
		});
		emitter.onTimeout(() -> {
			// emitters.remove(emitter);
			notificationService.removeEmitter(emitter);
		});
		return new ResponseEntity<>(emitter,HttpStatus.OK);

	}

//	  private void handleNotification(Notification notification) {
//	        emitters.parallelStream().forEach(emitter -> {
//	            try {
//	                emitter.send(notification);
//	            } catch (IOException e) {
//	                emitter.complete();
//	            }
//	        });
//	    }
//	  
//	  @Scheduled(fixedDelay = 2000)
//	    public void receiveNotification() {
//	        this.handleNotification(this.notificationService.get((long) (Math.random() * (100 - 1)) + 1));
//	    }

//	@GetMapping("/notification")
//	public ResponseEntity<SseEmitter> doNotify() throws InterruptedException, IOException {
//		final SseEmitter emitter = new SseEmitter();
//		notificationService.addEmitter(emitter);
//		notificationService.doNotify();
//		emitter.onCompletion(() -> notificationService.removeEmitter(emitter));
//		emitter.onTimeout(() -> notificationService.removeEmitter(emitter));
//		return new ResponseEntity<>(emitter, HttpStatus.OK);
//	}

//	  @GetMapping("/user/{id}")
//	  public ResponseEntity<List<Notification>>get(@PathVariable("id") User id,Integer limit){
//		return new ResponseEntity<>(this.notificationService.findByUser(id, limit),HttpStatus.OK);
//		  
//	  }
//	  @RequestMapping(value="/notifications/user",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
//		public ResponseEntity<Object> getNotificationsByUser(@RequestParam(required=false,defaultValue="") String limit){
//		  List<Notification> notifications = notificationService.findByUser(user, limit);
//		  
//			return null;
//		  
//	  }
	@PostMapping
	public ResponseEntity<?> createNotification(@RequestBody NotificationDtoRequest notificationDtoRequest) {
		return new ResponseEntity<>(NotificationResponseDtoMapper
				.mapNotificationToNotificationDtoResponse(notificationService.addNotification(
						NotificationRequestDtoMapper.mapNotificationRequestDtoToNotification(notificationDtoRequest),
						userService.findUserById(notificationDtoRequest.getUser()))),
				HttpStatus.CREATED);

	}

}
