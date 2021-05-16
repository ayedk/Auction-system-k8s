import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../auth.service';
import { User } from '../user.model';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  constructor(private authService: AuthService) { }

  ngOnInit() {
  }
  onSignup(form:NgForm){
    const user = new User()
    user.username=form.value.username;
    user.firstname=form.value.firstname;
    user.lastname=form.value.lastname;
    user.email=form.value.email;
    user.password=form.value.password;
    console.log(user)
    this.authService.signupUser(user);
  }

}
