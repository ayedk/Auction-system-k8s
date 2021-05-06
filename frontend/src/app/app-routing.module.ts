import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './auth/signup/signup.component';
import { LoginComponent } from './auth/login/login.component';
import { AuctionListComponent } from './auctions/auction-list/auction-list.component';
import { AuctionDetailComponent } from './auctions/auction-detail/auction-detail.component';
import { AddBidComponent } from './auctions/add-bid/add-bid.component';

const routes: Routes = [
  {path: '', redirectTo: '/', pathMatch: 'full'},
  { path: 'auctions/:id', component: AuctionDetailComponent },
  { path: 'bids/:id', component: AddBidComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'login', component: LoginComponent } 
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }