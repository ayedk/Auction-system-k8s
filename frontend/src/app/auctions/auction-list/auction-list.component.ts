import { Component, OnInit } from '@angular/core';
import { Auction } from '../auction.model';
import { AuctionsService } from '../auctions.service';

@Component({
  selector: 'app-auction-list',
  templateUrl: './auction-list.component.html',
  styleUrls: ['./auction-list.component.css']
})
export class AuctionListComponent implements OnInit {
  auctions:Auction[];
  constructor(private auctionsService:AuctionsService) { }
  
  ngOnInit(): void {
     this.auctionsService.getAuctions().subscribe(resp => {
      this.auctions  = resp;
      console.log(resp)
  });;
  }

}
