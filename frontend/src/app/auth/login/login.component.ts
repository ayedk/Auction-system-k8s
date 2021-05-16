import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../auth.service';
import { AuthData } from '../authdata';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService:AuthService) { }

  ngOnInit(): void {
  }
  onLogin(form:NgForm){
    const authdata = new AuthData()
    authdata.username=form.value.username;
    authdata.password=form.value.password;
    this.authService.loginUser(authdata);

  }
}
