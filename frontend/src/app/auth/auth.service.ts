import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { AuthData } from './authdata';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
    token: string;
    constructor(private router: Router,private http: HttpClient) {}
  
    signupUser(authdata: AuthData) {
      
      this.http.post("http://localhost:8080/signup",authdata)
      .subscribe(
          response => {
              this.router.navigate(['/signin']);
            }
      )
    }
  
    loginUser(authdata: AuthData) {
      this.http.post("http://localhost:8080/login",authdata)
      .subscribe(
          response => {
            localStorage.setItem('userData', JSON.stringify(response));
            this.router.navigate(['/']);
            
          }
        )
    }
  
    logout() {
      this.token = null;
    }
  
    /*getToken() {
      firebase.auth().currentUser.getIdToken()
        .then(
          (token: string) => this.token = token
        );
      return this.token;
    }*/
  
    isAuthenticated() {
      //return this.token != null;
      return false;
    }

}

