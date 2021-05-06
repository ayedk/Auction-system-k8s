import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Auction } from "./auction.model";
import { Bid } from "./bid.model";

@Injectable()
export class AuctionsService{

    constructor(private http: HttpClient){}

    getAuctions(){
        return this.http.get<Auction>("http://localhost:8080/api/auctions");
    }

    getBidId(id:number):any{
        this.http.get<Bid>("http://localhost:8080/api/bid"+id)
        .subscribe(resp => {
            return resp;
        });
    }

    getAuctionId(id:number):any{
        this.http.get<Auction>("http://localhost:8080/api/auctions"+id)
        .subscribe(resp => {
            return resp;
        });
    }
}