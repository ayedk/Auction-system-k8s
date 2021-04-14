import { Auction } from "./auction.model";
import { Bid } from "./bid.model";



export class AuctionsService{


    private Auctions:Auction[]
    private Bids:Bid[] 
    constructor(){}

    getAuctions(){
        return this.Auctions.slice();
    }

    getBids(){
        return this.Bids.slice();
    }

    getAuctionId(id:number){
        return this.Auctions[id];
    }
}