import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuctionsService } from '../auctions/auctions.service';
import { Bid } from '../auctions/bid.model';
import { Notification } from '../auctions/notification.model';
import { WebSocketService } from '../auctions/websocket.service';
import { AuthService } from '../auth/auth.service';
import { User } from '../auth/user.model';
import {MatSnackBar} from '@angular/material/snack-bar'
import { Subscriber } from 'rxjs';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  displayedColumns: string[] = ['auctionId', 'buyerId','bidPrice', 'bidDate'];
  displayedNotificationColumns: string[] = ['Massage', 'Timestimp'];
  bids: Bid[];
  notifications: Notification[];
  show;
  user:User;
  username:String;
  constructor(
    private auctionsService:AuctionsService,
    private authService:AuthService,
    private _wsService:WebSocketService,
    private _snackBar: MatSnackBar,
    private route:ActivatedRoute) { }
  ngOnInit(): void {
    this.username = localStorage.getItem("username");
    this.authService.getUserInfo(this.username).subscribe(
        
      response => {
        
        this.user= response
        localStorage.setItem("id", this.user.id.toString());
       }
  
    )
    this.wsSubscribe();
    this.show = true;
  }

  OnSelectBid(){
    this.show = true;
    this.auctionsService.getBidByUserId(this.user.id).subscribe(resp => {
      this.bids = resp;
  });
  }

  OnSelectNotif(){
    this.show = false;
    this.auctionsService.getNotifByUserId(this.user.id).subscribe(resp => {
      this.notifications = resp;
      console.log(this.notifications)
  });
  }

  private wsSubscribe(){
    // Open connection with server socket
    let stompClient = this._wsService.connect();
   
    console.log(stompClient);
   
    stompClient.connect({}, frame => {
      console.log(stompClient);
      console.log(frame);
      // Subscribe to notification topic
      stompClient.subscribe('/user/'+this.user.id+'/queue/messages', notification => {
        console.log(notification)
        this._snackBar.open(notification.body, '', { duration: 7000, horizontalPosition: 'right' });
      })
    })
  }
}
