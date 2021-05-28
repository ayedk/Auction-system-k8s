import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { AuthData } from './authdata';
import { User } from './user.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
    constructor(private router: Router,private http: HttpClient) {}
  
    signupUser(user: User) {
      
      this.http.post("http://localhost:8080/api/users/signup",user)
      .subscribe(
          response => {
              this.router.navigate(['/login']);
            }
          ,err=>{
            console.log(err)
          }
      )
    }
  
    loginUser(authdata: AuthData) {
      this.http.post<{Authorization:String,username:String}>("http://localhost:8080/api/users/login",authdata)
      .subscribe(
          response => {
            console.log(response)
            localStorage.setItem("token", response.Authorization.slice());
            localStorage.setItem("username", response.username.slice());
            //console.log(token)
            this.router.navigate(['/profile']);
           }
      )
    }
    getUserInfo(username: String):any{
      return this.http.get("http://localhost:8080/api/users/info/"+ username)
    }
  
    logout() {
      localStorage.removeItem("token");
      localStorage.removeItem("id");
      localStorage.removeItem("username");
    }
  
    getToken() {
      return localStorage.getItem("token");
    }
  
    isAuthenticated():Boolean {
      let isAuth = this.getToken();
      return isAuth != null;
    }

}

