import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';
import { AuthService } from '../Configuration/auth.service';
import { Book } from '../Models/book';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private apiUrl = 'http://localhost:9090/api/book'; // API endpoint




  constructor(private httpClient: HttpClient) { }




  getAllBooks(): Observable<Book[]> {

    return this.httpClient.get<Book[]>(this.apiUrl);

  }

  createBook(book: any): Observable<object> {

    return this.httpClient.post('http://localhost:9090/api/book/post', book);

  }




  getByIsbn(isbn: any): Observable<any> {

    // Make an HTTP request to fetch book by ISBN




    return this.httpClient.get<any>('http://localhost:9090/api/book/' + isbn);

  }

  getBooksByTitle(title: any): Observable<any> {

    console.log("service");




    return this.httpClient.get<any>(`http://localhost:9090/api/book/title/${title}`).pipe(

      map((response: Book) => response));

  }

  getBooksByCategory(category: string): Observable<any> {

    console.log("entered service");

    // Make an HTTP request to fetch books by category

    return this.httpClient.get<any>('http://localhost:9090/api/book/category/' + category);

  }




  getBooksByPublisherId(publisherId: any): Observable<any> {

    // Make an HTTP request to fetch books by publisher ID

    return this.httpClient.get<any>('http://localhost:9090/api/book/publisher/' + publisherId);

  }




  updateBookTitleByISBN(isbn: string, title: string): Observable<any> {

    return this.httpClient.put<any>(`${this.apiUrl}/update/title/${isbn}`, { title });

  }




  updateBookDescriptionByISBN(isbn: string, description: string): Observable<any> {

    return this.httpClient.put<any>(`${this.apiUrl}/update/description/${isbn}`, { description });

  }




  updateBookCategoryByISBN(isbn: string, category: any): Observable<any> {

    return this.httpClient.put<any>(`${this.apiUrl}/update/category/${isbn}`, { category });

  }




  updateBookEditionByISBN(isbn: string, edition: number): Observable<any> {

    return this.httpClient.put<any>(`${this.apiUrl}/update/edition/${isbn}`, { edition });

  }




  updateBookPublisherByISBN(isbn: string, publisherId: any): Observable<any> {

    return this.httpClient.put<any>(`${this.apiUrl}/update/publisher/${isbn}`, { publisherId });

  }




  updateBookDetailsByISBN(isbn: string, book: any): Observable<any> {

    return this.httpClient.put<any>(`${this.apiUrl}/update/${isbn}`, book).pipe(

      catchError((error: HttpErrorResponse) => {

        const errorMessage = error.error.errorMessage;

        return throwError(errorMessage);

      })

    );

  }

  // private apiUrl = 'http://localhost:9090/api/book'; // API endpoint

  // constructor(private httpClient: HttpClient) { }

  // getAllBooks(): Observable<Book[]> {
  //   return this.httpClient.get<Book[]>(this.apiUrl);
  // }
  // createBook(book: any): Observable<object> {
  //   return this.httpClient.post('http://localhost:9090/api/book/post', book);
  // }

  // getByIsbn(isbn: any): Observable<any> {
  //   // Make an HTTP request to fetch book by ISBN

  //   return this.httpClient.get<any>('http://localhost:9090/api/book/' + isbn);
  // }
  // getBooksByTitle(title: any): Observable<any> {
  //   console.log("service");

  //   return this.httpClient.get<any>(`http://localhost:9090/api/book/title/${title}`).pipe(
  //     map((response: Book) => response));
  // }
  // getBooksByCategory(category: string): Observable<any> {
  //   console.log("entered service");
  //   // Make an HTTP request to fetch books by category
  //   return this.httpClient.get<any>('http://localhost:9090/api/book/category/' + category);
  // }

  // getBooksByPublisherId(publisherId: any): Observable<any> {
  //   // Make an HTTP request to fetch books by publisher ID
  //   return this.httpClient.get<any>('http://localhost:9090/api/book/publisher/' + publisherId);
  // }

  // updateBookTitleByISBN(isbn: string, title: string): Observable<any> {

  //   return this.httpClient.put<any>(`http://localhost:9090/api/book/update/title/${isbn}`, { title });
  // }

  // updateBookDescriptionByISBN(isbn: string, description: string): Observable<any> {
  //   return this.httpClient.put<any>(`http://localhost:9090/api/book/update/description/${isbn}`, { description });
  // }

  // updateBookCategoryByISBN(isbn: string, category: any): Observable<any> {
  //   return this.httpClient.put<any>(`http://localhost:9090/api/book/update/category/${isbn}`, { category });
  // }

  // updateBookEditionByISBN(isbn: string, edition: number): Observable<any> {
  //   return this.httpClient.put<any>(`http://localhost:9090/api/book/update/edition/${isbn}`, { edition });
  // }

  // updateBookPublisherByISBN(isbn: string, publisherId: any): Observable<any> {
  //   return this.httpClient.put<any>(`http://localhost:9090/api/book/update/publisher/${isbn}`, { publisherId });
  // }

}