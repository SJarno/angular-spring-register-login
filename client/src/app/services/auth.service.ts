import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  authenticated = false;

  constructor(private http: HttpClient) { }

  /* Should authenticate user */
  authenticate(credentials: any, callback: any) {
    const headers = new HttpHeaders(credentials ? {
        authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
    } : {});
    /* Place headers to request */
    this.http.get(environment.baseUrl+'user', {headers: headers}).subscribe((response) => {
        let result: any = response;
        console.log('Vastaus: \n'+result);
        console.log("Käyttäjänimi: "+result['name']);
        console.log("Roolit: "+result['authorities']);
        /* for (const key in result) {
          console.log(key);
          
        } */
        /* Refactor this? */
        if (result['name']) {
            this.authenticated = true;
        } else {
            this.authenticated = false;
            
        }
        return callback && callback();
    });
  }
  
}
