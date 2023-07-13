import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Reviewer } from '../Models/Reviewer';

@Injectable({
  providedIn: 'root'
})
export class ReviewerService implements OnInit {

  private baseUrl = 'http://localhost:9090/api/reviewer';

  constructor(private http: HttpClient) {}
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  addReviewer(reviewer: Reviewer): Observable<any> {
    const url = `${this.baseUrl}/post`;
    return this.http.post(url, reviewer);
  }

  getReviewerById(reviewerId: number): Observable<Reviewer> {
    const url = `${this.baseUrl}/${reviewerId}`;
    return this.http.get<Reviewer>(url);
  }

  updateReviewerName(reviewerId: number, name: string): Observable<any> {
    const url = `${this.baseUrl}/update/name/${reviewerId}`;
    const body = { name: name };
    return this.http.put(url, body);
  }

  updateReviewerEmployedBy(reviewerId: number, employedBy: string): Observable<any> {
    const url = `${this.baseUrl}/update/employedby/${reviewerId}`;
    const body = { employedBy: employedBy };
    return this.http.put(url, body);
  }
}