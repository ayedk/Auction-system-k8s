import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { AuthData } from '../authdata';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService:AuthService,
    private _snackBar: MatSnackBar,
    private router: Router) { }

  ngOnInit(): void {
  }
  onLogin(form:NgForm){
    const authdata = new AuthData()
    authdata.username=form.value.username;
    authdata.password=form.value.password;
    this.authService.loginUser(authdata).subscribe(
      response => {
        
        console.log(response)
        localStorage.setItem("token", response.Authorization.slice());
        localStorage.setItem("username", response.username.slice());
        //console.log(token)
        this.router.navigate(['/profile']);
          
        
          
        }
      ,err=>{
        this._snackBar.open("wrong credential", '',{ duration: 2000});
      }
  );;

  }
}
