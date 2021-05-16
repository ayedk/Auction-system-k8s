import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './auth/signup/signup.component';
import { LoginComponent } from './auth/login/login.component';
import { AuctionDetailComponent } from './auctions/auction-detail/auction-detail.component';
import { AddBidComponent } from './auctions/add-bid/add-bid.component';
import { AuctionListComponent } from './auctions/auction-list/auction-list.component';
import { ProfileComponent } from './profile/profile.component';

const routes: Routes = [
  { path: '', redirectTo: '/', pathMatch: 'full'},
  { path: '', component: AuctionListComponent},
  { path: 'auctions/:id', component: AuctionDetailComponent},
  { path: 'bids/:id', component: AddBidComponent},
  { path: 'profile', component: ProfileComponent},
  { path: 'signup', component: SignupComponent},
  { path: 'login', component: LoginComponent} 
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }