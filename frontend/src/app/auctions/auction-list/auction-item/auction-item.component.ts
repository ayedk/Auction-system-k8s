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
  constructor(private router:Router) { }
  day;
  hours;
  minutes;
  seconds;

  ngOnInit(): void {
    this.day = new Date(this.auction.endDate).getDay();
    this.hours = new Date(this.auction.endDate).getHours();
    this.minutes = new Date(this.auction.endDate).getMinutes();
    this.seconds = new Date(this.auction.endDate).getSeconds();
  }


  onSelect(){
    this.router.navigate(['/auctions',this.auction.id]);
  }
}
