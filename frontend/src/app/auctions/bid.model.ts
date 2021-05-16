export class Bid{
    public id:number;
    public auctionId:number;
    public bidPrice: number;
    public buyerId: number;
    public bidDate: Date;

    constructor(auction_id:number,bid_pr:number,user:number,bid_d:Date){
        this.auctionId = auction_id;
        this.bidPrice = bid_pr;
        this.buyerId = user;
        this.bidDate = bid_d;
    }
}