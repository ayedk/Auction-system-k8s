import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Auction } from '../auction.model';
import { AuctionsService } from '../auctions.service';
import { Bid } from '../bid.model';

@Component({
  selector: 'app-auction-detail',
  templateUrl: './auction-detail.component.html',
  styleUrls: ['./auction-detail.component.css']
})
export class AuctionDetailComponent implements OnInit {
  displayedColumns: string[] = ['id', 'auctionId','bidPrice', 'buyerId', 'bidDate'];
  bids: Bid[];
  interval;
  timeLeft: number;
  auction: Auction;
  constructor(
    private auctionsService:AuctionsService,
    private route:ActivatedRoute) { }
  
  startTimer() {
    this.interval = setInterval(() => {
      if(this.timeLeft > 0) {
        this.timeLeft--;
      }
    },1000)
  }
  ngOnInit(): void {
    let id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.auction = this.auctionsService.getAuctionId(id);
    this.bids = this.auctionsService.getBidId(id);
    this.timeLeft = this.auction.endDate.getTime() - this.auction.startDate.getTime()
    this.startTimer()
  }
}
