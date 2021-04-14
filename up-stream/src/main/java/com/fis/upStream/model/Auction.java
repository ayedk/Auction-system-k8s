package com.fis.upStream.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Auction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Float initialPrice;
    private String title;
    private Date startDate;
    private Date endDate;
    private String imagePath;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(Float initialPrice) {
        this.initialPrice = initialPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public Auction() {
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Auction(Float initialPrice, String title, Date startDate, Date endDate, String imagePath, String description) {
        this.initialPrice = initialPrice;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imagePath = imagePath;
        this.description = description;
    }
}
