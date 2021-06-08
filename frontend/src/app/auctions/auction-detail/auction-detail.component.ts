import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';
import { Auction } from '../auction.model';
import { AuctionsService } from '../auctions.service';
import { Bid } from '../bid.model';

@Component({
  selector: 'app-auction-detail',
  templateUrl: './auction-detail.component.html',
  styleUrls: ['./auction-detail.component.css']
})
export class AuctionDetailComponent implements OnInit {
  displayedColumns: string[] = ['auctionId', 'buyerId','bidPrice', 'bidDate'];
  bids: Bid[];
  auction: Auction;
  constructor(
    private auctionsService:AuctionsService,
    private authService:AuthService,
    private route:ActivatedRoute,
    private router:Router) { }
    month;
    day;
    hours;
    minutes;
    seconds;
  ngOnInit(): void {
    let id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.auctionsService.getAuctionId(id).subscribe(resp => {
      this.auction =  resp;
      this.month = new Date(this.auction.endDate).getMonth();
      this.day = new Date(this.auction.endDate).getDay();
      this.hours = new Date(this.auction.endDate).getHours();
      this.minutes = new Date(this.auction.endDate).getMinutes();
      this.seconds = new Date(this.auction.endDate).getSeconds();
  });;
    this.auctionsService.getBidByAuctionId(id).subscribe(resp => {
      this.bids =  resp;
  });
  }

  onSelect(){
    let id = parseInt(this.route.snapshot.paramMap.get('id'));
    if(this.authService.isAuthenticated()){
      this.router.navigate(['/place-bid',id]);
    }else{
      this.router.navigate(['/login']);
    }
    

  }
}
