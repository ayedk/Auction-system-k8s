import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuctionsService } from '../auctions.service';

@Component({
  selector: 'app-add-bid',
  templateUrl: './add-bid.component.html',
  styleUrls: ['./add-bid.component.css']
})
export class AddBidComponent implements OnInit {
  auction_id:number;
  user_id:number;
  constructor(private auctionsService:AuctionsService,
    private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.auction_id = parseInt(this.route.snapshot.paramMap.get('id'));
    if (localStorage.getItem('MyApp_Auth')){
      this.user_id = parseInt(localStorage.getItem('MyApp_Auth'));
    }
  }
  

}
