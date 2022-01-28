import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  username?: string;
  authenticated = false;
  authorities:any = [];
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
      console.log("Nimi "+result['name']);
      console.log("Authorities "+result['authorities']);
      this.printForTesting(result['authorities']);
      
      /* console.log("Details: "+result['details']);
      console.log("Autentikoitunut: "+result['authenticated']);
      console.log("Principal "+result['principal']);
      console.log("Credentials "+result['credentials']); */

      
      for (const key in result['credentials']) {
        console.log(key);
      }
      
      /* Refactor this? */
      if (result['name']) {
        this.username = result['name'];
        this.authenticated = true;
        this.authorities = result['authorities'];
        console.log("Haetaan listalta Objekti ja...: "+this.authorities[0]['authority']);
      } else {

        this.authenticated = false;
        this.authorities = [];
        this.username = "";

      }
      
      return callback && callback();
    });
  }
  printForTesting(list: []) { void
    console.log("Tulostetaan: ")
    list.forEach(element => {
      console.log(element);
    }) 
  }
  listContainsRole(role: string) { 
    return this.authorities.some(function(el:any){return el.authority === role});
  }

}
