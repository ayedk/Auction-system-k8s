import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Auction } from "./auction.model";
import { Bid } from "./bid.model";

@Injectable({
    providedIn: 'root',
  })
export class AuctionsService{

    constructor(private http: HttpClient){}

    egt():Observable<any>{
        return this.http.get<Auction>("http://localhost:8080/api/auctions")
    }

    getAuctions():any{
        return this.http.get<Auction>("http://localhost:8080/api/auctions")
        
    }

    getBidByAuctionId(id:number):any{
        return this.http.get<Bid>("http://localhost:8080/api/bids/auction/"+id)
        
    }

    getAuctionId(id:number):any{
        return this.http.get<Auction>("http://localhost:8080/api/auctions/"+id)
        }

    getBidByUserId(id:number):any{

        return this.http.get<Bid>("http://localhost:8080/api/bids/user/"+id)
    }

    getNotifByUserId(id:number):any{
        return this.http.get<Notification>("http://localhost:8080/api/notifications/"+id)
       
    }

    addBid(bid:Bid){
        this.http.post("http://localhost:8080/api/bids",bid)
    }
}