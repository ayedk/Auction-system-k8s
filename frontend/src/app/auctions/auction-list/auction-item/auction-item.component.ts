import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Auction } from '../../auction.model';

@Component({
  selector: 'app-auction-item',
  templateUrl: './auction-item.component.html',
  styleUrls: ['./auction-item.component.css']
})
export class AuctionItemComponent implements OnInit {
  @Input() auction: Auction;
  interval;
  timeLeft: number;
  constructor(private router:Router) { }
  startTimer() {
    this.interval = setInterval(() => {
      if(this.timeLeft > 0) {
        this.timeLeft--;
      } else {
        this.timeLeft = 60;
      }
    },1000)
  }
  ngOnInit(): void {
    this.timeLeft = this.auction.endDate.getTime() - this.auction.startDate.getTime()
    this.startTimer()
  }

  onSelect(auction:Auction){
    this.router.navigate(['/auctions',auction.id]);
  }
}
