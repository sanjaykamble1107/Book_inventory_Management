import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { State } from '../Models/state';

@Injectable({
  providedIn: 'root'
})
export class StateService implements OnInit{

  constructor(private _httpClient: HttpClient) { }
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  getStateList(): Observable<any>{
    console.log("wdwaaw");
    return this._httpClient.get('http://localhost:9090/api/state');
  }

  createState(state: State): Observable<Object> {
    console.log("create state");
    return this._httpClient.post('http://localhost:9090/api/state/add', state);
  }

  updateStateName(stateCode: string, stateName: string): Observable<any> {
    const url = `http://localhost:9090/api/state/update/name/${stateCode}`;
    const body = { stateName: stateName };
    return this._httpClient.put(url, body);
  }
}


