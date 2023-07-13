import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Author } from '../Models/Author';
import { Book } from '../Models/book';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {
  constructor(private httpClient: HttpClient) { }

  addAuthor(author: Author): Observable<Object> {
    return this.httpClient.post<Author[]>('http://localhost:9090/api/author/post', author);
  }

  searchAuthors(authorId?: number, firstName?: string, lastName?: string): Observable<Author[]> {
    let url = 'http://localhost:9090/api/author';
    if (authorId) {
      url += `/${authorId}`;
    } else if (firstName) {
      url += `/firstname/${firstName}`;
    } else if (lastName) {
      url += `/lastname/${lastName}`;
    }
    return this.httpClient.get<Author[]>(url);
  }

  getData(): Observable<any[]> {
    return this.httpClient.get<any[]>('http://localhost:9090/api/author');
  }

  getBooksByAuthor(authorId: number): Observable<Book[]> {
    const url = `http://localhost:9090/api/author/books/${authorId}`;
    let data: any = this.httpClient.get<Book[]>(url);
    return data;
  }

  updateFirstName(authorId: number, firstName: string): Observable<any> {
    const url = `http://localhost:9090/api/author/update/firstname/${authorId}`;
    return this.httpClient.put(url, { firstName });
  }

  updateLastName(authorId: number, lastName: string): Observable<any> {
    const url = `http://localhost:9090/api/author/update/lastname/${authorId}`;
    return this.httpClient.put(url, { lastName });
  }
}
