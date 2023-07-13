import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Inventory } from '../Models/inventory';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {
  private apiUrl = 'http://localhost:9090/api/inventory';

  constructor(private http: HttpClient) { }

  addInventory(inventory: Inventory): Observable<any> {
    return this.http.post(`${this.apiUrl}/add`, inventory);

  }

  searchInventory(inventoryId: number): Observable<Inventory> {
    const url = `${this.apiUrl}/${inventoryId}`;
    return this.http.get<Inventory>(url);
  }

  updatePurchased(inventoryId: number, purchased: number): Observable<any> {
    const url = `${this.apiUrl}/update/${inventoryId}`;
    const body = { purchased: purchased };
    return this.http.put(url, body);
  }
}
