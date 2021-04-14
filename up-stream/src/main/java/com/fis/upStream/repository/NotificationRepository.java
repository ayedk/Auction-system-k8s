package com.fis.upStream.repository;


import com.fis.upStream.model.Notification;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, String> {
}
