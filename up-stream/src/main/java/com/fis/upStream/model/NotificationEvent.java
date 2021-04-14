package com.fis.upStream.model;

import java.util.Date;

public class NotificationEvent {
    private Integer receiverId;
    private String content;
    private Date timestamp;

    public NotificationEvent(Integer receiverId, String content, Date timestamp) {
        this.receiverId = receiverId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public NotificationEvent() {
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
