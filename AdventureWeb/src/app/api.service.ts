import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  apiUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  getAllEmployees(): Observable<any> {
    return this.http.get(`${this.apiUrl}Employees`);
  }

  addEmployee(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}Employees`, data);
  }

  updateEmployee(id: number, data: any): Observable<any> {
    return this.http.put(`${this.apiUrl}Employees/${id}`, data);
  }

  deleteEmployee(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}Employees/${id}`);
  }

  getSalesByEmployee(employee: number, startDate?: string | null, endDate?: string | null): Observable<any> {
    return this.http.get(`${this.apiUrl}Employees/${employee}/Sales?startDate=${startDate}&endDate=${endDate}`);
  }

  getDepartments(): Observable<any> {
    return this.http.get(`${this.apiUrl}Departments`);
  }

  getShifts(): Observable<any> {
    return this.http.get(`${this.apiUrl}Shifts`);
  }

  getProvinces(): Observable<any> {
    return this.http.get(`${this.apiUrl}Provinces`);
  }

}
