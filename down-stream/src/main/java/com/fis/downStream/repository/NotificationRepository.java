package com.fis.downStream.repository;


import com.fis.downStream.model.Notification;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, String> {
}
