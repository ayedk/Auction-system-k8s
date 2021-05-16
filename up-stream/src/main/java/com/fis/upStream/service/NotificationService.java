package com.fis.upStream.service;


import com.fis.upStream.model.Auction;
import com.fis.upStream.model.Bid;
import com.fis.upStream.model.Notification;
import com.fis.upStream.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getNotificationByBuyerId(Integer id){
        List<Notification> notificationRecords = new ArrayList<>();

        notificationRepository.findAll().forEach(notification->
        {
            if(notification.getReceiverId().equals(id)){
                notificationRecords.add(notification);
            }

        });
        return notificationRecords;
    }

    public List<Notification> getAllNotifications()
    {
        List<Notification>notificationRecords = new ArrayList<>();
        notificationRepository.findAll().forEach(notificationRecords::add);
        return notificationRecords;
    }
}
