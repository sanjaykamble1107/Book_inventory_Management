import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, forkJoin } from 'rxjs';
import { bookReview } from '../Components/book-review/bookreview';
import { AuthService } from '../Configuration/auth.service';

@Injectable({
  providedIn: 'root'
})
export class PublisherService {


  private apiUrl = 'http://localhost:9090/api/publisher';

  constructor(private httpClient: HttpClient,private authService:AuthService) { }

  getAllPublisher(): Observable<any> {
    return this.httpClient.get(this.apiUrl,{headers:this.authService.headers});
  }

  addPublisher(publisher: any): Observable<object> {
    return this.httpClient.post(`${this.apiUrl}/post`, publisher);
  }

  updatePublisher(
    publisherId: number,
    name: string,
    city: string,
    stateCode: string
  ): Observable<any> {
    const updateNameUrl = `${this.apiUrl}/update/name/${publisherId}`;
    const updateCityUrl = `${this.apiUrl}/update/city/${publisherId}`;
    const updateStateUrl = `${this.apiUrl}/update/state/${publisherId}`;

    const updateName$ = this.httpClient.put(updateNameUrl, { name });
    const updateCity$ = this.httpClient.put(updateCityUrl, { city });
    const updateState$ = this.httpClient.put(updateStateUrl, { stateCode });

    return forkJoin([updateName$, updateCity$, updateState$]);
  }

  getPublisherById(publisherId: number) {
    const url = `http://localhost:9090/api/publisher/${publisherId}`;
    return this.httpClient.get(url);
  }

  getPublisherByName(name: string) {
    const url = `http://localhost:9090/api/publisher/name/${name}`;
    return this.httpClient.get(url);
  }

  getPublisherByCity(city: string) {
    console.log("getbycityservice entered");
    const url = `http://localhost:9090/api/publisher/city/${city}`;
    console.log("getbycityservice exit");
    return this.httpClient.get<bookReview[]>(url);
  }

  getPublisherByStateCode(stateCode: string) {
    const url = `http://localhost:9090/api/publisher/state/${stateCode}`;
    return this.httpClient.get<bookReview[]>(url);
  }
}

