package com.fis.downStream.service;

import com.fis.downStream.model.Notification;
import com.fis.downStream.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void addNotification(Notification NotificationRecord)
    {
        notificationRepository.save(NotificationRecord);
    }
}
