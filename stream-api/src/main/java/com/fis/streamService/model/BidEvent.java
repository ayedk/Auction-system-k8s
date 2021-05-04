package com.fis.streamService.model;


import java.util.Date;


public class BidEvent {
    private Integer auctionId;
    private Float  bidPrice;
    private Integer buyerId;
    private Date bidDate;

    public BidEvent(Integer auctionId, Float bidPrice, Integer buyerId, Date bidDate) {
        this.auctionId = auctionId;
        this.bidPrice = bidPrice;
        this.buyerId = buyerId;
        this.bidDate = bidDate;
    }

    public BidEvent() {
    }

    @Override
    public String toString() {
        return "BidEvent{" +
                "auctionId=" + auctionId +
                ", bidPrice=" + bidPrice +
                ", buyerId=" + buyerId +
                ", bidDate=" + bidDate +
                '}';
    }

    public Integer getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Integer auctionId) {
        this.auctionId = auctionId;
    }

    public Float getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(Float bidPrice) {
        this.bidPrice = bidPrice;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Date getBidDate() {
        return bidDate;
    }

    public void setBidDate(Date bidDate) {
        this.bidDate = bidDate;
    }
}
