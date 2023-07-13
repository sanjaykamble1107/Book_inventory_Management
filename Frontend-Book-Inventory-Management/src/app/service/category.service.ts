import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../Models/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private baseUrl = 'http://localhost:9090/api/category/post';

  constructor(private http: HttpClient) { }

  addCategory(category: any): Observable<any> {
    return this.http.post(this.baseUrl, category);
  }
  updateCategory(catId: any, category: any): Observable<any> {
    const url = 'http://localhost:9090/api/category/update/description/' + catId + '';
    return this.http.put(url, category);
  }

  getCategory(catId: number): Observable<Category> {
    const url = 'http://localhost:9090/api/category/' + catId;
    return this.http.get<Category>(url);
  }

  getAllCategories():Observable<Category[]>{
    return this.http.get<Category[]>('http://localhost:9090/api/category/getall')
  }
}
