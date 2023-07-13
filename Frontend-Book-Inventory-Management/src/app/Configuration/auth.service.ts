import { Injectable, OnInit } from '@angular/core';
import { Guest } from './Model/Guest';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import {
  HttpClient,
  HttpHeaders,
  HttpErrorResponse,
} from '@angular/common/http';
import { Router } from '@angular/router';
import { LoginRequest } from './Model/LoginRequest';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  endpoint: string = 'http://localhost:9090/api';

  headers = new HttpHeaders().set('Content-Type', 'application/json');

  createAuthorizationHeader(): HttpHeaders {
    let authHeaders: HttpHeaders = new HttpHeaders();
    console.log('create header called');
    return authHeaders.set('Authorization', 'Bearer ' + this.getToken());
  }

  currentUser = {};
  constructor(private http: HttpClient, public router: Router) {}

  // Sign-up

  signUp(guest: Guest): Observable<any> {
    let api = `${this.endpoint}/auth/register`;
    return this.http
      .post(api, guest, { headers: this.headers })
      .pipe(catchError(this.handleError));
  }
  // Sign-in

  signIn(request: LoginRequest) {
    console.log(request);

    return this.http
      .post<any>(`${this.endpoint}/auth/login`, request, {
        headers: this.headers,
      })
      .subscribe(
        (res: any) => {
          console.log(res);

          sessionStorage.setItem('access_token', res.jwtToken);
          sessionStorage.setItem('username', res.username);
          sessionStorage.setItem('roleNumber', res.roleNumber);

          // localStorage.setItem('access_token', res.jwtToken);
          // localStorage.setItem('username', res.username);
          // localStorage.setItem('roleNumber', res.roleNumber);
          this.currentUser = res.username;
          console.log(this.currentUser);
          this.navigateToDashboard(res.roleNumber);
          console.log('Role : ' + res.roleNumber);
        },
        (error: HttpErrorResponse) => {
          const errorMessage = error.error.errorMessage; // Retrieve the error message from the HTTP response
          alert(errorMessage); // Show the error message to the user
        }
      );
  }

  getToken(): string | null {
    // console.log(localStorage.getItem('access_token'));
    // return localStorage.getItem('access_token')

    console.log(sessionStorage.getItem('access_token'));
    return sessionStorage.getItem('access_token');
  }

  get isLoggedIn(): boolean {
    // let authToken = localStorage.getItem('access_token');
    // return authToken !== null && authToken !== '' ? true : false;

    let authToken = sessionStorage.getItem('access_token');
    return authToken !== null && authToken !== '' ? true : false;
  }

  doLogout() {
    sessionStorage.removeItem('access_token');
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('roleNumber');
    this.router.navigate(['home']);

    // let removeToken = localStorage.removeItem('access_token');
    // localStorage.removeItem('username');
    // localStorage.removeItem('roleNumber');

    // if (removeToken == null || removeToken == '') {
    //   this.router.navigate(['home']);
    // }
  }

  getUsername(): string | null {
    let username = sessionStorage.getItem('username');
    return username != null && username !== '' ? username : null;

    // let username = localStorage.getItem('username');
    // if (username != null || username != '') return username;
    // else return null;
  }

  // getRoleNumber(): number | null {
  //   const roleNumber = localStorage.getItem('roleNumber');
  //   return roleNumber !== null ? parseInt(roleNumber, 10) : null;  }
  // User profile

  getRoleNumber(): string | null {
    const roleNumber = sessionStorage.getItem('roleNumber');
    return roleNumber !== null && roleNumber !== '' ? roleNumber : null;

    // const roleNumber = localStorage.getItem('roleNumber');
    // return roleNumber !== null ? roleNumber : null;
  }

  getUserProfile(userId: any): Observable<any> {
    let api = `${this.endpoint}/user/${userId}`;

    return this.http.get(api, { headers: this.headers }).pipe(
      map((res) => {
        console.log(res);
        return res;
        // return res || {};
      }),
      catchError(this.handleError)
    );
  }

  // Error

  handleError(error: HttpErrorResponse) {
    let msg = '';

    if (error.error instanceof ErrorEvent) {
      // client-side error

      msg = error.error.message;
    } else {
      // server-side error

      msg = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }

    return throwError(msg);
  }

  navigateToDashboard(roleNumber: string) {
    switch (roleNumber) {
      case 'Guest':
        console.log('guest run');
        this.router.navigate(['/guest-dashboard']);
        break;
      case 'RegisteredUser':
        console.log('registered user run');
        this.router.navigate(['/reg-user-dashboard']);
        break;
      case 'StoreOwner':
        console.log('store ownwer run');
        this.router.navigate(['/store-owner-dashboard']);
        break;
      case 'Admin':
        console.log('admin run');
        this.router.navigate(['/dashboard']);
        break;
      default:
        console.log('default run');
        this.router.navigate(['/home']);
        break;
    }
  }
}
