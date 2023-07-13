// perm-role.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { permRole } from '../Models/permrole';

@Injectable({
  providedIn: 'root'
})
export class PermRoleServiceService {
  private baseUrl = 'http://localhost:9090/api/permrole';

  constructor(private http: HttpClient) {}

  addPermRole(permRole: permRole): Observable<any> {
    return this.http.post(`${this.baseUrl}/post`, permRole);
  }

  searchPermRole(roleNumber: number): Observable<permRole> {
    return this.http.get<permRole>(`${this.baseUrl}/${roleNumber}`);
  }

  updatePermRole(roleNumber: number, updatedPermRole: string): Observable<any> {
    return this.http.put(`${this.baseUrl}/update/permrole/${roleNumber}`, { permRole: updatedPermRole });
  }
}