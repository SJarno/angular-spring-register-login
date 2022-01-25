import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http'
import { environment } from 'src/environments/environment';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { response } from './response';

@Injectable({
  providedIn: 'root'
})
export class ServerRequestsService {

  url = environment.baseUrl;
  testi: any;
  constructor(private http: HttpClient) { }

  getPublicData(): Observable<any> {
    return this.http.get<any[]>(this.url + "public/resource");
  }
  getSecretData(): Observable<any> {
    return this.http.get<any[]>(this.url+"secret/resource");
  }
  getAdminData(): Observable<any> {
    return this.http.get<any>(this.url+"admin/resource");
  }
}
