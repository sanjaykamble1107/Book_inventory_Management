import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { bookReview } from '../Components/book-review/bookreview';

@Injectable({
  providedIn: 'root'
})
export class BookReviewService {
  private apiUrl = 'http://localhost:9090/api/bookreview';

  constructor(private http: HttpClient) { }

  addBookReview(bookReview: bookReview): Observable<any> {
    const url = `${this.apiUrl}/post`;
    return this.http.post(url, bookReview);
  }

  getBookReviewsByISBN(isbn: string): Observable<bookReview[]> {
    console.log("entered");
    return this.http.get<bookReview[]>(`${this.apiUrl}/${isbn}`);
  }

  updateRatingByISBN(isbn: string, rating: number): Observable<any> {
    const url = `${this.apiUrl}/update/rating/${isbn}`;
    return this.http.put(url, { rating });
  }

  updateCommentsByISBN(isbn: string, comments: string): Observable<any> {
    const url = `${this.apiUrl}/update/comments/${isbn}`;
    return this.http.put(url, { comments });
  }
}
