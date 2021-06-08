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
      
      return this.http.post<{status,message}>("http://localhost:8080/api/users/signup",user)
      
    }
  
    loginUser(authdata: AuthData) {
      return this.http.post<{Authorization:String,username:String}>("http://localhost:8080/api/users/login",authdata)
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

