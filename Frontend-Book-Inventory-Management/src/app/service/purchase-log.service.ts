import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { purchaseLog } from '../Models/purchselog';
@Injectable({
  providedIn: 'root'
})
export class PurchaseLogService {
  private apiUrl = 'http://localhost:9090/api/purchaselog';

  constructor(private http: HttpClient) { }

  addPurchaseLog(purchaseLog: purchaseLog): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/post`, purchaseLog);
  }

  getPurchaseLogs(): Observable<purchaseLog[]> {
    return this.http.get<purchaseLog[]>(this.apiUrl);
  }

  getPurchaseLogsByUserId(userId: number) {
    const url = `${this.apiUrl}/${userId}`;
    return this.http.get(url);
  }

  updatePurchaseLog(userId: number, inventoryId: number) {
    const url = `${this.apiUrl}/update/inventoryId/${userId}`;
    return this.http.put(url, { inventoryId });
  }
}
