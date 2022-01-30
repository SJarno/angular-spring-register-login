import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  username?: string;
  authenticated: boolean = false;
  authorities: any = [];
  errorMessage!: string;

  constructor(private http: HttpClient) { }

  /* Should authenticate user, original method: */
  testAuthenticate(credentials: any, callback: any) {
    const headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});
    /* Place headers to request */
    this.http.get(environment.baseUrl + 'user', { headers: headers }).subscribe((response) => {
      let result: any = response;
      
      /* If the principal is found, place values: */
      if (result['name']) {
        this.username = result['name'];
        this.authenticated = true;
        this.authorities = result['authorities'];
      /* else, checkout authentication:  */
      } else {
        this.authenticated = false;
        this.authorities = [];
        this.username = "";
        

      }

      return callback && callback();
    });
  }
  /* Rather user this one, to subscribe later and display errors: */
  authenticate(credentials: any, callback: any) {
    const headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.username + ':'+credentials.password)
    } : {});
    return this.http.get(environment.baseUrl + 'user', {headers: headers});
      
    
  }
  /* For */
  printForTesting(list: []) {
    console.log("Tulostetaan: ")
    list.forEach(element => {
      console.log(element);
    })
  }
  listContainsRole(role: string) {
    return this.authorities.some((auth: { authority: string; }) => auth.authority === role);
    
  }

}
