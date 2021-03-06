import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { User } from '../user.model';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  constructor(private authService: AuthService,
    private router: Router,
    private _snackBar: MatSnackBar) { }

  ngOnInit() {
  }
  onSignup(form:NgForm){
    const user = new User()
    user.username=form.value.username;
    user.firstname=form.value.firstname;
    user.lastname=form.value.lastname;
    user.email=form.value.email;
    user.password=form.value.password;
    this.authService.signupUser(user).subscribe(
      response => {
        if(response.status==200){
          this.router.navigate(['/login']);
        }else{
          this._snackBar.open(response.message, '',{ duration: 2000});
        }
          
        }
      ,err=>{
        console.log(err)
      }
  );
  }

}
