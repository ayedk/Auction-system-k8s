package com.fis.upStream.model;


import javax.persistence.*;
import java.util.Date;

@Entity(name = "auction")
@Table(name = "auction")
public class Auction {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "initial_price")
    private Float initialPrice;
    @Column(name = "title")
    private String title;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "description")
    private String description;

    public Auction() {
    }

    public Auction(Float initialPrice, String title, Date startDate, Date endDate, String imagePath, String description) {
        this.initialPrice = initialPrice;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.imagePath = imagePath;
        this.description = description;
    }

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

    public void setDescription(String description) {
        this.description = description;
    }
}

