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
  /* Todo: user info here from principal */

  constructor(private http: HttpClient) { }

  /* Should authenticate user */
  authenticate(credentials: any, callback: any) {
    const headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});
    /* Place headers to request */
    this.http.get(environment.baseUrl + 'user', { headers: headers }).subscribe((response) => {
      let result: any = response;
      console.log('Vastaus: \n' + result);
      console.log("Nimi " + result['name']);
      console.log("Authorities " + result['authorities']);
      this.printForTesting(result['authorities']);

      for (const key in result['credentials']) {
        console.log(key);
      }

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
