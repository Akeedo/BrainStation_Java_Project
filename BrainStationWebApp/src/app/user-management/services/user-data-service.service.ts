import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserDataServiceService {

  constructor(
    private http: HttpClient
  ) { }

  private apiURL: string = 'http://localhost:8080';

    
    saveUser(user: any): Observable<any>{
      return this.http.post<any>(this.apiURL +'/users', user);
    }

    getUsers(): Observable<any>{
      return this.http.get<any>(this.apiURL +'/users');
    }

    getUser(id: number): Observable<any>{
      return this.http.get<any>(this.apiURL +'/users/'+id);
    }

    updateUser(user: any): Observable<any>{
      return this.http.put<any>(this.apiURL +'/users/'+user.id, user);
    }

    deleteUser(id: number): Observable<any>{
      return this.http.delete<any>(this.apiURL +'/users/'+id);
    }
  }

