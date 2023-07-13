import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Bookcondition } from '../Models/bookcondition';

@Injectable({
  providedIn: 'root'
})
export class BookConditionService {
  private apiUrl = 'http://localhost:9090/api/bookcondition';
  constructor(private http: HttpClient) {}
  
  addBookCondition(book: any): Observable<object> {
    return this.http.post(`${this.apiUrl}/post`, book);
  }
  
  updateDescription( ranks: any, description:any): Observable<any> {  
    //url = 'localhost:9090/api/bookcondition/update/description/'+ranks;
    //return this.http.put(url , description );
    return this.http.put(`${this.apiUrl}/update/description/${ranks}`, description);
  }
  
  updateFullDescription( ranks: any, fulldescription:Bookcondition): Observable<any> {  
    //return ('http://localhost:9090/api/bookcondition/update/fulldescription/'+ranks)
    console.log("bookcondition service called");
    return this.http.put(`${this.apiUrl}/update/fulldescription/${ranks}`, fulldescription);
  }

  getBookConditionByRanks(ranks: string) {
    const url = `${this.apiUrl}/${ranks}`;
    return this.http.get(url);
  }
}
