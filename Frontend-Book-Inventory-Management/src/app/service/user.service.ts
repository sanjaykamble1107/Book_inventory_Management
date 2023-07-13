import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../Models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = 'http://localhost:9090/api/user';

  constructor(private http: HttpClient) {}

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl);
  }

  getUserById(userId: number): Observable<User> {
    const url = `${this.baseUrl}/${userId}`;
    return this.http.get<User>(url);
  }

  registerUser(user: User): Observable<any> {
    return this.http.post(`${this.baseUrl}/post`, user);
  }

  updateUserFirstName(userId: number, firstName: string): Observable<any> {
    const url = `${this.baseUrl}/update/firstname/${userId}`;
    const body = { firstName: firstName };
    return this.http.put(url, body);
  }

  updateUserLastName(userId: number, lastName: string): Observable<any> {
    const url = `${this.baseUrl}/update/lastname/${userId}`;
    const body = { lastName: lastName };
    return this.http.put(url, body);
  }

  updateUserPhoneNumber(userId: number, phoneNumber: number): Observable<any> {
    const url = `${this.baseUrl}/update/phonenumber/${userId}`;
    const body = { phoneNumber: phoneNumber };
    return this.http.put(url, body);
  }
}
