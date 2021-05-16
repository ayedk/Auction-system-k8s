import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuctionsService } from '../auctions.service';
import { Bid } from '../bid.model';

@Component({
  selector: 'app-add-bid',
  templateUrl: './add-bid.component.html',
  styleUrls: ['./add-bid.component.css']
})
export class AddBidComponent implements OnInit {
  auction_id:number;
  user_id:number;
  constructor(private auctionsService:AuctionsService,
    private route:ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.auction_id = parseInt(this.route.snapshot.paramMap.get('id'));
    if (localStorage.getItem('id')){
      this.user_id = parseInt(localStorage.getItem('id'));
    }
  }
  onSubmit(form:NgForm){
    let bid = new Bid(this.auction_id,form.value.price,this.user_id,new Date());
    this.auctionsService.addBid(bid);
    this.router.navigate(['/profile']);
  }

}
